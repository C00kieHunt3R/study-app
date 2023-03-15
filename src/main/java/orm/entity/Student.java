package orm.entity;

import jakarta.persistence.*;
import lombok.*;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.util.Date;

@Entity
@Table(name="students", schema="public")
@Getter
@Setter
@Builder
public class Student {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    private String name;
    @Temporal(value = TemporalType.DATE)
    private Date birthdate;
    private int number;

    public Student(long id, String name, Date birthdate, int number, Group group) {
        this.id = id;
        this.name = name;
        this.birthdate = birthdate;
        this.number = number;
        this.group = group;
    }

    public Student() {}

    @ManyToOne(fetch = FetchType.EAGER, cascade = CascadeType.REFRESH)
    @JoinColumn(name = "group_id", foreignKey = @ForeignKey(ConstraintMode.NO_CONSTRAINT))
    private Group group;
}
