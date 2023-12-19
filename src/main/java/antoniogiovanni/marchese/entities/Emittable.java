package antoniogiovanni.marchese.entities;

import javax.persistence.*;
import java.time.LocalDate;

@Entity
@Table(name = "emittables")
@Inheritance(strategy = InheritanceType.JOINED)
public abstract class Emittable {
    @Id
    @GeneratedValue
    private long id;
    @Column(name = "issue_date")
    LocalDate issueDate;

    public long getId() {
        return id;
    }

    public Emittable(){

    }
    public Emittable(LocalDate issueDate) {
        issueDate = issueDate;
    }

    @ManyToOne
    @JoinColumn(name = "card_ticket_issuer")
    private CardTicketIssuer cardTicketIssuer;

    public CardTicketIssuer getCardTicketIssuer() {
        return cardTicketIssuer;
    }

    public void setCardTicketIssuer(CardTicketIssuer cardTicketIssuer) {
        this.cardTicketIssuer = cardTicketIssuer;
    }

    public LocalDate getIssueDate() {
        return issueDate;
    }

    public void setIssueDate(LocalDate issueDate) {
        issueDate = issueDate;
    }

    @Override
    public String toString() {
        return
                "id=" + id +
                ", IssueDate=" + issueDate;
    }
}
