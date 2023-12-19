package antoniogiovanni.marchese.dao;

import antoniogiovanni.marchese.entities.Means;
import antoniogiovanni.marchese.entities.Mileage;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;

public class MileageDAO {
    private final EntityManager em;

    public MileageDAO ( EntityManager em ) {
        this.em = em;
    }

    //************************* SAVE ******************************
    public void saveMileage( Mileage mileage){
        EntityTransaction transaction = em.getTransaction();
        transaction.begin();
        em.persist(mileage);
        transaction.commit();
        System.out.println( "Mileage with id: " + mileage.getId() + " successfully added to Public Transport");
    }
    //*********************** FIND BY ID **************************
    public Mileage findById( long id) {
        return em.find(Mileage.class, id);
    }

    //*************************** DELETE **************************
    public void findByIdAndDelete(long id){
        Mileage found = this.findById(id);
        if(found != null){
            EntityTransaction transaction = em.getTransaction();
            transaction.begin();
            em.remove(found);
            transaction.commit();
            System.out.println( "Mileage with id: " + found.getId() + " successfully deleted from Public Transport");
        }else {
            System.out.println( "Mileage not found");
        }
    }
}
