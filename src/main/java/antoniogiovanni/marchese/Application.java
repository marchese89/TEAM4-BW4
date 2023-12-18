package antoniogiovanni.marchese;

import antoniogiovanni.marchese.dao.MeansDAO;
import antoniogiovanni.marchese.entities.Means;
import antoniogiovanni.marchese.enums.MeansType;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import java.time.LocalDate;

public class Application {

    private static final EntityManagerFactory emf = Persistence.createEntityManagerFactory("u4-w3-l2");

    public static void main(String[] args) {

        EntityManager em = emf.createEntityManager();
        MeansDAO md = new MeansDAO(em);

        Means vehicle1 = new Means(50, MeansType.BUS);
        //md.saveMeans(vehicle1);

        System.out.println(vehicle1);
    }
}
