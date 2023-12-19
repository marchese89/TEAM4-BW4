package antoniogiovanni.marchese.entities;
import antoniogiovanni.marchese.enums.VendingMachineState;

import javax.persistence.*;
@Entity
@Table
@DiscriminatorValue("vending_machine")
public class VendingMachine extends CardTicketIssuer {

    private VendingMachineState vendingMachineState;

    public VendingMachine(){}

    public VendingMachine(VendingMachineState vendingMachineState)
    {
        this.vendingMachineState = vendingMachineState;
    }

    public VendingMachineState getVendingMachineState()
    {
        return vendingMachineState;
    }

    public void setVendingMachineState(VendingMachineState vendingMachineState)
    {
        this.vendingMachineState = vendingMachineState;
    }

    @Override
    public String toString()
    {
        return "Distributore{" +
                "stato=" + vendingMachineState +
                '}';
    }


    @Override
    public Ticket issueTicket() {
        return null;
    }
}

