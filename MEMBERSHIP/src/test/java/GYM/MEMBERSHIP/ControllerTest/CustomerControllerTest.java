package GYM.MEMBERSHIP.ControllerTest;

import java.util.HashMap;
import java.util.Map;


import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.webmvc.test.autoconfigure.AutoConfigureMockMvc;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import GYM.MEMBERSHIP.ModelClasses.Customer;
import GYM.MEMBERSHIP.Repository.CustomerRepository;
import tools.jackson.databind.ObjectMapper;


@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.MOCK)
@AutoConfigureMockMvc
public class CustomerControllerTest {

    @Autowired
    private MockMvc mockMvc;
    @Autowired
    private ObjectMapper objectMapper;

    private CustomerRepository customerRepository;

    private Customer customer;

    @BeforeEach
    void setUp(){
        customer = new Customer("user", 20, "user1", "password1");
        customerRepository.save(customer);
    }
    @Test
    public void createCustomerTest() throws Exception{
        Map<String, String> params = new HashMap<>();
        params.put("name","random1");
        params.put("age","20");
        params.put("username","randomuser");
        params.put("password","password1");
        
        mockMvc.perform(
                        post("/customer/createCustomer")
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(objectMapper.writeValueAsString(params))
                    .accept(MediaType.APPLICATION_JSON))
            .andExpect(status().isOk())
            .andExpect(jsonPath("$.name").value("user"))
            .andExpect(jsonPath("$.username").value("user1"));
    }


}

