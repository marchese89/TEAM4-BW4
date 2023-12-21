package antoniogiovanni.marchese.entities;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "users")
//@NamedQuery(name="getSubscriptionByCard", query="SELECT s FROM User u JOIN u.cards c JOIN u.subscriptions s WHERE  c.cardNumber = :specificCardNumber AND s.issueDate > CURRENT_DATE")
@NamedQuery(name="getSubscriptionByCard", query="SELECT s FROM User u WHERE :cardNumber IN (SELECT c FROM Card u WHERE c.)")
public class User {
    @Id
    @GeneratedValue
    private long id;
    private String name;
    private String surname;

    @OneToMany(mappedBy = "user")
    private List<Subscription> subscriptions;

    @OneToMany(mappedBy = "user")
    private List<Card> cards;
    public User() {
    }

    public User(String name, String surname) {
        this.name = name;
        this.surname = surname;
    }

    public List<Card> getCards() {
        return cards;
    }

    public void setCards(List<Card> cards) {
        this.cards = cards;
    }

    public long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public List<Subscription> getSubscriptions() {
        return subscriptions;
    }

    public void setSubscriptions(List<Subscription> subscriptions) {
        this.subscriptions = subscriptions;
    }

    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", surname='" + surname + '\'' +
                ", subscriptions=" + subscriptions +
                '}';
    }
}
