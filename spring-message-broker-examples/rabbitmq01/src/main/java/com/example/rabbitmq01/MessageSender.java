package com.example.rabbitmq01;

import java.util.concurrent.TimeUnit;

import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.boot.CommandLineRunner;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@RestController
public class MessageSender {//implements CommandLineRunner {

    private final RabbitTemplate rabbitTemplate;
    private final Receiver receiver;

    public MessageSender(Receiver receiver, RabbitTemplate rabbitTemplate) {
        this.receiver = receiver;
        this.rabbitTemplate = rabbitTemplate;
    }

    @Scheduled(fixedDelay = 10000)
    @RequestMapping("/heyrabi")
    public String getHelloWorld(){
        if(rabbitTemplate != null){
            System.out.println("Sending message...");
            rabbitTemplate.convertAndSend(RabitmqSenderApplication.topicExchangeName, "foo.bar.baz", "Hello from RabbitMQ!");
            try {
                receiver.getLatch().await(10000, TimeUnit.MILLISECONDS);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }else{
            return "rabbitTemplate = null";
        }

     return "hellooooooooo woooorld";
    }

}