package antoniogiovanni.marchese.entities;

import javax.persistence.*;
import java.time.LocalDate;
import java.time.LocalDateTime;

@Entity
@Table(name = "tickets")
public class Ticket extends Emittable{
    private Boolean valid;
    @Column(name = "endorsement_date", nullable = true)
    private LocalDateTime endorsementDate;
    @ManyToOne
    @JoinColumn(name = "means_id")
    private Means means;

    //CONSTRUCTORS
    public Ticket ( LocalDate issueDate ) {
        super(issueDate);
        this.valid = true;
    }


    //GETTERS & SETTERS

    public Boolean getValid() {
        return valid;
    }

    public void setValid(Boolean valid) {
        this.valid = valid;
    }

    public LocalDateTime getEndorsementDate() {
        return endorsementDate;
    }

    public void setEndorsementDate(LocalDateTime endorsementDate) {
        this.endorsementDate = endorsementDate;
    }

    public Means getMeans() {
        return means;
    }

    public void setMeans(Means means) {
        this.means = means;
    }

    //TO_STRING
    @Override
    public String toString() {
        return "Ticket with issue date: " + super.getIssueDate()+
                "valid: " + valid +
                ", endorsement date: " + endorsementDate +
                ", means: " + means ;
    }
    //METHODS
    public Ticket() {
        this.valid = true;
    }
}
