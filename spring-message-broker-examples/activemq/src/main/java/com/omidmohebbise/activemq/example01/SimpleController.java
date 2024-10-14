package com.omidmohebbise.activemq.example01;

import org.apache.activemq.command.ActiveMQTextMessage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jms.annotation.JmsListener;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.jms.JMSException;
import javax.jms.Message;

@RestController
public class SimpleController {
    @GetMapping("/hello")
    public String getHello(){
        return "Hello";
    }
    @Autowired
    private JmsTemplate jmsTemplate;

    @GetMapping("/sendhello")
    public String sendHello(){
        jmsTemplate.convertAndSend("testQueue","hello");
        return "message sent";
    }

    @JmsListener(destination = "testQueue")
    public void sendHello(String message){
        System.out.println(message);
        System.out.println( "message received");
    }
    ////synchronise
    @GetMapping("/ssendhello")
    public String sendHelloAndReceive() throws JMSException {
        jmsTemplate.convertAndSend("queue1","hello");
        ActiveMQTextMessage response = (ActiveMQTextMessage) jmsTemplate.receive("queue1Response");
        //String str = response.getBody(String.class);
        return response.getText();
    }

    @JmsListener(destination = "queue1")
    @SendTo("queue1Response")
    public String test1Response(String message){
        System.out.println(message);
        System.out.println( "message received");
        //jmsTemplate.convertAndSend("queue1Response","response: hello received");
        return "response: hello received";
    }



}
