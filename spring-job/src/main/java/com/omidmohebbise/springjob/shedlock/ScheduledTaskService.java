package com.omidmohebbise.springjob.shedlock;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import net.javacrumbs.shedlock.spring.annotation.SchedulerLock;
import org.springframework.core.env.Environment;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import java.util.concurrent.atomic.AtomicInteger;


@Slf4j
@Service
@RequiredArgsConstructor
public class ScheduledTaskService {

    private final Environment environment;
    private final AtomicInteger counter = new AtomicInteger(0);
    private final AtomicInteger secondCounter = new AtomicInteger(0);

    @Scheduled(fixedRate = 1000)
    @SchedulerLock(name = "ScheduledTaskService_performTask", lockAtMostFor = "5m", lockAtLeastFor = "2s")
    public void performTask() {
        log.atInfo()
                .addArgument(environment.getProperty("server.port"))
                .addArgument(secondCounter.getAndIncrement())
                .addArgument(counter.getAndIncrement())
                .log("App with port: [{}] => counter [{}] \\ [{}]");
    }

    @Scheduled(fixedRate = 1000)
    public void secondCounter() {
        secondCounter.incrementAndGet();
    }


}
