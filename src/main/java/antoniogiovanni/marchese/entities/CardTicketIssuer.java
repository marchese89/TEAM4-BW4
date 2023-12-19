package antoniogiovanni.marchese.entities;

import antoniogiovanni.marchese.enums.SubscriptionType;

import javax.persistence.*;
import javax.persistence.criteria.CriteriaBuilder;
import java.time.LocalDate;
import java.util.List;

@Entity
@Table(name = "card_ticket_issuers")
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
@DiscriminatorColumn(name="issuer_type")
public abstract class CardTicketIssuer {

    @Id
    @GeneratedValue
    private long id;

    public Subscription issueSupscription(User user, SubscriptionType subscriptionType) {
        //verifichiamo che l'utente selezionato abbia  una tessera in corso di validità
        List<Card> cardList = user.getCards();
        LocalDate now = LocalDate.now();
        boolean ok = false;//indica se l'utente può fare un abbonamento
        for (Card c: cardList){
            if(now.isBefore(c.getExpirationDate())){
                ok = true;
                break;
            }
        }
        if(ok) {
            Subscription subscription = new Subscription(user, subscriptionType);
            subscription.setCardTicketIssuer(this);
            return subscription;
        }else{
            return null;
        }
    }

    public Ticket issueTicket() {
        Ticket ticket = new Ticket();
        ticket.setCardTicketIssuer(this);
        return ticket;
    }

    public long getId() {
        return id;
    }
    @OneToMany
    private List<Emittable> emittableList;
}
