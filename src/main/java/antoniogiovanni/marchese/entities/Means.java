package antoniogiovanni.marchese.entities;

import antoniogiovanni.marchese.enums.MeansType;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Entity
public class Means {
    @Id
    @GeneratedValue
    @Column(name="id")
    private long id;
    @Column(name="capacity")
    private int capacity;
    @Enumerated(EnumType.STRING)
    @Column(name="means_type")
    private MeansType meansType;
    @ManyToMany
    @JoinTable(
            name="means_period",
            joinColumns = @JoinColumn(name="means_id"),
            inverseJoinColumns = @JoinColumn(name="period_id")
    )
    private List<PeriodStateMeansOfTransport> periods= new ArrayList<>();

    public void addPeriodStateMeansOfTransport(PeriodStateMeansOfTransport period){
        periods.add(period);
    }
    @ManyToMany
    @JoinTable(
            name="means_route",
            joinColumns = @JoinColumn(name="means_id"),
            inverseJoinColumns = @JoinColumn(name="route_id"))
    private List<Route> routes= new ArrayList<>();

    @OneToMany(mappedBy = "means")
    private List<Ticket> ticketList;

    //CONSTRUCTOR
    public Means (  MeansType meansType ) {
        this.meansType = meansType;
        if(meansType == MeansType.BUS){
            this.capacity = 50;
        }else{
            this.capacity = 30;
        }
    }
    public Means (){}

    //SETTERS
    public void setCapacity ( int capacity ) {
        this.capacity = capacity;
    }
    public void setMeansType ( MeansType meansType ) {
        this.meansType = meansType;
    }

    public void addRoute ( Route route ) {
        this.routes.add(route);
    }

    //GETTER
    public long getId () {
        return id;
    }
    public int getCapacity () {
        return capacity;
    }
    public MeansType getMeansType () {
        return meansType;
    }

    public Route getRouteById (long id) {

        for(Route r:routes){
            if(r.getId() == id){
                return r;
            }
        }

        return null;
    }
    //TO_STRING

    @Override
    public String toString () {
        return "Means has id: " + id + "; the capacity is: " + capacity + "; the Means type is: " + meansType;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Means means = (Means) o;
        return id == means.id && capacity == means.capacity && meansType == means.meansType;
    }
}
