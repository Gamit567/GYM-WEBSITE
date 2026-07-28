package GYM.MEMBERSHIP.Repository;

import org.springframework.data.jpa.repository.JpaRepository;

import GYM.MEMBERSHIP.ModelClasses.Membership;

public interface MembershipRepository extends JpaRepository<Membership, Long>{
    
    
}
