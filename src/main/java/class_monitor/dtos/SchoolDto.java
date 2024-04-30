package class_monitor.dtos;

import class_monitor.entity.School;
import class_monitor.entity.Teacher;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.HashSet;
import java.util.Set;

@Data
@NoArgsConstructor
public class SchoolDto {
    private Integer schoolId;
    private String schoolName;
    private String city;
    private String principalName;
    private Set<TeacherDto> teachers = new HashSet<>();

    public SchoolDto(School school){
        this.schoolId = school.getSchoolId();
        this.schoolName = school.getSchoolName();
        this.city = school.getCity();
        this.principalName = school.getPrincipalName();
        for(Teacher teacher: school.getTeachers()){
            this.teachers.add(new TeacherDto(teacher));
        }
    }

    public SchoolDto (Integer schoolId, String schoolName, String city, String principalName){
        this.schoolId = schoolId;
        this.schoolName = schoolName;
        this.city = city;
        this.principalName = principalName;
    }

    public School toSchool(){
        School school = new School();
        school.setSchoolId(schoolId);
        school.setSchoolName(schoolName);
        school.setCity(city);
        school.setPrincipalName(principalName);
        for(TeacherDto teacherDto: teachers){
            school.getTeachers().add(teacherDto.toTeacher());
        }
        return school;
    }
}
