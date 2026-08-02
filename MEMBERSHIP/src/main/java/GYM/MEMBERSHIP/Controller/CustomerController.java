package GYM.MEMBERSHIP.Controller;

import java.util.Map;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import GYM.MEMBERSHIP.Config.CustomerResponse;
import GYM.MEMBERSHIP.ModelClasses.Customer;
import GYM.MEMBERSHIP.Service.CustomerService;

@RestController
@RequestMapping("/customer")
public class CustomerController {
    // this handles actual responses from the user, 
    // the controller parses through inputs and uses the service to generate http responses.
    // controlls how the frontend will interact with the backend
    private CustomerService customerService;
    public CustomerController(CustomerService customerService){
        this.customerService = customerService;
    }

    @PostMapping("/createcustomer")
    public ResponseEntity<CustomerResponse> createCustomer(@RequestBody Map<String, String> params){
        String name = String.valueOf(params.get("name"));
        int age = Integer.valueOf(params.get("age"));
        String username = String.valueOf(params.get("username"));
        String password = String.valueOf(params.get("password"));

        Customer customer = customerService.createCustomer( name, age, username, password);
        return ResponseEntity.ok(new CustomerResponse(customer));
           
    }

    @GetMapping("/getcustomer")
    public ResponseEntity<CustomerResponse> getCustomer(@RequestBody Map<String, String> params){
        Long id = Long.valueOf(params.get("id"));
        Customer customer = customerService.findById(id);
        return ResponseEntity.ok(new CustomerResponse(customer));
    }
    @PostMapping("/changeusername")
    public ResponseEntity<CustomerResponse> changeUsername(@RequestBody Map<String, String> params){
        String username = String.valueOf(params.get("username"));
        Long id = Long.valueOf(params.get("id"));
        Customer customer  = customerService.changeUsername(username, id);
        return ResponseEntity.ok(new CustomerResponse(customer));
        
    }
    @PostMapping("/changepassword")
    public ResponseEntity<Customer> changePassword(@RequestBody Map<String, String> params){
        String Password = String.valueOf(params.get("password"));
        Long id = Long.valueOf(params.get("id"));
        Customer customer  = customerService.changepassword(Password, id);
        // only in the changePassword will the original be visible because we need
        // to check the password
        return ResponseEntity.ok(customer);
        
    }

    
    
}
