package antoniogiovanni.marchese.entities;

import antoniogiovanni.marchese.enums.MeansType;

import javax.persistence.*;

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


    //CONSTRUCTOR
    public Means ( int capacity, MeansType meansType ) {
        this.capacity = capacity;
        this.meansType = meansType;
    }
    public Means (){}

    //SETTERS
    public void setCapacity ( int capacity ) {
        this.capacity = capacity;
    }
    public void setMeansType ( MeansType meansType ) {
        this.meansType = meansType;
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

    //TO_STRING

    @Override
    public String toString () {
        return "Mean has " + "id: " + id + "; the capacity is: " + capacity + "; the Means type is: " + meansType;
    }
}
