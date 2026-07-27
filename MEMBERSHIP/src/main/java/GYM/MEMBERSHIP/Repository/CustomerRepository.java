package GYM.MEMBERSHIP.Repository;

import org.springframework.data.jpa.repository.JpaRepository;

import GYM.MEMBERSHIP.ModelClasses.Customer;

public interface CustomerRepository extends JpaRepository<Customer, Long> {
    
    
}
