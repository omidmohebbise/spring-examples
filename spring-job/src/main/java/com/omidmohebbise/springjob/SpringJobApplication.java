package com.omidmohebbise.springjob;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@EnableScheduling
public class SpringJobApplication {

    public static void main(String[] args) {
        SpringApplication.run(SpringJobApplication.class, args);
    }

}
