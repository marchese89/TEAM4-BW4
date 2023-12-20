package antoniogiovanni.marchese.entities;

import antoniogiovanni.marchese.enums.SubscriptionType;

import javax.persistence.*;
import javax.persistence.criteria.CriteriaBuilder;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Entity
@Table(name = "card_ticket_issuers")
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
@DiscriminatorColumn(name="issuer_type")
@NamedQuery(name="findEmittableByIssuer", query ="SELECT e FROM Emittable e WHERE e.cardTicketIssuer = :issuer AND e.issueDate BETWEEN :initialDate AND :finalDate")
public abstract class CardTicketIssuer {
    @Id
    @GeneratedValue
    private long id;

    @OneToMany(mappedBy = "cardTicketIssuer")
    protected List<Emittable> emittedItems = new ArrayList<>();


    //GETTER
    public long getId() {
        return id;
    }


    //METHODS
    public Subscription issueSubscription(User user, SubscriptionType subscriptionType, LocalDate issueDate) {
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
            Subscription subscription = new Subscription(user, subscriptionType, issueDate);
            subscription.setCardTicketIssuer(this);
            emittedItems.add(subscription);
            return subscription;
        }else{
            return null;
        }
    }

    public Ticket issueTicket(LocalDate date) {
        Ticket ticket = new Ticket(date);
        ticket.setCardTicketIssuer(this);
        emittedItems.add(ticket);
        return ticket;
    }

    @Override
    public boolean equals ( Object o ) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        CardTicketIssuer that = (CardTicketIssuer) o;
        return id == that.id;
    }

}
