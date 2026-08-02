package GYM.MEMBERSHIP.Service;
import org.springframework.stereotype.Service;
import GYM.MEMBERSHIP.ModelClasses.Customer;
import GYM.MEMBERSHIP.Repository.CustomerRepository;

@Service
public class CustomerService {
    private final CustomerRepository customerRepository;
    public CustomerService( CustomerRepository customerRepository){
        this.customerRepository= customerRepository;
    }

    public Customer createCustomer( String name,int age, String username,String password ){
        Customer customer = new Customer();
        customer.setName(name);
        customer.setAge(age);
        customer.setUsername(username);
        customer.setPassword(password);
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
        customer.setPassword(password);
        customerRepository.save(customer);
        return customer;
    }
    
}
