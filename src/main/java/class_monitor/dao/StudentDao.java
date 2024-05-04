package class_monitor.dao;

import class_monitor.entity.Student;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface StudentDao extends JpaRepository<Student, Integer> {
    Optional<Student> findByStudentFirstNameAndStudentLastName(String studentFirstName, String studentLastName);
}
