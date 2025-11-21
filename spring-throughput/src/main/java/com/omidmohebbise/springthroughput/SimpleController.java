package com.omidmohebbise.springthroughput;

import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
@CrossOrigin("*")
@RequestMapping("/api")
public class SimpleController {

    private final BlockingIOService blockingIOService;

    public SimpleController(BlockingIOService blockingIOService) {
        this.blockingIOService = blockingIOService;
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
