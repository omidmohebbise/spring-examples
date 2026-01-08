package com.omidmohebbise.springthroughput;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.test.context.bean.override.mockito.MockitoBean;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.asyncDispatch;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.request;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(SimpleController.class)
class SimpleControllerTest {

    @Autowired
    MockMvc mockMvc;

    @MockitoBean
    BlockingIOService blockingIOService;

    @Test
    void hog_works() throws Exception {
        mockMvc.perform(get("/api/hog").param("ms", "10"))
                .andExpect(status().isOk())
                .andExpect(content().string(org.hamcrest.Matchers.containsString("Slept 10ms")));
    }

    @Test
    void hogAsync_executesAsAsync() throws Exception {
        MvcResult result = mockMvc.perform(get("/api/hog-async").param("ms", "10"))
                .andExpect(request().asyncStarted())
                .andReturn();

        mockMvc.perform(asyncDispatch(result))
                .andExpect(status().isOk())
                .andExpect(content().string(org.hamcrest.Matchers.containsString("Slept 10ms")));
    }
}
