package antoniogiovanni.marchese.dao;

import antoniogiovanni.marchese.entities.Means;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;

public class MeansDAO {
    private final EntityManager em;

    public MeansDAO ( EntityManager em ) {
        this.em = em;
    }

    //SAVE METHOD
    public void saveMeans( Means vehicle){
        EntityTransaction transaction = em.getTransaction();
        transaction.begin();
        em.persist(vehicle);
        transaction.commit();
    }
}
