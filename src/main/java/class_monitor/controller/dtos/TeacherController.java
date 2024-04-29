package class_monitor.controller.dtos;

import class_monitor.service.TeacherService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/class_monitor")
@Slf4j
public class TeacherController {

    @Autowired
    private TeacherService teacherService;

    @PostMapping("/teacher")
    @ResponseStatus(code = HttpStatus.CREATED)
    private TeacherDto createTeacher(@RequestBody TeacherDto teacherDto) {
        log.info("Creating teacher data: {}", teacherDto);
        return teacherService.saveTeacher(teacherDto);
    }

    @GetMapping("/teacher/{teacherId}")
    public TeacherDto getTeacherById(@PathVariable Integer teacherId) {
        log.info("Getting teacher by id: {}", teacherId);
        return teacherService.getTeacherById(teacherId);
    }

    @GetMapping("/{schoolId}/teachers")
    public List<TeacherDto> getAllTeachersForSchool(@PathVariable Integer schoolId) {
        log.info("Retrieving all teachers for school with id: {}", schoolId);
        return teacherService.retrieveAllTeachers(schoolId);
    }

    @DeleteMapping("/teacher/{teacherId}")
    public Map<String, String> deleteTeacher(@PathVariable Integer teacherId) {
        log.info("Deleting teacher by id: {}", teacherId);
        teacherService.deleteTeacher(teacherId);
        return Map.of("message", "Teacher with id = " + teacherId + " deleted successfully");
    }

    @PutMapping("/teacher/{teacherId}")
    public TeacherDto updateTeacher(@PathVariable Integer teacherId, @RequestBody TeacherDto teacherDto) {
        teacherDto.setTeacherId(teacherId);
        log.info("Updating teacher by id: {}", teacherDto);
        return teacherService.saveTeacher(teacherDto);
    }
}
