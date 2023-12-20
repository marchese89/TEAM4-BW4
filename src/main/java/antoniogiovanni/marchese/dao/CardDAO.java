package antoniogiovanni.marchese.dao;

import antoniogiovanni.marchese.entities.Card;
import antoniogiovanni.marchese.entities.User;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;

public class CardDAO {
    private final EntityManager em;

    public CardDAO(EntityManager em) {
        this.em = em;
    }

    //************************* SAVE ******************************
    public void save(Card card) {
        EntityTransaction transaction = em.getTransaction();
        transaction.begin();
        em.persist(card);
        transaction.commit();
        System.out.println("Card " + card.getCardNumber() + " added!");
    }

    //*********************** FIND BY ID **************************
    public Card findById(long id) {
        return em.find(Card.class,id);
    }


    //*************************** DELETE **************************
    public void findByIdAndDelete(long id) {
        Card found = this.findById(id);

        if (found != null) {
            EntityTransaction transaction = em.getTransaction();
            transaction.begin();
            em.remove(found);
            transaction.commit();
            System.out.println("Card " + found.getCardNumber() + " deleted!");
        } else {
            System.out.println("Card with id " + id + " not found");
        }

    }
}
