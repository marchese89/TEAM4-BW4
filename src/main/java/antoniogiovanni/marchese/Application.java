package antoniogiovanni.marchese;

import antoniogiovanni.marchese.dao.MeansDAO;
import antoniogiovanni.marchese.dao.PeriodStateMeansOfTransportDAO;
import antoniogiovanni.marchese.entities.Means;
import antoniogiovanni.marchese.entities.PeriodStateMeansOfTransport;
import antoniogiovanni.marchese.enums.MeansState;
import antoniogiovanni.marchese.enums.MeansType;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import java.time.LocalDate;

public class Application {

    private static final EntityManagerFactory emf = Persistence.createEntityManagerFactory("team4-bw4");

    public static void main(String[] args) {

        EntityManager em = emf.createEntityManager();
        MeansDAO md = new MeansDAO(em);
        PeriodStateMeansOfTransportDAO pd = new PeriodStateMeansOfTransportDAO(em);

        Means vehicle1 = new Means( MeansType.BUS);
        //md.saveMeans(vehicle1);
        PeriodStateMeansOfTransport period1 = new PeriodStateMeansOfTransport(LocalDate.of(2023,12,5),LocalDate.of(2023,12,25), MeansState.MAINTENANCE);
        //pd.savePeriod(period1);

        System.out.println(vehicle1);
        System.out.println(period1);
    }
}
