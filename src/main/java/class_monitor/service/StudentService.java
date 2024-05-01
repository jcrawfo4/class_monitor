package class_monitor.service;

import class_monitor.dao.SchoolDao;
import class_monitor.dao.StudentDao;
import class_monitor.dao.TeacherDao;
import class_monitor.dtos.StudentDto;
import class_monitor.entity.Student;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Map;
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
        Integer studentId = studentDto.getStudentId();
        String studentFirstName = studentDto.getStudentFirstName();
        String studentLastName = studentDto.getStudentLastName();
        Student student = findOrCreateStudent(studentId, studentFirstName, studentLastName);
        setFieldsInStudent(student, studentDto);
        Student databaseStudent = studentDao.save(student);
        return new StudentDto(databaseStudent);
    }

    private void setFieldsInStudent(Student student, StudentDto studentDto) {
        student.setStudentFirstName(studentDto.getStudentFirstName());
        student.setStudentLastName(studentDto.getStudentLastName());
        student.setMerits(studentDto.getMerits());
        student.setDemerits(studentDto.getDemerits());
        student.setSchoolId(studentDto.getSchoolId());
        student.setStudentGrade(studentDto.getStudentGrade());
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

    public Map<String, String> deleteStudent(Integer studentId) {
        studentDao.deleteById(studentId);
        return Map.of("message", "Student with id = " + studentId + " deleted successfully");
    }


}
