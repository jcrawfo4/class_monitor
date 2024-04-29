package class_monitor.controller.dtos;

import class_monitor.entity.Student;
import class_monitor.entity.Teacher;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.HashSet;
import java.util.Set;

@Data
@NoArgsConstructor
public class TeacherDto {

    private Integer teacherId;
    private String teacherLastName;
    private String teacherFirstName;
    private Set<Student> students = new HashSet<>();

    public TeacherDto(Teacher teacher) {
        teacherId = teacher.getTeacherId();
        teacherLastName = teacher.getTeacherLastName();
        teacherFirstName = teacher.getTeacherFirstName();
        for(Student student: teacher.getStudents()){
            students.add(student);
        }
    }


    public Teacher toTeacher(){
        Teacher teacher = new Teacher();
        teacher.setTeacherId(teacherId);
        teacher.setTeacherLastName(teacherLastName);
        teacher.setTeacherLastName(teacherFirstName);

        return teacher;
    }



}
