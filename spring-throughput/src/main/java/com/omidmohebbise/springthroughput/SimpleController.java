package com.omidmohebbise.springthroughput;

import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Map;
import java.util.concurrent.Callable;

@Slf4j
@RestController
@CrossOrigin("*")
@RequestMapping("/api")
public class SimpleController {

    private final BlockingIOService blockingIOService;

    public SimpleController(BlockingIOService blockingIOService) {
        this.blockingIOService = blockingIOService;
    }

    @GetMapping("/hog")
    public String hog(@RequestParam(defaultValue = "5000") long ms) throws InterruptedException {
        // This blocks the request-handling thread on purpose.
        Thread.sleep(ms);
        return "Slept " + ms + "ms on thread=" + Thread.currentThread().getName();
    }

    /**
     * Async variant to demonstrate/enforce the global timeout configured via:
     *   spring.mvc.async.request-timeout=5s
     */
    @GetMapping("/hog-async")
    public Callable<String> hogAsync(@RequestParam(defaultValue = "5000") long ms) {
        return () -> {
            Thread.sleep(ms);
            return "Slept " + ms + "ms on thread=" + Thread.currentThread().getName();
        };
    }

    /**
     * Quick runtime check.
     * When virtual threads are enabled, this should often return isVirtual=true.
     */
    @GetMapping("/thread")
    public ResponseEntity<Map<String, Object>> threadInfo() {
        Thread t = Thread.currentThread();
        return ResponseEntity.ok(Map.of(
                "name", t.getName(),
                "isVirtual", t.isVirtual(),
                "isDaemon", t.isDaemon()
        ));
    }

    /**
     * Exact Tomcat connector thread pool counts from JMX.
     * These numbers correspond to the pool configured via server.tomcat.threads.*
     * and are different from total JVM live threads.
     */
    @GetMapping("/tomcat-threads")
    public ResponseEntity<Map<String, Map<String, Integer>>> tomcatThreads() {
        return ResponseEntity.ok(blockingIOService.getTomcatThreadPoolMetrics());
    }

    @GetMapping("/use-cpu/{num}")
    public ResponseEntity<String> useCpu(@PathVariable int num) {
        log.info("use-cpu..");
        return ResponseEntity.ok(blockingIOService.useCpu(num));

    }

    @GetMapping("/use-cpu-async/{num}")
    public ResponseEntity<String> useCpuAsync(@PathVariable int num) {
        log.info("use-cpu-async...");
        return ResponseEntity.ok(blockingIOService.useCpuUsingExecutor(num));
    }

    @GetMapping("/use-cpu-rate-limiting/{num}")
    public ResponseEntity<String> useCpuRateLimiting(@PathVariable int num) {
        log.info("use-cpu-rate-limiting...");
        return ResponseEntity.ok(blockingIOService.useCpuRateLimiting(num));
    }

    @GetMapping("/occupy-heap")
    public ResponseEntity<String> occupyHeap(){
        return ResponseEntity.ok(blockingIOService.occupyHeap());
    }

    @GetMapping("/info")
    public ResponseEntity<String> getTomcatThreadInfo(){
        return ResponseEntity.ok(blockingIOService.getTomcatThreadInfo());
    }
}
