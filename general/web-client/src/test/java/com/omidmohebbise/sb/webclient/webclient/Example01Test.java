package com.omidmohebbise.sb.webclient.webclient;

import com.omidmohebbise.sb.webclient.webclient.dto.ResponseDTO;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.web.reactive.function.client.WebClient;

@ExtendWith(MockitoExtension.class)
class Example01Test {

    GetFakeUserService getFakeUserService;

    @BeforeEach
    void beforeAll() {
        getFakeUserService = new GetFakeUserService();
    }

    public void getFakeUserAsFlux(){

        getFakeUserService.getFakeUserAsFlux();

    }

}