package GYM.MEMBERSHIP.Service;

import GYM.MEMBERSHIP.ModelClasses.Customer;
import GYM.MEMBERSHIP.ModelClasses.Membership;
import GYM.MEMBERSHIP.ModelClasses.MembershipEnum;
import GYM.MEMBERSHIP.Repository.CustomerRepository;
import GYM.MEMBERSHIP.Repository.MembershipRepository;

public class MembershipService {

    private final MembershipRepository membershipRepository;
    private final CustomerRepository customerRepository; 

    public MembershipService(MembershipRepository membershipRepository, CustomerRepository customerRepository){
        this.membershipRepository = membershipRepository;
        this.customerRepository = customerRepository;
    }

    public Membership createMembership(long id){
        Customer customer = customerRepository.findById(id).orElseThrow(() -> new RuntimeException("customer not found"));
        Membership membership = new Membership();
        // link the membership to the customer
        customer.setMembership(membership);

        membershipRepository.save(membership);
        return membership;
    }

    public Membership findById(long id){
        return membershipRepository.findById(id)
            .orElseThrow(() -> new RuntimeException("membership not found"));
    }

    public Membership changeStatus(long id,boolean status){
        Membership membership = membershipRepository.findById(id).orElseThrow(() -> new RuntimeException("customer not found"));
        membership.setStatus(status);
        membershipRepository.save(membership);
        return membership;

    }

        public Membership changetype(long id,MembershipEnum type){
        Membership membership = membershipRepository.findById(id).orElseThrow(() -> new RuntimeException("customer not found"));
        // it shouldnt allow the type to change but not the status
        if (membership.isStatus() == false && type != MembershipEnum.none){
            changeStatus(id, true);
        }
       

        // reset to false for type
        if (membership.isStatus() == true && type == MembershipEnum.none){
            changeStatus(id, false);
        }

         membership.setType(type);
        
        membershipRepository.save(membership);
        return membership;

    }
    
}
