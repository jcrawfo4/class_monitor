package class_monitor.controller.dtos;


import class_monitor.entity.Student;

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


    public Student toStudent() {
        Student student = new Student();
        student.setStudentId(studentId);
        student.setStudentFirstName(studentFirstName);
        student.setStudentLastName(studentLastName);
        student.setMerits(merits);
        student.setDemerits(demerits);
        return student;
    }
}
