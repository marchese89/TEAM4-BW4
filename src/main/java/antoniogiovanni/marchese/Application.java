package antoniogiovanni.marchese;

import antoniogiovanni.marchese.dao.*;
import antoniogiovanni.marchese.entities.*;
import antoniogiovanni.marchese.enums.MeansState;
import antoniogiovanni.marchese.enums.MeansType;
import antoniogiovanni.marchese.enums.SubscriptionType;
import antoniogiovanni.marchese.enums.VendingMachineState;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Scanner;

public class Application {

    private static final EntityManagerFactory emf = Persistence.createEntityManagerFactory("team4-bw4");

    public static void main(String[] args) {
        //ENTITY MANAGER DAO
        EntityManager em = emf.createEntityManager();
        MeansDAO md = new MeansDAO(em);
        PeriodStateMeansOfTransportDAO pd = new PeriodStateMeansOfTransportDAO(em);
        UserDAO ud = new UserDAO(em);
        CardDAO ccd = new CardDAO(em);
        EmittableDAO eemd = new EmittableDAO(em);
        RouteDAO routeDAO = new RouteDAO(em);
        MileageDAO mileageDAO = new MileageDAO(em);
        CardTicketIssuerDAO issuerDAO = new CardTicketIssuerDAO(em);

        //EXECUTION APP
        //Creiamo user e gli diamo una tessera
        Scanner userInput= new Scanner(System.in);

        System.out.println("Ciao e benvenuto/a, per poter usufruire del servizio di abbonamento è necessario registrarsi.");
        System.out.println("Inserisci per favore il tuo nome: ");

        //recuperiamo tramite scanner i dati forniti

        /*
        String name = userInput.nextLine();
        System.out.println("Ora il tuo cognome: ");
        String surname = userInput.nextLine();
         */
             */

        //Registro user nel database
            //User newUser = new User(name, surname);
            //ud.save(newUser);
        User newUserFromDB = ud.findById(1);
        Card newCard = new Card(181013,LocalDate.now(),newUserFromDB);
            //ccd.save(newCard);
        Card firstUserCard = ccd.findById(3);
        System.out.println("Ecco a te: ");
        System.out.println(newCard);
        //********************** USER TTICKETS AND SUBSCRIPTION CREATION *******************
        Means vehicle1 = new Means(MeansType.BUS);

//        md.saveMeans(vehicle1);
        PeriodStateMeansOfTransport period1 = new PeriodStateMeansOfTransport(LocalDate.of(2023, 12, 5), LocalDate.of(2023, 12, 25), MeansState.MAINTENANCE);
//        pd.savePeriod(period1);
        //colleghiamo il mezzo al periodo-stato
        Means meansFromDB = md.findById(1);
        PeriodStateMeansOfTransport periodFromDB = pd.findById(2);
        meansFromDB.addPeriodStateMeansOfTransport(periodFromDB);
//        md.saveMeans(meansFromDB);
//        System.out.println(vehicle1);
//        System.out.println(period1);
        User u1 = new User("Nome", "Cognome");
//        ud.save(u1);
        User userFromDB = ud.findById(4);
        Card card = new Card(123, LocalDate.now().minusWeeks(2), userFromDB);
//        ccd.save(card);
        Subscription subscription = new Subscription(userFromDB, SubscriptionType.MONTHLY, LocalDate.now());
//        eemd.save(subscription);
        //Ticket ticket = new Ticket();
        //ticket.setMeans(meansFromDB);
//        eemd.save(ticket);
        Route route = new Route("Partenza", "Arrivo", 60);
//        routeDAO.saveRoute(route);
        Route routeFromDB = routeDAO.findById(10);
        meansFromDB.addRoute(routeFromDB);
//        md.saveMeans(meansFromDB);
        Mileage mileage = new Mileage(LocalDateTime.now().plusDays(2), 70, routeFromDB, meansFromDB);
//        mileageDAO.saveMileage(mileage);
        User user2 = new User("name", "surname");
//          ud.save(user2);
        User user2fromdb = ud.findById(17);
        VendingMachine vending1 = new VendingMachine(VendingMachineState.ACTIVE);
//          issuerDAO.save(vending1);
        VendingMachine vending1fromdb = (VendingMachine) issuerDAO.findById(20);
        Subscription subscription1 = new Subscription(user2fromdb, SubscriptionType.MONTHLY, LocalDate.of(2023, 12, 19));
//        eemd.save(subscription1);
        Card card1 = new Card(1890, LocalDate.now().minusMonths(5), user2fromdb);
//        ccd.save(card1);
//        ud.getSubscriptionByCard(1890);
        System.out.println("ci sono abbonamenti validi per n. carta?");
        System.out.println(eemd.SubscriptionPerCardNumber(123));
        //md.saveMeans(meansFromDB);
//        System.out.println(vehicle1);
//        System.out.println(period1);
      
        Long meansRouteCount = mileageDAO.meansRouteCount(meansFromDB,routeFromDB);
        System.out.println("viaggi fatti dal mezzo "+ meansFromDB.getId() + " sulla rotta "+routeFromDB.getId()+": "+meansRouteCount);

        VendingMachine machine1 = new VendingMachine(VendingMachineState.ACTIVE);
        //issuerDAO.save(machine1);
        VendingMachine machineFromDB = (VendingMachine) issuerDAO.findById(16);
        //Ticket ticket1 = machineFromDB.issueTicket(LocalDate.now());
        //eemd.save(ticket1);
        System.out.println("All emittable items issued in a specific period");
        //issuerDAO.getEmittableByIssuer(machineFromDB,LocalDate.now().minusDays(1),LocalDate.now().plusDays(1)).forEach(System.out::println);
        Ticket ticketFromDB = (Ticket) eemd.findById(6);
        //ticketFromDB.endorseTicket(meansFromDB,LocalDateTime.now());
        //eemd.save(ticketFromDB);
        System.out.println("***************************** Tiket vidimati su uno specifico mezzo in totale **************************");
        //System.out.println(eemd.endorsedTicketPerMeansTotal(meansFromDB));
        System.out.println("****************************************** Tiket vidimati su uno specifico mezzo e in un range di date *********************** ");
        //System.out.println(eemd.endorsedTicketPerMeansPeriod(meansFromDB,LocalDateTime.now().minusDays(1),LocalDateTime.now().plusDays(1)));
    }
}
