package antoniogiovanni.marchese.entities;

import javax.persistence.*;
import java.time.LocalDate;

@Entity
@Table(name = "emittables")
@Inheritance(strategy = InheritanceType.JOINED)
public abstract class Emittable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    @Column(name = "issue_date")
    LocalDate issueDate;
    @ManyToOne
    @JoinColumn(name = "card_ticket_issuer")
    private CardTicketIssuer cardTicketIssuer;

    //CONSTRUCTORS
    public Emittable(){}
    public Emittable(LocalDate issueDate) {
        this.issueDate = issueDate;
    }

    //GETTERS
    public long getId() {
        return id;
    }
    public CardTicketIssuer getCardTicketIssuer() {
        return cardTicketIssuer;
    }
    public LocalDate getIssueDate() {
        return issueDate;
    }

    //SETTERS
    public void setCardTicketIssuer(CardTicketIssuer cardTicketIssuer) {
        this.cardTicketIssuer = cardTicketIssuer;
    }
    public void setIssueDate(LocalDate issueDate) {
        issueDate = issueDate;
    }

    //TO_STRING
    @Override
    public String toString() {
        return
                "id=" + id +
                ", IssueDate=" + issueDate;
    }
}
