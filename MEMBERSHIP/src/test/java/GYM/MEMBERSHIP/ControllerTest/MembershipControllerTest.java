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
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;

import GYM.MEMBERSHIP.ModelClasses.Customer;
import GYM.MEMBERSHIP.ModelClasses.Membership;
import GYM.MEMBERSHIP.ModelClasses.MembershipEnum;
import GYM.MEMBERSHIP.Repository.CustomerRepository;
import GYM.MEMBERSHIP.Repository.MembershipRepository;
import tools.jackson.databind.ObjectMapper;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.MOCK)
@AutoConfigureMockMvc
public class MembershipControllerTest {
    @Autowired
    private MockMvc mockMvc;
    @Autowired
    private ObjectMapper objectMapper;
    @Autowired
    private MembershipRepository membershipRepository;
    @Autowired
    private CustomerRepository customerRepository;

    private Customer customer;
    private Membership membership;

    @BeforeEach
    public void setUp(){
        membership = new Membership();
        membership.setType(MembershipEnum.special);
        membershipRepository.save(membership);

        customer = new Customer();
        customer.setMembership(membership);
        customerRepository.save(customer);
    }

    @Test
    public void createMembershiptest() throws Exception{
        Map<String, String> params = new HashMap<>();
        params.put("id",String.valueOf(customer.getId()));    
        mockMvc.perform(
                        post("/membership/createmembership")
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(objectMapper.writeValueAsString(params))
                    .accept(MediaType.APPLICATION_JSON))
            .andExpect(status().isOk());
    }
    @Test 
    public void findMembership() throws Exception{
         Map<String, String> params = new HashMap<>();
        params.put("id", String.valueOf(membership.getId()));
        MvcResult action = mockMvc.perform(
                        get("/membership/findmembership")
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(objectMapper.writeValueAsString(params))
                    .accept(MediaType.APPLICATION_JSON))
            .andExpect(status().isOk())
            .andReturn();
        Membership result = objectMapper.readValue(action.getResponse().getContentAsString(), Membership.class);
        assertEquals(result.getType(), membership.getType());
    }

     @Test
    public void setStatus() throws Exception{
        Map<String, String> params = new HashMap<>();
        params.put("id", String.valueOf(membership.getId()));
        params.put("status","true");
        mockMvc.perform(
                        post("/membership/changestatus")
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(objectMapper.writeValueAsString(params))
                    .accept(MediaType.APPLICATION_JSON))
            .andExpect(status().isOk())
            .andExpect(jsonPath("$.status").value("true"));
    }

    
     @Test
    public void setType() throws Exception{
        Map<String, String> params = new HashMap<>();
        params.put("id", String.valueOf(membership.getId()));
        params.put("type","standard");
        mockMvc.perform(
                        post("/membership/changetype")
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(objectMapper.writeValueAsString(params))
                    .accept(MediaType.APPLICATION_JSON))
            .andExpect(status().isOk())
            .andExpect(jsonPath("$.type").value("standard"));
    }
}
