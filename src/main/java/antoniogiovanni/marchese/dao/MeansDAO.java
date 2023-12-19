package antoniogiovanni.marchese.dao;

import antoniogiovanni.marchese.entities.Means;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import java.util.UUID;

public class MeansDAO {
    private final EntityManager em;

    public MeansDAO ( EntityManager em ) {
        this.em = em;
    }

    //************************* SAVE ******************************
    public void saveMeans( Means vehicle){
        EntityTransaction transaction = em.getTransaction();
        transaction.begin();
        em.persist(vehicle);
        transaction.commit();
        System.out.println( vehicle.getMeansType()+ " with id " + vehicle.getId() + " successfully added to Public Transport");
    }
    //*********************** FIND BY ID **************************
    public Means findById( long id) {
        return em.find(Means.class, id);
    }

    //*************************** DELETE **************************
    public void findByIdAndDelete(long id){
        Means found = this.findById(id);
        if(found != null){
            EntityTransaction transaction = em.getTransaction();
            transaction.begin();
            em.remove(found);
            transaction.commit();
            System.out.println( found.getMeansType()+ " with id " + found.getId() + " successfully deleted from Public Transport");
        }else {
            System.out.println( "Means not found");
        }
    }
}
