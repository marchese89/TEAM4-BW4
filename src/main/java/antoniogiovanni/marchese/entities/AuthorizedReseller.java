package antoniogiovanni.marchese.entities;
import antoniogiovanni.marchese.enums.SubscriptionType;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.List;

@Entity
@Table(name="resellers")
@DiscriminatorValue("reseller")
public class AuthorizedReseller extends CardTicketIssuer {

    public AuthorizedReseller(){}


    @Override
    public String toString() {
        return "AuthorizedReseller{" +
                "id=" + super.getId() +
                "} " + super.toString();
    }


    @Override
    public Ticket issueTicket() {
        return new Ticket();
    }
}

