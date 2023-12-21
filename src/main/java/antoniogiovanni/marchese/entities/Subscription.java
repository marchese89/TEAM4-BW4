package antoniogiovanni.marchese.entities;

import antoniogiovanni.marchese.enums.SubscriptionType;

import javax.persistence.*;
import java.time.LocalDate;

@Entity
@Table(name = "subscriptions")
//a partire da un numero di tessera dobbiamo restituire, se esiste, il primo abbonamento in corso di validità per l'utente con quella tessera
@NamedQuery(name="getSubscriptionByCard", query="SELECT s FROM Subscription s WHERE s.expirationDate > CURRENT_DATE AND s.user = (SELECT c.user FROM Card c WHERE c.cardNumber = :cardNumber)")
public class Subscription extends Emittable {

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;
    @Column(name = "subscription_type")
    @Enumerated(EnumType.STRING)
    private SubscriptionType subscriptionType;

    @Column(name = "expiration_date")
    private LocalDate expirationDate;

    public Subscription() {
    }

    public Subscription(User user, SubscriptionType subscriptionType, LocalDate issueDate) {
        super(issueDate);
        this.user = user;
        this.subscriptionType = subscriptionType;
        if (subscriptionType == SubscriptionType.WEEKLY) {
            this.expirationDate = issueDate.plusWeeks(1);
        } else {
            this.expirationDate = issueDate.plusMonths(1);
        }
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public SubscriptionType getSubscriptionType() {
        return subscriptionType;
    }

    public void setSubscriptionType(SubscriptionType subscriptionType) {
        this.subscriptionType = subscriptionType;
    }

    @Override
    public String toString() {
        return "Subscription{" +
                "subscriptionType=" + subscriptionType +
                ", expirationDate=" + expirationDate +
                ", issueDate=" + issueDate +
                "} " + super.toString();
    }
}
