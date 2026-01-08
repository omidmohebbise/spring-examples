# Threads use cases (Spring Boot + Tomcat)

Below is a quick reference for the thread prefixes you observed from `Thread.getAllStackTraces()` grouping.

> Sample:
>
> `Threads by prefix: {container=1, Signal Dispatcher=1, RMI Scheduler(0)=1, Notification Thread=1, Common=1, Monitor Ctrl=1, Reference Handler=1, scheduling=1, DestroyJavaVM=1, http=52, Finalizer=1, Catalina=2, RMI TCP Connection(idle)=4, Attach Listener=1, RMI TCP Accept=1}`

## Thread prefixes table

| Thread prefix (as seen) | What it typically is | Who creates it | When / why it exists | Notes / what to watch |
|---|---|---|---|---|
| `http` | **Tomcat request worker threads** (often `http-nio-8080-exec-*`) that execute controllers/filters and blocking work | Embedded **Tomcat** (Spring Boot web starter) | Created on demand up to `server.tomcat.threads.max` to process incoming HTTP requests | This is the main pool you tune with `server.tomcat.threads.*`. If you block (sleep/JDBC/IO) on these threads, throughput drops. Your `/api/tomcat-threads` endpoint reads the exact JMX counts. |
| `Catalina` | Tomcat internal threads (housekeeping, container background processor, etc.) | **Tomcat** | Lifecycle + periodic background tasks | Not controlled via `server.tomcat.threads.max`. Usually low count. |
| `container` | JVM/service container support thread (name varies by JDK/JVM build) | **JDK/JVM runtime** | Internal runtime management / service threads | Not application-controlled. Exact meaning can vary by vendor/version. Usually ignore unless it spikes. |
| `scheduling` | Spring’s scheduler thread(s) used for `@Scheduled` tasks | **Spring Framework / Spring Boot** | Exists when scheduling is enabled (you use `@EnableScheduling`) and tasks run | Can be tuned via `spring.task.scheduling.pool.size`. If you create long-running `@Scheduled` tasks, increase pool size or offload the work. |
| `Common` | ForkJoin common pool threads (often `ForkJoinPool.commonPool-*`, but your prefix grouping chops at `-`) used by `CompletableFuture` and some libraries | **JDK** | Lazy-created when libraries/app use the common pool | If you see many, something is using `CompletableFuture.supplyAsync(...)` without providing an Executor. You can also see these with parallel streams. |
| `Reference Handler` | JVM reference-processing thread for `java.lang.ref.*` (weak/soft/phantom references) | **JVM** | Always present; part of GC/reference cleanup | Normal; not app-controlled. |
| `Finalizer` | JVM finalizer thread handling legacy finalization (`finalize()`) | **JVM** | Present for backward compatibility; runs finalizers (discouraged in modern Java) | If you see heavy activity here, some code still uses finalizers (bad for latency). |
| `Signal Dispatcher` | JVM thread for OS signal handling (e.g., TERM/INT) | **JVM** | Always present | Normal; not app-controlled. |
| `DestroyJavaVM` | Main JVM thread waiting for shutdown (often the “main” lifecycle thread) | **JVM** | Exists for the lifetime of the process | Normal; indicates the JVM main thread sits here while app runs. |
| `Notification Thread` | JVM/JMX notification dispatch thread | **JVM (JMX subsystem)** | For delivering JMX notifications/events | Often present when JMX is enabled/used (Spring Boot often enables JMX by default unless disabled). |
| `Monitor Ctrl` | JDK internal monitoring/control thread (vendor-specific naming) | **JDK/JVM** | Internal monitoring/control infrastructure | Name/behavior varies. Typically low count. |
| `Attach Listener` | JVM attach mechanism thread (supports tools like VisualVM, JMC, jcmd, profilers) | **JVM** | Appears when attach API is enabled/used; sometimes always present | Normal. Security-sensitive environments sometimes disable attach. |
| `RMI Scheduler(0)` | RMI runtime scheduler thread used by JMX/RMI | **JDK (RMI/JMX)** | Usually created when JMX remote/RMI is used | If you expose JMX remotely, you’ll see RMI threads. |
| `RMI TCP Accept` | RMI server acceptor thread | **JDK (RMI)** | Listens for RMI connections | Usually tied to JMX remote or other RMI usage. |
| `RMI TCP Connection(idle)` | RMI connection handler threads (idle) | **JDK (RMI)** | Created as clients connect; may remain idle | Count can grow if many clients connect (monitoring tools, IDEs). |

## Quick takeaways

- **Only the `http-*` request worker pool** is directly limited by `server.tomcat.threads.max`.
- JVM threads like **GC, signal handling, reference handling, RMI/JMX** are expected and not controlled by Tomcat.
- If you want fewer non-request threads, look at: JMX/RMI usage, your own executors, and Spring scheduling/task pools.

## Optional: improve the prefix grouping

Your grouping currently uses `name.indexOf('-')`, which makes `http-nio-8080-exec-1` show up as `http`. If you want more detail, group using a smarter rule (e.g., keep `http-nio` or `http-nio-8080-exec`).

