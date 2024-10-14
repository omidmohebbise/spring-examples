package com.example.redis;

import omid.spring.example.springexample.redis.Receiver;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.data.redis.listener.PatternTopic;
import org.springframework.data.redis.listener.RedisMessageListenerContainer;
import org.springframework.data.redis.listener.adapter.MessageListenerAdapter;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.concurrent.CountDownLatch;


//@SpringBootApplication
// after add security library then it is need to use security configuration.
//@ComponentScan("omid.spring.example.springexample.security")
public class RunSpring {
    private static final Logger LOGGER = LoggerFactory.getLogger(RunSpring.class);


    public  static   void main(String[] args) throws InterruptedException {
        ConfigurableApplicationContext contex =  SpringApplication.run(RunSpring.class, args);
    }

    @Autowired
    private ApplicationContext context;

    @RestController
    public class SimpleController{

        @RequestMapping("/test")
        public String getHelloWorld(){

            StringRedisTemplate template = context.getBean(StringRedisTemplate.class);
            CountDownLatch latch = context.getBean(CountDownLatch.class);

            LOGGER.info("Sending message...");

            Thread t = new Thread(new Runnable() {
                @Override
                public void run() {
                    for (int i = 0 ;  i < 100 ; i++) {
                        template.convertAndSend("chat", i + " => Hello from Redis!");
                        try {
                            Thread.sleep(100);
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }

                    }

                }
            });
            t.start();

            try {
                latch.await();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }


            return "hello world 1";
        }
    }

    ///////////////////////////////////////////////////////////////


    @Bean
    RedisMessageListenerContainer container(RedisConnectionFactory connectionFactory,
                                            MessageListenerAdapter listenerAdapter) {

        RedisMessageListenerContainer container = new RedisMessageListenerContainer();
        container.setConnectionFactory(connectionFactory);
        container.addMessageListener(listenerAdapter, new PatternTopic("chat"));

        return container;
    }

    @Bean
    MessageListenerAdapter listenerAdapter(omid.spring.example.springexample.redis.Receiver receiver) {
        return new MessageListenerAdapter(receiver, "receiveMessage");
    }

    @Bean
    omid.spring.example.springexample.redis.Receiver receiver(CountDownLatch latch) {
        return new Receiver(latch);
    }

    @Bean
    CountDownLatch latch() {
        return new CountDownLatch(1);
    }

    @Bean
    StringRedisTemplate template(RedisConnectionFactory connectionFactory) {
        return new StringRedisTemplate(connectionFactory);
    }



}
