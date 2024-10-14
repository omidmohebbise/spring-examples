package com.javadeveloper.hal.halbrowser;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@SpringBootApplication
public class HalBrowserApplication {
    public static void main(String[] args) {
        SpringApplication.run(HalBrowserApplication.class, args);
    }
}
