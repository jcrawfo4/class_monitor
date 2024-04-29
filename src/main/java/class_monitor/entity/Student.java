package class_monitor.entity;

import jakarta.persistence.*;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

import java.util.HashSet;
import java.util.Set;

@Entity
@Data
public class Student {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer studentId;
    private String studentFirstName;
    private String studentLastName;
    private Integer studentGrade;
    private Integer merits;
    private Integer demerits;


    @ToString.Exclude
    @EqualsAndHashCode.Exclude
    @ManyToMany(mappedBy = "students")
    private Set<Teacher> teachers = new HashSet<>();
}
