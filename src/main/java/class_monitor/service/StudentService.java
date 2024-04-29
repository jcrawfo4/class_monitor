package class_monitor.service;

import class_monitor.controller.dtos.StudentDto;
import class_monitor.dao.SchoolDao;
import class_monitor.dao.StudentDao;
import class_monitor.dao.TeacherDao;
import class_monitor.entity.Student;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Objects;
import java.util.Optional;

@Service
public class StudentService {
    @Autowired
    private SchoolDao schoolDao;

    @Autowired
    private StudentDao studentDao;

    @Autowired
    private TeacherDao teacherDao;

    @Transactional(readOnly = false)
    public StudentDto saveStudent(StudentDto studentDto){
        Student student = studentDto.toStudent();
        Student databaseStudent = studentDao.save(student);
        return new StudentDto(databaseStudent);
    }

    private void setFieldsInStudent(Student student, StudentDto studentDto) {
    }

    private Student findOrCreateStudent(Integer studentId, String studentFirstName, String studentLastName) {
        Student student;
        if (Objects.isNull(studentId)) {
            Optional<Student> studentOptional = studentDao.findByStudentFirstNameAndStudentLastName(studentFirstName, studentLastName);
            if (studentOptional.isPresent()) {
                throw new DuplicateKeyException("Student with full name = " + studentFirstName +" "+ studentLastName + " already exists");
            } else {
                student = new Student();
            }
        } else {
            student = findStudentById(studentId);
        }
        return student;
    }

    private Student findStudentById(Integer studentId) {
        return studentDao.findById(studentId)
                .orElseThrow();
    }


    public StudentDto getStudentById(Integer studentId) {
        Student student = findStudentById(studentId);
        return new StudentDto(student);
    }
}
