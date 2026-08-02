package GYM.MEMBERSHIP.ServiceTests;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.when;

import java.util.Optional;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import GYM.MEMBERSHIP.ModelClasses.Customer;
import GYM.MEMBERSHIP.Repository.CustomerRepository;
import GYM.MEMBERSHIP.Service.CustomerService;

@ExtendWith(MockitoExtension.class)
public class CustomerServiceTest {
 
    @Mock private CustomerRepository customerRepository;
    private CustomerService customerService;
    private Customer customer;

    @BeforeEach
    public void setup(){
        customerService = new CustomerService(customerRepository);

        customer = new Customer("user", 20, "user1", "password1");
       
    }

    @Test
    public void createCustomer(){
         when(customerRepository.save(any(Customer.class))).thenReturn(customer);
        Customer result = customerService.createCustomer("user", 20, "user1", "password1");

        assertEquals(customer.getName(), result.getName());
        assertEquals(customer.getAge() ,result.getAge());
        assertEquals(customer.getUsername(), result.getUsername());
        assertEquals(customer.getPassword(), result.getPassword());
    }
    @Test
    public void findBYid(){
        when(customerRepository.findById(anyLong())).thenReturn(Optional.of(customer));
        Customer result = customerService.findById(1L);
        assertEquals(customer, result);
    }
    @Test
    public void findBYidFail(){
        when(customerRepository.findById(99L)).thenReturn(Optional.empty());
        assertThrows(RuntimeException.class, () -> customerService.findById(99L));
    }
    @Test
    public void setUsername(){
        when(customerRepository.findById(anyLong())).thenReturn(Optional.of(customer));
        customerService.changeUsername("user2", 1l);

        assertEquals("user2", customer.getUsername());
        
    }
    @Test
     public void setPassword(){
        when(customerRepository.findById(anyLong())).thenReturn(Optional.of(customer));
        customerService.changepassword("password2", 1l);

        assertEquals("password2", customer.getPassword());
        
    }

}