package GYM.MEMBERSHIP.Config;

import GYM.MEMBERSHIP.ModelClasses.Customer;

public class CustomerResponse {
    // this will be the reponse
    // it will remove any mention of a password  from the response so it is unable to be seen, even the hash
    // 

    public Long id;
    public String name;
    public int age;
    public String username;

    public CustomerResponse(Customer customer) {
        this.id = customer.getId();
        this.name = customer.getName();
        this.age = customer.getAge();
        this.username = customer.getUsername();
    }
}