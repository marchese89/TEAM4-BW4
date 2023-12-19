package antoniogiovanni.marchese;

import antoniogiovanni.marchese.dao.CardTicketIssuerDAO;
import antoniogiovanni.marchese.entities.CardTicketIssuer;
import antoniogiovanni.marchese.entities.VendingMachine;
import antoniogiovanni.marchese.enums.VendingMachineState;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import java.time.LocalDate;

public class Application {

    private static final EntityManagerFactory emf = Persistence.createEntityManagerFactory("team4-bw4");

    public static void main(String[] args) {
        EntityManager em = emf.createEntityManager();
        VendingMachine vendingMachine = new VendingMachine(VendingMachineState.ACTIVE);
        CardTicketIssuerDAO ctd = new CardTicketIssuerDAO(em);
        ctd.save(vendingMachine);
        System.out.println("Tutto ok");
    }
}
