package GYM.MEMBERSHIP.Service;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import GYM.MEMBERSHIP.ModelClasses.Customer;
import GYM.MEMBERSHIP.Repository.CustomerRepository;

@Service
public class CustomerService {
    // building the logic for how the customer interacts with the database
    // tells the system how to create a customer or change its details.
    private final CustomerRepository customerRepository;
    private final PasswordEncoder passwordEncoder;
    public CustomerService( CustomerRepository customerRepository, PasswordEncoder passwordEncoder){
        this.customerRepository= customerRepository;
        this.passwordEncoder = passwordEncoder;
    }

    public Customer createCustomer( String name,int age, String username,String password ){
        Customer customer = new Customer();
        customer.setName(name);
        customer.setAge(age);
        customer.setUsername(username);
        customer.setPassword(passwordEncoder.encode(password));
        customerRepository.save(customer);
        return customer;
    }

    public Customer findById(Long id){
        return customerRepository.findById(id)
            .orElseThrow(() -> new RuntimeException("Customer not found"));
    }

    public Customer changeUsername(String username, long id){
        Customer customer = findById(id);
        customer.setUsername(username);
        customerRepository.save(customer);
        return customer;
    }
    public Customer changepassword(String password, long id){
        Customer customer = findById(id);
        customer.setPassword(passwordEncoder.encode(password));
        customerRepository.save(customer);
        return customer;
    }
    
}
