package antoniogiovanni.marchese.entities;

import javax.persistence.*;
import java.time.LocalDate;

@Entity
@Inheritance(strategy = InheritanceType.JOINED)
public abstract class Emittable {
    @Id
    @GeneratedValue
    private long id;
    @Column(name = "issue_date")
    LocalDate IssueDate;

    public long getId() {
        return id;
    }

    public LocalDate getIssueDate() {
        return IssueDate;
    }

    public void setIssueDate(LocalDate issueDate) {
        IssueDate = issueDate;
    }

    @Override
    public String toString() {
        return
                "id=" + id +
                ", IssueDate=" + IssueDate;
    }
}
