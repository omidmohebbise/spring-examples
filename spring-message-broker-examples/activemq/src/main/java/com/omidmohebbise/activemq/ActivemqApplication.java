package com.omidmohebbise.activemq;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.jms.annotation.EnableJms;

@SpringBootApplication
//@EnableJms
public class ActivemqApplication {
	public static void main(String[] args) {
		SpringApplication.run(ActivemqApplication.class, args);
	}

//	@Bean
//	public BrokerService broker() throws Exception {
//		BrokerService broker = new BrokerService();
//		broker.addConnector("tcp://localhost:61616");
//		return broker;
//	}




}
