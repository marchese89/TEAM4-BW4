package antoniogiovanni.marchese.entities;

import javax.persistence.*;
import java.time.LocalDate;

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

    public Card() {
    }

    public Card(long cardNumber, LocalDate issueDate, LocalDate expirationDate, User user) {
        this.cardNumber = cardNumber;
        this.issueDate = issueDate;
        this.expirationDate = expirationDate;
        this.user = user;
    }

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

    @Override
    public String toString() {
        return "Card{" +
                "id=" + id +
                ", cardNumber=" + cardNumber +
                ", issueDate=" + issueDate +
                ", expirationDate=" + expirationDate +
                ", user=" + user.getName() +
                '}';
    }
}
