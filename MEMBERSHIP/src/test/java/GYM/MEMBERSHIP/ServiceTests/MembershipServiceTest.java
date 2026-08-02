package GYM.MEMBERSHIP.ServiceTests;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

import java.util.Optional;
import java.util.PrimitiveIterator;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import GYM.MEMBERSHIP.ModelClasses.Customer;
import GYM.MEMBERSHIP.ModelClasses.Membership;
import GYM.MEMBERSHIP.ModelClasses.MembershipEnum;
import GYM.MEMBERSHIP.Repository.CustomerRepository;
import GYM.MEMBERSHIP.Repository.MembershipRepository;
import GYM.MEMBERSHIP.Service.CustomerService;
import GYM.MEMBERSHIP.Service.MembershipService;

@ExtendWith(MockitoExtension.class)
public class MembershipServiceTest {

    @Mock private MembershipRepository membershipRepository;
    @Mock private CustomerRepository customerRepository;
    
    private MembershipService membershipService;
    private Membership membership;
    private Customer customer;


    @BeforeEach
    public void setUP(){
        membership = new Membership();
        customer = new Customer();
        membershipService = new MembershipService(membershipRepository, customerRepository);
    }

    @Test
    public void createMembership(){
        when(customerRepository.findById(1L)).thenReturn(Optional.of(customer));
        when(membershipRepository.save(any(Membership.class))).thenReturn(membership);
        Membership member = membershipService.createMembership(1l);
        assertEquals(membership.getType(), member.getType());
        assertEquals(membership.isStatus(), member.isStatus());
        assertEquals(membership.getDateStarted(), member.getDateStarted());
        assertEquals(membership.getDateEnded(), member.getDateEnded());       
    }
    @Test
    public void changeStatus(){
        when(membershipRepository.findById(1L)).thenReturn(Optional.of(membership));
        membershipService.changeStatus(1L, true);
        assertEquals(true,  membership.isStatus());
    }

    @Test
    public void changeType(){
        when(membershipRepository.findById(1L)).thenReturn(Optional.of(membership));
        membershipService.changetype(1L, MembershipEnum.special);
        assertEquals( MembershipEnum.special,  membership.getType());
    }

    
}
