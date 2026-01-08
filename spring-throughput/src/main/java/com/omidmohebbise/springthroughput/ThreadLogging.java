package com.omidmohebbise.springthroughput;

import lombok.extern.slf4j.Slf4j;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.lang.management.ManagementFactory;
import java.lang.management.ThreadMXBean;
import java.util.Map;
import java.util.stream.Collectors;

@Slf4j
@Component
public class ThreadLogging {

    private final ThreadMXBean threadMxBean = ManagementFactory.getThreadMXBean();

    @Scheduled(fixedDelayString = "5000")
    public void logThreads() {
        int live = threadMxBean.getThreadCount();
        int peak = threadMxBean.getPeakThreadCount();
        int daemon = threadMxBean.getDaemonThreadCount();

        // group by thread name prefix to see where they come from
        Map<String, Long> threadsCount = Thread.getAllStackTraces().keySet().stream()
                .collect(Collectors.groupingBy(t -> prefix(t.getName()), Collectors.counting()));

        log.info("Threads: live={}, daemon={}, peak={}", live, daemon, peak);
        log.info("Threads by prefix: {}", threadsCount);

    }

    private String prefix(String name) {
        // crude but useful grouping
        int i = name.indexOf('-');
        return i > 0 ? name.substring(0, i) : name;
    }
}
