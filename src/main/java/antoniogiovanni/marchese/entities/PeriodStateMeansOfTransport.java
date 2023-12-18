package antoniogiovanni.marchese.entities;

import antoniogiovanni.marchese.enums.MeansState;

import javax.persistence.*;
import java.time.LocalDate;

@Entity
public class PeriodStateMeansOfTransport {
    @Id
    @GeneratedValue
    @Column(name = "id")
    private int id;
    @Column(name = "start_date")
    private LocalDate startDate;
    @Column(name = "end_date")
    private LocalDate endDate;

    @Enumerated(EnumType.STRING)
    @Column(name = "means_state")
    private MeansState state;

    //    COSTRUTTORI
    public PeriodStateMeansOfTransport(LocalDate startDate, LocalDate endDate, MeansState state) {
        this.startDate = startDate;
        this.endDate = endDate;
        this.state = state;
    }

    public PeriodStateMeansOfTransport() {
    }

    //GETTER
    public int getId() {
        return id;
    }

    public LocalDate getStartDate() {
        return startDate;
    }

    public LocalDate getEndDate() {
        return endDate;
    }

    public MeansState getState() {
        return state;
    }
//    SETTER

    public void setStartDate(LocalDate startDate) {
        this.startDate = startDate;
    }

    public void setEndDate(LocalDate endDate) {
        this.endDate = endDate;
    }

    public void setState(MeansState state) {
        this.state = state;
    }

    //TOSTRING
    @Override
    public String toString() {
        return "PeriodStateMeansOfTransport " +
                "id: " + id +
                "; startDate: " + startDate +
                "; endDate: " + endDate +
                "; state: " + state;

    }
}
