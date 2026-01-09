package com.omidmohebbise.springboot3.http;

import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.service.annotation.GetExchange;
import org.springframework.web.service.annotation.HttpExchange;

@HttpExchange(url = "/omid-web")
public interface UserClient {

    @GetExchange("/{path}")
    String getUserById(@PathVariable
                        String path);
}
