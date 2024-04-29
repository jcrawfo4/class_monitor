package class_monitor.dao;

import class_monitor.entity.Teacher;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface TeacherDao extends JpaRepository<Teacher, Integer> {
    Optional<Teacher> findByTeacherFirstNameAndTeacherLastName(String teacherFirstName, String teacherLastName);

    List<Teacher> findAllBySchool_SchoolId(Integer schoolId);
    //SELECT t.* FROM teacher t WHERE t.school_id = ?SELECT t.* FROM teacher t WHERE t.school_id = ?
}