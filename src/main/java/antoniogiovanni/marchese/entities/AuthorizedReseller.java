package antoniogiovanni.marchese.entities;
import antoniogiovanni.marchese.enums.SubscriptionType;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.List;

@Entity
@Table(name="resellers")
@DiscriminatorValue("reseller")
public class AuthorizedReseller extends CardTicketIssuer {

    //CONSTRUCTOR
    public AuthorizedReseller(){}

    //TO_STRING
    @Override
    public String toString() {
        return "AuthorizedReseller{" +
                "id=" + super.getId() +
                "} " + super.toString();
    }

}

