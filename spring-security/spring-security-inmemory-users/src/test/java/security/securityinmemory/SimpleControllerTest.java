package security.securityinmemory;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@AutoConfigureMockMvc
class SimpleControllerTest {

    @Autowired
    MockMvc mockMvc;
    @Test
    void getSecuredHelloWorld_Validate_Access() throws Exception {
        var status = mockMvc.perform(MockMvcRequestBuilders.get("/secured")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.status().isUnauthorized());
    }
    @Test
    @WithMockUser(username = "user", password = "123", roles = "USER")
    public void testAuthenticatedUser() throws Exception {
        // Perform a GET request to your secured endpoint
        mockMvc.perform(MockMvcRequestBuilders.get("/secured"))
                // Expect a status code 200 OK
                .andExpect(MockMvcResultMatchers.status().isOk());
    }

}