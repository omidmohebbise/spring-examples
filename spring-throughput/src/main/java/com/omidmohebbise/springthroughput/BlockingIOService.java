package com.omidmohebbise.springthroughput;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import javax.management.MBeanServer;
import javax.management.ObjectName;
import java.lang.management.ManagementFactory;
import java.util.Set;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Semaphore;

@Slf4j
@Service
public class BlockingIOService {

    private static ExecutorService executor = Executors.newFixedThreadPool(10);
    private final Semaphore semaphore = new Semaphore(10);

    public String useCpuUsingExecutor(int num) {
        try {
            return "Fibonacci result: " + executor.submit(() -> fibonacci(num)).get().toString();
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        } catch (ExecutionException e) {
            throw new RuntimeException(e);
        }
    }

    public String useCpuRateLimiting(int num) {
        if (!semaphore.tryAcquire()) {
            return "Too many concurrent requests";
        }
        try {
            return "Fibonacci result: " + fibonacci(num);
        } finally {
            semaphore.release();
        }


    }


    public String useCpu(int num) {
        long result = fibonacci(num); // Adjust the number to increase/decrease CPU usage
        return "Fibonacci result: " + result;
    }

    private long fibonacci(int n) {
        log.atInfo().addArgument(n)
                .log("Calculating fibonacci for n = {}");
        if (n <= 1) {
            return n;
        }
        return fibonacci(n - 1) + fibonacci(n - 2);
    }

    public String occupyHeap() {
        int[] largeArray = new int[250000000];
        int[] largeArray1 = new int[250000000];
        int[] largeArray2 = new int[250000000];
        int[] largeArray3 = new int[250000000];
        int[] largeArray4 = new int[250000000];

        return """
                       array 1 : %d,
                       array 2 : %d,
                       array 3 : %d,
                       array 4 : %d,
                       array 5 : %d,
                
                """.formatted(largeArray.length, largeArray1.length, largeArray2.length, largeArray3.length, largeArray4.length);
    }

    public String getTomcatThreadInfo() {
        try {
            MBeanServer mBeanServer = ManagementFactory.getPlatformMBeanServer();
            Set<ObjectName> objectNames = mBeanServer.queryNames(new ObjectName("Tomcat:type=ThreadPool,*"), null);
            StringBuilder info = new StringBuilder();
            for (ObjectName objectName : objectNames) {
                int maxThreads = (Integer) mBeanServer.getAttribute(objectName, "maxThreads");
                int currentThreadCount = (Integer) mBeanServer.getAttribute(objectName, "currentThreadCount");
                int currentThreadsBusy = (Integer) mBeanServer.getAttribute(objectName, "currentThreadsBusy");
                info.append(String.format("Name: %s, Max Threads: %d, Current Thread Count: %d, Current Threads Busy: %d%n",
                        objectName.getKeyProperty("name"), maxThreads, currentThreadCount, currentThreadsBusy));
            }
            return info.toString();
        } catch (Exception e) {
            log.error("Failed to get Tomcat thread info", e);
            return "Error retrieving Tomcat thread info";
        }
    }

}
