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

}
