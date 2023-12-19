package antoniogiovanni.marchese.dao;

import antoniogiovanni.marchese.entities.Mileage;
import antoniogiovanni.marchese.entities.PeriodStateMeansOfTransport;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;

public class PeriodStateMeansOfTransportDAO {
    private final EntityManager em;

    public PeriodStateMeansOfTransportDAO ( EntityManager em ) {
        this.em = em;
    }

    //************************* SAVE ******************************
    public void saveMileage( PeriodStateMeansOfTransport period){
        EntityTransaction transaction = em.getTransaction();
        transaction.begin();
        em.persist(period);
        transaction.commit();
        System.out.println( "Period with id: " + period.getId() + " successfully added to Public Transport");
    }
    //*********************** FIND BY ID **************************
    public PeriodStateMeansOfTransport findById( long id) {
        return em.find(PeriodStateMeansOfTransport.class, id);
    }

    //*************************** DELETE **************************
    public void findByIdAndDelete(long id){
        PeriodStateMeansOfTransport found = this.findById(id);
        if(found != null){
            EntityTransaction transaction = em.getTransaction();
            transaction.begin();
            em.remove(found);
            transaction.commit();
            System.out.println( "Period with id: " + found.getId() + " successfully deleted from Public Transport");
        }else {
            System.out.println( "Period not found");
        }
    }
}
