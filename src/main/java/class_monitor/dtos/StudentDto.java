package class_monitor.dtos;


import class_monitor.entity.Student;

import jakarta.persistence.criteria.CriteriaBuilder;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@Data
public class StudentDto {

    private Integer studentId;
    private String studentFirstName;
    private String studentLastName;
    private Integer merits;
    private Integer demerits;
    private Integer schoolId;
    private Integer studentGrade;

    public StudentDto(Student student){
        this.studentId = student.getStudentId();
        this.studentFirstName = student.getStudentFirstName();
        this.studentLastName = student.getStudentLastName();
        this.merits = student.getMerits();
        this.demerits = student.getDemerits();
        this.schoolId = student.getStudentId();
        this.studentGrade = student.getStudentGrade();
    }


    public Student toStudent() {
        Student student = new Student();
        student.setStudentId(this.studentId);
        student.setStudentFirstName(this.studentFirstName);
        student.setStudentLastName(this.studentLastName);
        student.setMerits(this.merits);
        student.setDemerits(this.demerits);
        student.setSchoolId(this.schoolId);
        student.setStudentGrade(this.studentGrade);
        return student;
    }
}
