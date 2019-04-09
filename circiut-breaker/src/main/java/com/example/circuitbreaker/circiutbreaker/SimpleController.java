package com.example.circuitbreaker.circiutbreaker;

import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class SimpleController {
    //@HystrixCommand(fallbackMethod = "doSomething")
    @RequestMapping
    public String getHelloFromCircuitBreaker(){
        return "hello world";
    }

    /*public String doSomething(){
        System.out.println("################ doSomething called.");
        return "hello world from circuit breaker";
    }*/
}
