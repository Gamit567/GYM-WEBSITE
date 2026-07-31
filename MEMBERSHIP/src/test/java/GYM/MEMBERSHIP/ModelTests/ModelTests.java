package GYM.MEMBERSHIP.ModelTests;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.time.LocalDate;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import GYM.MEMBERSHIP.ModelClasses.Customer;
import GYM.MEMBERSHIP.ModelClasses.Membership;

public class ModelTests {
    public Customer customer;
    public Membership membership;

    @BeforeEach
    public void setup(){
        customer = new Customer();
        membership = new Membership();
    }

    @Test
    public void CustomerTest(){
        customer.setName("user2");
        customer.setAge(25);
        customer.setUsername("user2username");
        customer.setPassword("newpassword");
        customer.setMembership(membership);

        assertEquals("user2", customer.getName());
        assertEquals(25, customer.getAge());
        assertEquals("user2username", customer.getUsername());
        assertEquals("newpassword", customer.getPassword());
        assertEquals(membership, customer.getMembership());
    }

    public void MembershipTest(){
        Membership membership = new Membership();
        membership.setStatus(true);
        membership.setDateStarted(LocalDate.now());

    }

    
}
