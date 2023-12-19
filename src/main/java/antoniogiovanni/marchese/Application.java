package antoniogiovanni.marchese;

import antoniogiovanni.marchese.dao.*;
import antoniogiovanni.marchese.entities.*;
import antoniogiovanni.marchese.enums.MeansState;
import antoniogiovanni.marchese.enums.MeansType;
import antoniogiovanni.marchese.enums.SubscriptionType;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import java.time.LocalDate;
import java.time.LocalDateTime;

public class Application {

    private static final EntityManagerFactory emf = Persistence.createEntityManagerFactory("team4-bw4");

    public static void main(String[] args) {

        EntityManager em = emf.createEntityManager();
        MeansDAO md = new MeansDAO(em);
        PeriodStateMeansOfTransportDAO pd = new PeriodStateMeansOfTransportDAO(em);
        UserDAO ud = new UserDAO(em);
        CardDAO ccd = new CardDAO(em);
        EmittableDAO eemd = new EmittableDAO(em);
        RouteDAO routeDAO = new RouteDAO(em);
        MileageDAO mileageDAO = new MileageDAO(em);

        Means vehicle1 = new Means( MeansType.BUS);

        md.saveMeans(vehicle1);
        PeriodStateMeansOfTransport period1 = new PeriodStateMeansOfTransport(LocalDate.of(2023,12,5),LocalDate.of(2023,12,25), MeansState.MAINTENANCE);
        pd.savePeriod(period1);
        //colleghiamo il mezzo al periodo-stato
        Means meansFromDB = md.findById(1);
        PeriodStateMeansOfTransport periodFromDB = pd.findById(2);
        meansFromDB.addPeriodStateMeansOfTransport(periodFromDB);
        md.saveMeans(meansFromDB);
//        System.out.println(vehicle1);
//        System.out.println(period1);
        User u1 = new User("Nome","Cognome");
        ud.save(u1);
        User userFromDB = ud.findById(4);
        Card card = new Card(123,LocalDate.now().minusWeeks(2),userFromDB);
        ccd.save(card);
        Subscription subscription = new Subscription(userFromDB, SubscriptionType.MONTHLY);
        eemd.save(subscription);
        Ticket ticket = new Ticket();
        ticket.setMeans(meansFromDB);
        eemd.save(ticket);
        Route route = new Route("Partenza","Arrivo",60);
        routeDAO.saveRoute(route);
        Route routeFromDB = routeDAO.findById(10);
        meansFromDB.addRoute(routeFromDB);
        md.saveMeans(meansFromDB);
        Mileage mileage = new Mileage(LocalDateTime.now().plusDays(2),70,routeFromDB,meansFromDB);
        mileageDAO.saveMileage(mileage);

    }
}
