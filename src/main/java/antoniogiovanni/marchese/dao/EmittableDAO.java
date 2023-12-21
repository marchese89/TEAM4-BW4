package antoniogiovanni.marchese.dao;

import antoniogiovanni.marchese.entities.Emittable;
import antoniogiovanni.marchese.entities.Means;
import antoniogiovanni.marchese.entities.Subscription;
import antoniogiovanni.marchese.entities.User;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import javax.persistence.TypedQuery;
import java.time.LocalDateTime;
import java.util.List;

public class EmittableDAO {
    private final EntityManager em;

    public EmittableDAO(EntityManager em) {
        this.em = em;
    }

    //************************* SAVE ******************************
    public void save(Emittable emittable) {
        EntityTransaction transaction = em.getTransaction();
        transaction.begin();
        em.persist(emittable);
        transaction.commit();
        System.out.println("Emittable " + emittable.getId() + " added!");
    }
    //*********************** FIND BY ID **************************
    public Emittable findById(long id) {
        return em.find(Emittable.class,id);
    }

    //*************************** DELETE **************************
    public void findByIdAndDelete(long id) {
        Emittable found = this.findById(id);

        if (found != null) {
            EntityTransaction transaction = em.getTransaction();
            transaction.begin();
            em.remove(found);
            transaction.commit();
            System.out.println("Emittable " + found.getId() + " deleted!");
        } else {
            System.out.println("Emittable with id " + id + " not found");
        }

    }

    public long endorsedTicketPerMeansTotal(Means means){
        TypedQuery<Long> countTicketTotalQuery = em.createNamedQuery("endorsedTicketTotal",Long.class);
        countTicketTotalQuery.setParameter("means",means);
        return countTicketTotalQuery.getSingleResult();
    }

    public long endorsedTicketPerMeansPeriod(Means means, LocalDateTime startDate, LocalDateTime endDate) {
        TypedQuery<Long> countTicketTotalQuery = em.createNamedQuery("endorsedTicketPeriod", Long.class);
        countTicketTotalQuery.setParameter("means", means);
        countTicketTotalQuery.setParameter("startDate", startDate);
        countTicketTotalQuery.setParameter("endDate", endDate);
        return countTicketTotalQuery.getSingleResult();
    }
    public boolean SubscriptionPerCardNumber(long cardNumber){
        TypedQuery<Subscription> subscriptionPerCardNumberQuery = em.createNamedQuery("getSubscriptionByCard", Subscription.class);
        subscriptionPerCardNumberQuery.setParameter("cardNumber",cardNumber);
        List<Subscription> s = subscriptionPerCardNumberQuery.getResultList();
        if (s.size() > 0){
            return true;
        }else {
            return false;
        }
    }
}
