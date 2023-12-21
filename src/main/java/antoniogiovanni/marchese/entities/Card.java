package antoniogiovanni.marchese.entities;

import javax.persistence.*;
import java.time.LocalDate;
@Entity
@Table(name = "cards")
public class Card {
    @Id
    @GeneratedValue
    private long id;

    @Column(name = "card_number", unique = true)
    private long cardNumber;

    @Column(name = "issue_date")
    private LocalDate issueDate;

    @Column(name = "expiration_date")
    private LocalDate expirationDate;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;

    //CONSTRUCTORS
    public Card() {
    }

    public Card(long cardNumber, LocalDate issueDate, User user) {
        this.cardNumber = cardNumber;
        this.issueDate = issueDate;
        this.expirationDate = issueDate.plusYears(1);
        this.user = user;
    }

    //GETTERS & SETTERS
    public long getId() {
        return id;
    }

    public long getCardNumber() {
        return cardNumber;
    }

    public void setCardNumber(long cardNumber) {
        this.cardNumber = cardNumber;
    }

    public LocalDate getIssueDate() {
        return issueDate;
    }
    public void setIssueDate(LocalDate issueDate) {
        this.issueDate = issueDate;
    }

    public LocalDate getExpirationDate() {
        return expirationDate;
    }

    public void setExpirationDate(LocalDate expirationDate) {
        this.expirationDate = expirationDate;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }


    //TO_STRING
    @Override
    public String toString() {
        return "Card number: " + cardNumber +
                "| issue date: " + issueDate +
                "| expirationD date: " + expirationDate +
                "| belonging to " + user.getName() + " " + user.getSurname();
    }
}
