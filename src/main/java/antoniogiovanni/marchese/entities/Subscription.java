package antoniogiovanni.marchese.entities;

import antoniogiovanni.marchese.enums.SubscriptionType;

import javax.persistence.*;

@Entity
public class Subscription extends Emittable{

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;
    @Column(name = "subscription_type")
    @Enumerated(EnumType.STRING)
    private SubscriptionType subscriptionType;

    public Subscription() {
    }

    public Subscription(User user, SubscriptionType subscriptionType) {
        this.user = user;
        this.subscriptionType = subscriptionType;
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
        return "Subscription{ issueDate=" + super.getIssueDate()+
                "user=" + user +
                ", subscriptionType=" + subscriptionType +
                ", IssueDate=" + IssueDate +
                "} " + super.toString();
    }
}
