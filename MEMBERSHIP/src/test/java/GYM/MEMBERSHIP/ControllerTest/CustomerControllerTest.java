package GYM.MEMBERSHIP.ControllerTest;

import java.util.HashMap;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.webmvc.test.autoconfigure.AutoConfigureMockMvc;
import org.springframework.http.MediaType;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;

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

    @Autowired
    private CustomerRepository customerRepository;

    private Customer customer;
    private PasswordEncoder passwordEncoder = new BCryptPasswordEncoder();

    

    @BeforeEach
    void setUp(){
        customer = new Customer("user", 20, "user1", "password1");
        customerRepository.save(customer);
    }
    @Test
    public void createCustomerTest() throws Exception{
        // pass in the params that the controller will reaad
        Map<String, String> params = new HashMap<>();
        params.put("name","random1");
        params.put("age","20");
        params.put("username","randomuser");
        params.put("password","password1");
        // send it to the correct path
        mockMvc.perform(
                        post("/customer/createcustomer")
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(objectMapper.writeValueAsString(params))
                    .accept(MediaType.APPLICATION_JSON))
            // check the response is as intended
            .andExpect(status().isOk())
            .andExpect(jsonPath("$.name").value("random1"))
            .andExpect(jsonPath("$.username").value("randomuser"));
    }
    @Test
    public void getCustomer() throws Exception{
        Map<String, String> params = new HashMap<>();
        params.put("id", String.valueOf(customer.getId()));
        MvcResult action = mockMvc.perform(
                        get("/customer/getcustomer")
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(objectMapper.writeValueAsString(params))
                    .accept(MediaType.APPLICATION_JSON))
            .andExpect(status().isOk())
            .andReturn();
        // alternate is to get the returned object and compare the values inside the returned object
        Customer result = objectMapper.readValue(action.getResponse().getContentAsString(), Customer.class);
        assertEquals(customer.getName(), result.getName());
        assertEquals(customer.getAge(), result.getAge());
        assertEquals(customer.getUsername(), result.getUsername());
    }

    @Test
    public void setUsername() throws Exception{
        // similar to createcustomer using params that the controller reads and compares its result.
        Map<String, String> params = new HashMap<>();
        params.put("id", String.valueOf(customer.getId()));
        params.put("username","changedvalue");
        mockMvc.perform(
                        post("/customer/changeusername")
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(objectMapper.writeValueAsString(params))
                    .accept(MediaType.APPLICATION_JSON))
            .andExpect(status().isOk())
            .andExpect(jsonPath("$.username").value("changedvalue"));
    }

    
    @Test
    public void setPassword() throws Exception{
         // similar to createcustomer using params that the controller reads and compares its result.
        String new_password = "changedvalue";
        Map<String, String> params = new HashMap<>();
        params.put("id", String.valueOf(customer.getId()));
        params.put("password",new_password);
        MvcResult action = mockMvc.perform(
                        post("/customer/changepassword")
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(objectMapper.writeValueAsString(params))
                    .accept(MediaType.APPLICATION_JSON))
            .andExpect(status().isOk())
            .andReturn();
        Customer result = objectMapper.readValue(action.getResponse().getContentAsString(), Customer.class);
        boolean isCorrect = passwordEncoder.matches(new_password, result.getPassword());

        assertEquals(isCorrect, true);
    }


}

