package class_monitor.entity;

import jakarta.persistence.*;
import lombok.Data;

import java.util.HashSet;
import java.util.Set;

@Entity
@Data
public class Student {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer studentId;
    private Integer schoolId;
    private String studentFirstName;
    private String studentLastName;
    private Integer studentGrade;
    private Integer merits;
    private Integer demerits;

    @ManyToMany(mappedBy = "students")
    private Set<Teacher> teachers = new HashSet<>();
}
