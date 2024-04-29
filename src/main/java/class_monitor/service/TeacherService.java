package class_monitor.service;

import class_monitor.controller.dtos.TeacherDto;
import class_monitor.dao.SchoolDao;
import class_monitor.dao.StudentDao;
import class_monitor.dao.TeacherDao;
import class_monitor.entity.Teacher;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.NoSuchElementException;
import java.util.Objects;
import java.util.Optional;

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
        setFieldsInTeacher(teacher, teacherDto);
        return new TeacherDto(teacherDao.save(teacher));
    }

    private void setFieldsInTeacher(Teacher teacher, TeacherDto teacherDto) {
        teacher.setTeacherId(teacherDto.getTeacherId());
        teacher.setTeacherFirstName(teacherDto.getTeacherFirstName());
        teacher.setTeacherLastName(teacherDto.getTeacherLastName());
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
}
