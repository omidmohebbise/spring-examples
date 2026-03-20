package com.omidmohebbise.rag;


import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.test.web.server.LocalServerPort;

import static org.assertj.core.api.Assertions.assertThat;


@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class ChatControllerTest {


    @LocalServerPort
    private int port;

    @Autowired
    private TestRestTemplate restTemplate;

    @Test
    void shouldReturnDifferentAdviceForDifferentAges() {
        // age 7 → 5-10 group
        String responseChild = restTemplate.getForObject(
                "http://localhost:" + port + "/life-principles?age=7&question=How should I behave with friends?",
                String.class
        );

        // age 27 → 25-30 group
        String responseAdult = restTemplate.getForObject(
                "http://localhost:" + port + "/life-principles?age=27&question=How should I think about money?",
                String.class
        );

        assertThat(responseChild).isNotNull();
        assertThat(responseAdult).isNotNull();

        // loose assertions (LLM output is not deterministic)
        assertThat(responseChild.toLowerCase())
                .containsAnyOf("friends", "kind", "share", "respect");

        assertThat(responseAdult.toLowerCase())
                .containsAnyOf("money", "save", "invest", "discipline");

        // sanity: they should not be identical
        assertThat(responseChild).isNotEqualTo(responseAdult);
    }
}
