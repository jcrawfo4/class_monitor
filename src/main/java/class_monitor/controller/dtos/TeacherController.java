package class_monitor.controller.dtos;

import class_monitor.service.TeacherService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

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
}
