package antoniogiovanni.marchese.entities;

import javax.persistence.*;
import java.time.LocalDate;
import java.time.LocalDateTime;

@Entity
@Table(name = "tickets")
@NamedQuery(name = "endorsedTicketTotal",query = "SELECT COUNT(t) FROM Ticket t WHERE t.means = :means")
@NamedQuery(name = "endorsedTicketPeriod", query = "SELECT COUNT(t) FROM Ticket t WHERE t.means = :means AND t.endorsementDate BETWEEN :startDate AND :endDate")
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

    private void setMeans(Means means) {
        this.means = means;
    }

    public void endorseTicket(Means means,LocalDateTime endorsementDate){
        setMeans(means);
        this.valid = false;
        this.endorsementDate = endorsementDate;
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
