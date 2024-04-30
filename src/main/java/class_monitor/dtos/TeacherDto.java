package class_monitor.dtos;

import class_monitor.entity.School;
import class_monitor.entity.Student;
import class_monitor.entity.Teacher;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Data
@NoArgsConstructor
public class TeacherDto {

    private Integer teacherId;
    private String teacherLastName;
    private String teacherFirstName;
    private List<Integer> studentIds;
    private Integer schoolId;

    public TeacherDto(Teacher teacher) {
        this.teacherId = teacher.getTeacherId();
        this.teacherFirstName = teacher.getTeacherFirstName();
        this.teacherLastName = teacher.getTeacherLastName();
        this.schoolId = teacher.getSchool().getSchoolId();
        this.studentIds = new ArrayList<>();
        for(Student student: teacher.getStudents()){
            this.studentIds.add(student.getStudentId());
        }
    }


    public Teacher toTeacher(){
        Teacher teacher = new Teacher();
        teacher.setTeacherId(teacherId);
        teacher.setTeacherLastName(teacherLastName);
        teacher.setTeacherFirstName(teacherFirstName);


        return teacher;
    }



}
