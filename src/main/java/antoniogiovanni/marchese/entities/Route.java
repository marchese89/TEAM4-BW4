package antoniogiovanni.marchese.entities;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
public class Route {
    @Id
    @GeneratedValue
    @Column(name="id")
    private long id;
    @Column(name="departure")
    private String departure;
    @Column(name="terminals")
    private String terminals;
    @Column(name="average_time")
    private int averageTime;
    @ManyToMany(mappedBy = "routes")
    private List<Means> means = new ArrayList<>();
    @OneToMany(mappedBy = "route")
    private List<Mileage> milages = new ArrayList<>();


    //CONSTRUCTORS
    public Route ( String departure, String terminals, int averageTime ) {
        this.departure = departure;
        this.terminals = terminals;
        this.averageTime = averageTime;
    }

    //GETTER
    public long getId () {
        return id;
    }

    public String getDeparture () {
        return departure;
    }

    public String getTerminals () {
        return terminals;
    }

    //SETTER
    public void setDeparture ( String departure ) {
        this.departure = departure;
    }

    public void setTerminals ( String terminals ) {
        this.terminals = terminals;
    }

    //TO_STRING

    @Override
    public String toString () {
        return "Route id is: "   + id + "; departure from: " + departure + "; terminals to: " + terminals;
    }
}
