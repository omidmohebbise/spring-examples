package com.omidmohebbise.springboot4xfw7x.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jms.core.JmsClient;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.retry.annotation.EnableRetry;

@Configuration
@EnableRetry
public class Framework7FeatureConfig {

    @Bean
    JmsClient jmsClient(JmsTemplate jmsTemplate) {
        return JmsClient.create(jmsTemplate);
    }
}

