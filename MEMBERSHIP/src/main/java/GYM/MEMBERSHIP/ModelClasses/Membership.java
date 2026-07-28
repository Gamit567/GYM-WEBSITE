package GYM.MEMBERSHIP.ModelClasses;

import java.time.LocalDate;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;

@Entity
public class Membership {

    @Id
    @GeneratedValue
    private Long id;

    private MembershipEnum type;
    private boolean status;

    private LocalDate dateStarted;
    private LocalDate dateEnded;

    public Membership() {
        this.status = false;
        this.type = MembershipEnum.none;
        this.dateStarted = LocalDate.now();
        this.dateEnded = dateStarted.plusDays(30);
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public MembershipEnum getType() {
        return type;
    }

    public void setType(MembershipEnum type) {
        this.type = type;
    }

    public boolean isStatus() {
        return status;
    }

    public void setStatus(boolean status) {
        this.status = status;
    }

    public LocalDate getDateStarted() {
        return dateStarted;
    }

    public void setDateStarted(LocalDate dateStarted) {
        this.dateStarted = dateStarted;
    }

    public LocalDate getDateEnded() {
        return dateEnded;
    }

    public void setDateEnded(LocalDate dateEnded) {
        this.dateEnded = dateEnded;
    }

  
}