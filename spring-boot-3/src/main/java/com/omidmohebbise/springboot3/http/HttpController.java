package com.omidmohebbise.springboot3.http;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HttpController {

    private final UserClient userClient;

    public HttpController(UserClient userClient) {
        this.userClient = userClient;
    }

    @GetMapping("/omid-web")
    public String omidWeb() {
        var result = userClient.getUserById("omid-web");
        System.out.println(result);
        return result;
    }

}
