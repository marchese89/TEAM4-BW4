package antoniogiovanni.marchese.dao;

import antoniogiovanni.marchese.entities.User;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;

public class UserDAO {

    private final EntityManager em;

    public UserDAO(EntityManager em) {
        this.em = em;
    }

    //************************* SAVE ******************************
    public void save(User user) {
        EntityTransaction transaction = em.getTransaction();
        transaction.begin();
        em.persist(user);
        transaction.commit();
        System.out.println("User " + user.getName() + " added!");
    }
    //*********************** FIND BY ID **************************
    public User findById(long id) {
        return em.find(User.class,id);
    }

    //*************************** DELETE **************************
    public void findByIdAndDelete(long id) {
        User found = this.findById(id);

        if (found != null) {
            EntityTransaction transaction = em.getTransaction();
            transaction.begin();
            em.remove(found);
            transaction.commit();
            System.out.println("User " + found.getName() + " deleted!");
        } else {
            System.out.println("User with id " + id + " not found");
        }

    }
}
