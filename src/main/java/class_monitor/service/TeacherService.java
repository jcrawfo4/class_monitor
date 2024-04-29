package class_monitor.service;

import class_monitor.controller.dtos.TeacherDto;
import class_monitor.dao.SchoolDao;
import class_monitor.dao.StudentDao;
import class_monitor.dao.TeacherDao;
import class_monitor.entity.School;
import class_monitor.entity.Student;
import class_monitor.entity.Teacher;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.*;

@Service
public class TeacherService {
    @Autowired
    private SchoolDao schoolDao;

    @Autowired
    private StudentDao studentDao;

    @Autowired
    private TeacherDao teacherDao;

    @Transactional(readOnly = false)
    public TeacherDto saveTeacher(TeacherDto teacherDto) {
        Integer teacherId = teacherDto.getTeacherId();
        String teacherFirstName = teacherDto.getTeacherFirstName();
        String teacherLastName = teacherDto.getTeacherLastName();

        Teacher teacher = findOrCreateTeacher(teacherId, teacherFirstName, teacherLastName);
        Integer schoolId = teacherDto.getSchoolId();
        School school = schoolDao.findById(schoolId)
                .orElseThrow(() -> new NoSuchElementException("School with ID=" + schoolId + " not found!"));
        teacher.setSchool(school);
        setFieldsInTeacher(teacher, teacherDto);
        Teacher savedTeacher = teacherDao.save(teacher);
        TeacherDto responseTeacherDto = new TeacherDto(savedTeacher);
        responseTeacherDto.setSchoolId(savedTeacher.getSchool().getSchoolId());
        return responseTeacherDto;
    }

    private void setFieldsInTeacher(Teacher teacher, TeacherDto teacherDto) {
        teacher.setTeacherId(teacherDto.getTeacherId());
        teacher.setTeacherFirstName(teacherDto.getTeacherFirstName());
        teacher.setTeacherLastName(teacherDto.getTeacherLastName());

        Integer schoolId = teacherDto.getSchoolId();
        School school = schoolDao.findById(schoolId)
                .orElseThrow(() -> new NoSuchElementException("School with ID=" + schoolId + " not found!"));
        teacher.setSchool(school);
        Set<Student> students = new HashSet<>();
        for (Integer studentId : teacherDto.getStudentIds()) {
            Student student = studentDao.findById(studentId)
                    .orElseThrow(() -> new NoSuchElementException("Student with ID=" + studentId + " not found!"));
            students.add(student);
        }
        teacher.setStudents(students);
    }

    private Teacher findOrCreateTeacher(Integer teacherId, String teacherFirstName, String teacherLastName) {
        Teacher teacher;
        if (Objects.isNull(teacherId)) {
            Optional<Teacher> teacherOptional = teacherDao.findByTeacherFirstNameAndTeacherLastName(teacherFirstName, teacherLastName);
            if (teacherOptional.isPresent()) {
                throw new DuplicateKeyException("Teacher with full name = " + teacherFirstName +" "+ teacherLastName + " already exists");
            } else {
                teacher = new Teacher();
            }
        } else {
            teacher = findTeacherById(teacherId);
        }
        return teacher;
    }

    private Teacher findTeacherById(Integer teacherId) {
        return teacherDao.findById(teacherId)
                .orElseThrow(() -> new NoSuchElementException("Teacher with ID=" + teacherId + " not found!"));
    }

    public TeacherDto getTeacherById(Integer teacherId) {
        Teacher teacher = findTeacherById(teacherId);
        return new TeacherDto(teacher);
    }

    @Transactional(readOnly = true)
    public List<TeacherDto> retrieveAllTeachers(Integer schoolId) {
        List<Teacher> teachers = teacherDao.findAllBySchool_SchoolId(schoolId);
        List<TeacherDto> teacherDtos = new LinkedList<>();
        teachers.sort(Comparator.comparing(Teacher::getTeacherLastName)
                .thenComparing(Teacher::getTeacherFirstName));
        for (Teacher teacher : teachers) {
            TeacherDto teacherDto = new TeacherDto(teacher);
            teacherDtos.add(teacherDto);
        }
        return teacherDtos;
    }

    @Transactional(readOnly = false)
    public void deleteTeacher(Integer teacherId) {
        Teacher teacher = findTeacherById(teacherId);
        teacherDao.delete(teacher);
    }
}
