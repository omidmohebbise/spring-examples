package com.omidmohebbise.sb.webclient.webclient;

import com.omidmohebbise.sb.webclient.webclient.dto.ResponseDTO;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;

@Service
public class GetFakeUserService implements CommandLineRunner {

    public void getFakeUser(){
        WebClient webClient = WebClient.create("https://reqres.in/api/users?page=2");
        var result = webClient
                .get()
                .retrieve()
                .toEntity(ResponseDTO.class)
                .block();
        System.out.println(result.getBody().getData().toString());
    }
    public ResponseDTO getFakeUserAsFlux(){
        WebClient webClient = WebClient.create("https://reqres.in/api/users?page=2");
        var result = webClient
                .get()
                .retrieve()
                .bodyToFlux(ResponseDTO.class);
//        result.subscribe(responseDTO -> {
//            responseDTO.getData();
//        });

        return result.blockLast();

    }
    @Override
    public void run(String... args) throws Exception {
        //getFakeUser();
        System.out.println(getFakeUserAsFlux());
    }
}
