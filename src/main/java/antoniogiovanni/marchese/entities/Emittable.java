package antoniogiovanni.marchese.entities;

import javax.persistence.*;
import java.time.LocalDate;

@Entity
@Inheritance(strategy = InheritanceType.JOINED)
public abstract class Emittable {
    @Id
    @GeneratedValue
    private long id;
    @Column(name = "issue_date")
    LocalDate IssueDate;

    public long getId() {
        return id;
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
        return IssueDate;
    }

    public void setIssueDate(LocalDate issueDate) {
        IssueDate = issueDate;
    }

    @Override
    public String toString() {
        return
                "id=" + id +
                ", IssueDate=" + IssueDate;
    }
}
