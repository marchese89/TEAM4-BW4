package antoniogiovanni.marchese.entities;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import java.time.LocalDate;

@Entity
public class Ticket extends Emittable{
    private Boolean valid;
    @Column(name = "endorsement_date")
    private LocalDate endorsementDate;
    @ManyToOne
    @JoinColumn(name = "means_id")
    private Means means;
}
