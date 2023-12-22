package antoniogiovanni.marchese;

import antoniogiovanni.marchese.dao.*;
import antoniogiovanni.marchese.entities.*;
import antoniogiovanni.marchese.enums.MeansState;
import antoniogiovanni.marchese.enums.MeansType;
import antoniogiovanni.marchese.enums.SubscriptionType;
import antoniogiovanni.marchese.enums.VendingMachineState;
import jdk.swing.interop.SwingInterOpUtils;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
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


        //EXECUTION APP ************ PART 1 **************
        /**/
        //CREATION OF THE SPECIFIC USER AND OF HIS CARD
        Scanner userInput= new Scanner(System.in);

        System.out.println("HELLO! Welcome to M.A.L.S , the best service of public transport in the city! ");
        System.out.println("If you wanna buy a subscription, follow the following instructions. ");

        //1) INTERROGATE USER FOR DATA
        System.out.println("Type your name and press 'enter': ");
        String name = userInput.nextLine();
        System.out.println("Type your surname: ");
        String surname = userInput.nextLine();


        //2) SAVING USER'S DATA IN DATABASE AND CARD CREATION

        User newUser = new User(name, surname);
        ud.save(newUser);
        User newUserFromDB = ud.findById(1);
        System.out.println("Proceeding with your CARD CREATION");
        Card newCard = new Card(151515,LocalDate.now(),newUserFromDB);
        ccd.save(newCard);
        Card cardFromDB = ccd.findById(1);
        newUserFromDB.getCards().add(cardFromDB);

        Card firstUserCard = ccd.findById(1);
        System.out.println("Here's your new CARD! ");
        System.out.println(newCard);


        // ************** END PART 1 ********************
        // ************** START PART 2 *****************
        //3) CREATING SUBSCRIPTION
        System.out.println("We are now GENERATING YOUR SUBSCRIPTION...");
        Subscription subForNewUser = new Subscription(newUserFromDB,SubscriptionType.WEEKLY,LocalDate.now());
        eemd.save(subForNewUser);
        Subscription subForNewUserFromDB = (Subscription) eemd.findById(1);
        System.out.println("Here is your SUBSCRIPTION, thanks for choosing us!");
        System.out.println(subForNewUserFromDB);


        //4) CREATION OF VARIOUS THINGS

        //********************** MEANS CREATION **************************
        System.out.println("........Adding more data to DATABASE........");
        Means newPublicMeans1 = new Means(MeansType.BUS);
        Means newPublicMeans2 = new Means(MeansType.TRAM);
        Means newPublicMeans3 = new Means(MeansType.TRAM);
        Means newPublicMeans4 = new Means(MeansType.BUS);
        md.saveMeans(newPublicMeans1);
        md.saveMeans(newPublicMeans2);
        md.saveMeans(newPublicMeans3);
        md.saveMeans(newPublicMeans4);
        Means bus1FromDB = md.findById(1);
        Means tram1FromDB = md.findById(2);
        Means tram2FromDB = md.findById(3);
        Means bus2FromDB = md.findById(4);

        //********************** ROUTES CREATION **************************
        Route newRoute1 = new Route("ROMA LAURENTINA","ROMA TIBURTINA",45);
        Route newRoute2 = new Route("ROMA TERMINI","ROMA CORNELIA",35);
        Route newRoute3 = new Route("CORSO V.EMANUELE","PIAZZA DI SPAGNA",20);
        Route newRoute4 = new Route("TRASTEVERE","PIAZZA TRILUSSA",15);
        routeDAO.saveRoute(newRoute1);
        routeDAO.saveRoute(newRoute2);
        routeDAO.saveRoute(newRoute3);
        routeDAO.saveRoute(newRoute4);
        Route newRouteFromDB1 = routeDAO.findById(1);
        Route newRouteFromDB2 = routeDAO.findById(2);
        Route newRouteFromDB3 = routeDAO.findById(3);
        Route newRouteFromDB4 = routeDAO.findById(4);

        //********************** MILEAGE CREATION **************************
        Mileage newMileage1 = new Mileage(LocalDateTime.now(),50,newRouteFromDB1,bus1FromDB);
        Mileage newMileage2 = new Mileage(LocalDateTime.now().minusHours(3),30,newRouteFromDB2,tram1FromDB);
        Mileage newMileage3 = new Mileage(LocalDateTime.now().plusHours(1),50,newRouteFromDB3,tram2FromDB);
        Mileage newMileage4 = new Mileage(LocalDateTime.now().minusMinutes(15),45,newRouteFromDB4,bus2FromDB);
        mileageDAO.saveMileage(newMileage1);
        mileageDAO.saveMileage(newMileage2);
        mileageDAO.saveMileage(newMileage3);
        mileageDAO.saveMileage(newMileage4);
        Mileage mileage1FromDb = mileageDAO.findById(1);
        Mileage mileage2FromDb = mileageDAO.findById(2);
        Mileage mileage3FromDb = mileageDAO.findById(3);
        Mileage mileage4FromDb = mileageDAO.findById(4);

        //*********************** END PART 2 ****************************
        //*********************** START PART 3 **************************

        // 5)CARD VALIDITY, NEW TICKET ISSUER AND ENDORSEMENT
        //******************** USER TAKES A BUS/TRAM | check query ************************
        System.out.println("**********************************************");
        System.out.println("Here you have the available routes, choose one by typing a number from 1 to 4");
        System.out.println(newRouteFromDB1);
        System.out.println(newRouteFromDB2);
        System.out.println(newRouteFromDB3);
        System.out.println(newRouteFromDB4);
        int choosedRoute = Integer.parseInt(userInput.nextLine());
        routeDAO.findById(choosedRoute);
        System.out.println("Oh, looks like there's a ticket inspector. He needs your card");
        System.out.println("in order to check the validity of your subscription");
        System.out.println("Digit now the number of your card: ");
        int userShowsSubscription = Integer.parseInt(userInput.nextLine());
        eemd.getSubscriptionByCardNumber(userShowsSubscription);

        System.out.println("**********************************************");
        //5) RELEASING A TICKET FOR USER
        //******************** TICKET FROM V.MACHINE *************************
        System.out.println("Another customer is trying to buy a ticket");
        VendingMachine vendingOne = new VendingMachine(VendingMachineState.ACTIVE);
        issuerDAO.save(vendingOne);
        VendingMachine vendingFromDB = (VendingMachine) issuerDAO.findById(1);
        System.out.println("Printing... ");
        Ticket hereTicket = vendingFromDB.issueTicket(LocalDate.now().minusDays(1));
        eemd.save(hereTicket);
        Ticket ticketFromDB = (Ticket) eemd.findById(2);
        System.out.println("Here's the ticket! Enjoy your trip!");
        System.out.println(ticketFromDB);

        System.out.println("Welcome on the bus. The ticket inspector is asking for your ticket.");
        System.out.println("Thank you, here's your endorsed ticket!");
        ticketFromDB.endorseTicket(bus1FromDB,LocalDateTime.now());
        System.out.println(ticketFromDB);

        userInput.close();
        System.out.println("**********************************************");


        // ********************** END PART 3 *******************************
        /**/
        //5) CHECKING QUERIES CORRECT EXECUTION
        //************************** QUERY TEST ZONE ***********************
        /**/
        System.out.println("****************** QUERIES *****************");
        System.out.println("........Adding more data to DATABASE........");
        User newUserFromDB2 = ud.findById(1);
        VendingMachine vendingFromDB2 = (VendingMachine) issuerDAO.findById(1);
        Subscription subscription = vendingFromDB2.issueSubscription(newUserFromDB2,SubscriptionType.WEEKLY,LocalDate.now());
        eemd.save(subscription);



        System.out.println("");
        System.out.println("***** QUERY FOR RESEARCH OF EMITTABLES *****");

        // Ticket and/ or subsriptions issued in a specific period and from specific issuer.
        System.out.println("*********** Subscriptions released *********");
        issuerDAO.getSubscriptionByIssuer(vendingFromDB,LocalDate.now().minusDays(1),LocalDate.now().plusDays(1)).forEach(System.out::println);
        System.out.println("*************** Tickets released ***********");
        issuerDAO.getTicketByIssuer(vendingFromDB,LocalDate.now().minusDays(1),LocalDate.now().plusDays(1)).forEach(System.out::println);
        System.out.println("***** Tickets & Subscriptions released *****");
        issuerDAO.getEmittableByIssuer(vendingFromDB,LocalDate.now().minusDays(1),LocalDate.now().plusDays(1)).forEach(System.out::println);

        System.out.println("........Adding more data to DATABASE........");

        Means means = md.findById(1);
        Ticket ticket2fromDB = (Ticket) eemd.findById(2);
        Ticket ticket4 = vendingFromDB.issueTicket(LocalDate.now());
        Ticket ticket5 = vendingFromDB.issueTicket(LocalDate.now());
        Ticket ticket6 = vendingFromDB.issueTicket(LocalDate.now());
        Ticket ticket7 = vendingFromDB.issueTicket(LocalDate.now());
        Ticket ticket8 = vendingFromDB.issueTicket(LocalDate.now());
        eemd.save(ticket4);
        eemd.save(ticket5);
        eemd.save(ticket6);
        eemd.save(ticket7);
        eemd.save(ticket8);
        Ticket ticket4fromDB = (Ticket) eemd.findById(4);
        Ticket ticket5fromDB = (Ticket) eemd.findById(5);
        Ticket ticket6fromDB = (Ticket) eemd.findById(6);
        Ticket ticket7fromDB = (Ticket) eemd.findById(7);
        Ticket ticket8fromDB = (Ticket) eemd.findById(8);
        ticket2fromDB.endorseTicket(md.findById(1),LocalDateTime.now());
        ticket4fromDB.endorseTicket(md.findById(1),LocalDateTime.now());
        ticket5fromDB.endorseTicket(md.findById(1),LocalDateTime.now().minusDays(4));
        ticket6fromDB.endorseTicket(md.findById(1),LocalDateTime.now().plusDays(4));
        eemd.save(ticket2fromDB);
        eemd.save(ticket4fromDB);
        eemd.save(ticket5fromDB);
        eemd.save(ticket6fromDB);

        System.out.println("***** ENDORSED TICKETS BETWEEN YESTERDAY AND TOMORROW ****");
        System.out.println(eemd.endorsedTicketPerMeansPeriod(md.findById(1),LocalDateTime.now().minusDays(1),LocalDateTime.now().plusDays(1)));
        System.out.println("************** ENDORSED TICKETS TOTAL ********************");



        System.out.println(eemd.endorsedTicketPerMeansTotal(md.findById(1)));
         /**/
        System.out.println("************ MILEAGE PER MEANS PER ROUTE *****************");
        System.out.println(mileageDAO.meansRouteCount(md.findById(1),routeDAO.findById(1)));

    }
}
