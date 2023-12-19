package antoniogiovanni.marchese.dao;

import antoniogiovanni.marchese.entities.CardTicketIssuer;
import antoniogiovanni.marchese.entities.User;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;

public class CardTicketIssuerDAO {
    private final EntityManager em;

    public CardTicketIssuerDAO(EntityManager em) {
        this.em = em;
    }

    public void save(CardTicketIssuer cardTicketIssuer) {
        EntityTransaction transaction = em.getTransaction();
        transaction.begin();
        em.persist(cardTicketIssuer);
        transaction.commit();
        System.out.println("CardTicketIssuer " + cardTicketIssuer.getId() + " added!");
    }

    public CardTicketIssuer findById(long id) {
        return em.find(CardTicketIssuer.class,id);
    }


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
}
