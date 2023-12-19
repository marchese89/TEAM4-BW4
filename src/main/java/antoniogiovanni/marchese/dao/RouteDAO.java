package antoniogiovanni.marchese.dao;

import antoniogiovanni.marchese.entities.Mileage;
import antoniogiovanni.marchese.entities.Route;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;

public class RouteDAO {
    private final EntityManager em;
    public RouteDAO ( EntityManager em ) {
        this.em = em;
    }

    //************************* SAVE ******************************
    public void saveRoute( Route route){
        EntityTransaction transaction = em.getTransaction();
        transaction.begin();
        em.persist(route);
        transaction.commit();
        System.out.println( "Route with id: " + route.getId() + " successfully added to Public Transport");
    }
    //*********************** FIND BY ID **************************
    public Route findById( long id) {
        return em.find(Route.class, id);
    }

    //*************************** DELETE **************************
    public void findByCodeAndDelete(long id){
        Route found = this.findById(id);
        if(found != null){
            EntityTransaction transaction = em.getTransaction();
            transaction.begin();
            em.remove(found);
            transaction.commit();
            System.out.println( "Route with id: " + found.getId() + " successfully deleted from Public Transport");
        }else {
            System.out.println( "Route not found");
        }
    }
}
