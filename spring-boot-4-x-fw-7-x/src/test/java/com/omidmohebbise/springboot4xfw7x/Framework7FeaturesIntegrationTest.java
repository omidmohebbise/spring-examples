package com.omidmohebbise.springboot4xfw7x;

import com.omidmohebbise.springboot4xfw7x.resilience.ResilienceService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.test.web.servlet.MockMvc;

import static org.assertj.core.api.Assertions.assertThat;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
class Framework7FeaturesIntegrationTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ResilienceService resilienceService;

    @Autowired
    private JmsTemplate jmsTemplate;

    @Test
    void routesToVersion1UsingNativeApiVersionParameter() throws Exception {
        mockMvc.perform(get("/api/products/sku-1").param("api-version", "1"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.version").value("v1"));
    }

    @Test
    void routesToVersion2UsingNativeApiVersionParameter() throws Exception {
        mockMvc.perform(get("/api/products/sku-1").param("api-version", "2"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.version").value("v2"));
    }

    @Test
    void resilienceRetriesUntilThirdAttempt() {
        String response = resilienceService.checkSku("sku-123");
        assertThat(response).contains("attempt 3");
    }

    @Test
    void fluentJmsClientSendsToQueue() {
        jmsTemplate.convertAndSend("orders.events", "ping");
        Object payload = jmsTemplate.receiveAndConvert("orders.events");
        assertThat(payload).isEqualTo("ping");
    }
}

