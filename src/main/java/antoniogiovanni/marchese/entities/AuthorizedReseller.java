package antoniogiovanni.marchese.entities;
import antoniogiovanni.marchese.enums.SubscriptionType;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.List;

@Entity
@Table(name="resellers")
@DiscriminatorValue("reseller")
public class AuthorizedReseller extends CardTicketIssuer {
    @Id
    @GeneratedValue
    private long id;


    public AuthorizedReseller(){}


    @Override
    public String toString() {
        return "AuthorizedReseller{" +
                "id=" + id +
                "} " + super.toString();
    }


    @Override
    public Ticket issueTicket() {
        return new Ticket();
    }
}

