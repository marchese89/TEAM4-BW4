package antoniogiovanni.marchese.dao;

import antoniogiovanni.marchese.entities.CardTicketIssuer;
import antoniogiovanni.marchese.entities.Emittable;
import antoniogiovanni.marchese.entities.Subscription;
import antoniogiovanni.marchese.entities.Ticket;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import javax.persistence.TypedQuery;
import java.time.LocalDate;
import java.util.List;

public class CardTicketIssuerDAO {
    private final EntityManager em;

    public CardTicketIssuerDAO(EntityManager em) {
        this.em = em;
    }

    //************************* SAVE ******************************
    public void save(CardTicketIssuer cardTicketIssuer) {
        EntityTransaction transaction = em.getTransaction();
        transaction.begin();
        em.persist(cardTicketIssuer);
        transaction.commit();
        System.out.println("CardTicketIssuer " + cardTicketIssuer.getId() + " added!");
    }

    //*********************** FIND BY ID **************************
    public CardTicketIssuer findById(long id) {
        return em.find(CardTicketIssuer.class,id);
    }

    //*************************** DELETE **************************
    public void findByIdAndDelete(long id) {
        CardTicketIssuer found = this.findById(id);

        if (found != null) {
            EntityTransaction transaction = em.getTransaction();
            transaction.begin();
            em.remove(found);
            transaction.commit();
            System.out.println("CardTicketIssuer " + found.getId() + " deleted!");
        } else {
            System.out.println("CardTicketIssuer with id " + id + " not found");
        }

    }
    //*********************** EMITTABLE QUERY *********************
    public List<Emittable> getEmittableByIssuer( CardTicketIssuer issuer, LocalDate initialDate,LocalDate finalDate ){
        TypedQuery<Emittable> getEmittables = em.createNamedQuery("findEmittableByIssuer", Emittable.class);
        getEmittables.setParameter("issuer", issuer);
        getEmittables.setParameter("initialDate", initialDate);
        getEmittables.setParameter("finalDate", finalDate);
        return getEmittables.getResultList();
    }
    //************************* TICKET QUERY **********************
    public List<Ticket> getTicketByIssuer( CardTicketIssuer issuer, LocalDate initialDate,LocalDate finalDate ){
        TypedQuery<Ticket> getTickets = em.createNamedQuery("findTicketByIssuer", Ticket.class);
        getTickets.setParameter("issuer", issuer);
        getTickets.setParameter("initialDate", initialDate);
        getTickets.setParameter("finalDate", finalDate);
        return getTickets.getResultList();
    }
    //********************** SUBSCRIPTION QUERY ********************
    public List<Subscription> getSubscriptionByIssuer( CardTicketIssuer issuer, LocalDate initialDate, LocalDate finalDate ){
        TypedQuery<Subscription> getTickets = em.createNamedQuery("findSubscriptionByIssuer", Subscription.class);
        getTickets.setParameter("issuer", issuer);
        getTickets.setParameter("initialDate", initialDate);
        getTickets.setParameter("finalDate", finalDate);
        return getTickets.getResultList();
    }
}
