package com.omidmohebbise.springthroughput;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

@EnableScheduling
@SpringBootApplication
public class SpringThroughputApplication {

	public static void main(String[] args) {
		SpringApplication.run(SpringThroughputApplication.class, args);
	}

}
