package GYM.MEMBERSHIP.Controller;

import java.util.Map;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import GYM.MEMBERSHIP.ModelClasses.Membership;
import GYM.MEMBERSHIP.ModelClasses.MembershipEnum;
import GYM.MEMBERSHIP.Service.CustomerService;
import GYM.MEMBERSHIP.Service.MembershipService;

@RestController
@RequestMapping("/membership")
public class MembershipController {

    public MembershipService membershipService;
    public CustomerService customerService;

    public MembershipController(MembershipService membershipService,CustomerService customerService){
        this.membershipService = membershipService;
        this.customerService = customerService;
    }

    @PostMapping("/create")
    public ResponseEntity<Membership> createMembership(@RequestBody Map<String, String> params){
        Long id = Long.parseLong(params.get("id"));
        Membership membership = membershipService.createMembership(id);
        // create membership function handles the link between customer and membership
        return ResponseEntity.ok(membership); 
    }

    @GetMapping("/find")
    public ResponseEntity<Membership> findMembership(@RequestBody Map<String, String> params){
        Long id = Long.valueOf(params.get("id"));
        Membership membership = membershipService.findById(id);
        return ResponseEntity.ok(membership);
    }
    public ResponseEntity<Membership> changeStatus(@RequestBody Map<String, String> params){
        Long id = Long.valueOf(params.get("id"));
        boolean status = Boolean.valueOf(params.get("status"));
        Membership membership = membershipService.findById(id);
        membershipService.changeStatus(id, status);
        return ResponseEntity.ok(membership);

    }

    public ResponseEntity<Membership> changeType(@RequestBody Map<String, String> params){
        Long id = Long.valueOf(params.get("id"));
        MembershipEnum type = MembershipEnum.valueOf(params.get("type"));
        Membership membership = membershipService.findById(id);
        membershipService.changetype(id, type);
        return ResponseEntity.ok(membership);

    }
    
    




    
}
