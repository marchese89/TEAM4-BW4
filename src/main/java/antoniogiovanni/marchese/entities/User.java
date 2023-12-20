package antoniogiovanni.marchese.entities;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "users")
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

    //CONSTRUCTORS
    public User() {
    }

    public User(String name, String surname) {
        this.name = name;
        this.surname = surname;
    }

    //GETTERS & SETTERS
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


    //TO_STRING
    @Override
    public String toString() {
        return "User "  + name + ' ' + surname +
                " has following subscriptions: "+ subscriptions ;
    }
}
