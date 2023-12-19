package antoniogiovanni.marchese.entities;

import javax.persistence.*;
import java.time.LocalDate;

@Entity
@Table(name = "tickets")
public class Ticket extends Emittable{
    private Boolean valid;
    @Column(name = "endorsement_date")
    private LocalDate endorsementDate;
//    @ManyToOne
//    @JoinColumn(name = "means_id")
//    private Means means;

    public Ticket() {
        this.valid = true;
    }

    public Boolean getValid() {
        return valid;
    }

    public void setValid(Boolean valid) {
        this.valid = valid;
    }

    public LocalDate getEndorsementDate() {
        return endorsementDate;
    }

    public void setEndorsementDate(LocalDate endorsementDate) {
        this.endorsementDate = endorsementDate;
    }

//    public Means getMeans() {
//        return means;
//    }
//
//    public void setMeans(Means means) {
//        this.means = means;
//    }

    @Override
    public String toString() {
        return "Ticket{ issueDate=" + super.getIssueDate()+
                "valid=" + valid +
                ", endorsementDate=" + endorsementDate +
//                ", means=" + means +
                ", IssueDate=" + IssueDate +
                "} ";
    }
}
