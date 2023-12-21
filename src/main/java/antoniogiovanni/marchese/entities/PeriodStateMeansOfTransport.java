package antoniogiovanni.marchese.entities;

import antoniogiovanni.marchese.enums.MeansState;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Entity
public class PeriodStateMeansOfTransport {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private long id;
    @Column(name = "start_date")
    private LocalDate startDate;
    @Column(name = "end_date")
    private LocalDate endDate;
    @Enumerated(EnumType.STRING)
    @Column(name = "means_state")
    private MeansState state;
    @ManyToMany(mappedBy = "periods")
    private List<Means> means= new ArrayList<>();

    //CONSTRUCTORS
    public PeriodStateMeansOfTransport(LocalDate startDate, LocalDate endDate, MeansState state) {
        this.startDate = startDate;
        this.endDate = endDate;
        this.state = state;
    }

    public PeriodStateMeansOfTransport() {
    }

    //GETTERS
    public long getId() {
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


    //SETTERS
    public void setStartDate(LocalDate startDate) {
        this.startDate = startDate;
    }

    public void setEndDate(LocalDate endDate) {
        this.endDate = endDate;
    }

    public void setState(MeansState state) {
        this.state = state;
    }

    //TO_STRING
    @Override
    public String toString() {
        return "Period id: " + id +
                "; startDate: " + startDate +
                "; endDate: " + endDate +
                "; state: " + state;

    }
}
