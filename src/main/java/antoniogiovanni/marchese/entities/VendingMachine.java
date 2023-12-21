package antoniogiovanni.marchese.entities;
import antoniogiovanni.marchese.enums.SubscriptionType;
import antoniogiovanni.marchese.enums.VendingMachineState;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "vending_machines")
@DiscriminatorValue("vending_machine")
public class VendingMachine extends CardTicketIssuer {
    @Enumerated(EnumType.STRING)
    private VendingMachineState vendingMachineState;

    //CONSTRUCTORS
    public VendingMachine(){}
    public VendingMachine(VendingMachineState vendingMachineState)
    {
        this.vendingMachineState = vendingMachineState;
    }

    //GETTER
    public VendingMachineState getVendingMachineState()
    {
        return vendingMachineState;
    }

    //SETTER
    public void setVendingMachineState(VendingMachineState vendingMachineState)
    {
        this.vendingMachineState = vendingMachineState;
    }

    //TO_STRING
    @Override
    public String toString()
    {
        return "Vending machine state: " +
               vendingMachineState ;
    }

}

