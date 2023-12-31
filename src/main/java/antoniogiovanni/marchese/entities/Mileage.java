package antoniogiovanni.marchese.entities;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
//numero di volte che un mezzo percorre una tratta
@NamedQuery(name = "meansRouteCount",query = "SELECT COUNT(m) FROM Mileage m WHERE m.means = :means AND m.route = :route")
public class Mileage {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="id")
    private long id;

    @Column(name="date_and_time")
    private LocalDateTime dateAndTime;
    @Column(name="duration_in_minutes")
    private int duration;
    @ManyToOne
    @JoinColumn(name ="route_id")
    private Route route;
    @ManyToOne
    @JoinColumn(name ="means_id")
    private Means means;

    //CONSTRUCTORS
    public Mileage ( LocalDateTime dateAndTime, int duration, Route route, Means means ) {
        this.dateAndTime = dateAndTime;
        this.duration = duration;
        this.route = route;
        this.means = means;
    }

    public Mileage(){}


    //SETTERS
    public void setDate ( LocalDateTime date ) {
        this.dateAndTime = date;
    }

    public void setDuration ( int duration ) {
        this.duration = duration;
    }

    //GETTERS
    public long getId () {
        return id;
    }

    public LocalDateTime getDate () {
        return dateAndTime;
    }

    public int getDuration () {
        return duration;
    }

    //TO_STRING

    @Override
    public String toString () {
        return "Mileage with id: " + id + "; date: " + dateAndTime + "; duration: " + duration;
    }
}
