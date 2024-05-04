package class_monitor.controller;

import class_monitor.dtos.StudentDto;
import class_monitor.service.StudentService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("class_monitor")
@Slf4j
public class StudentController {

    @Autowired
    StudentService studentService;

    @PostMapping("/student")
    @ResponseStatus(code = HttpStatus.CREATED)
    private StudentDto createStudent(@RequestBody StudentDto studentDto) {
        log.info("Creating student data: {}", studentDto);
        return studentService.saveStudent(studentDto);
    }

    @GetMapping("student/{studentId}")
    public StudentDto getStudentById(@PathVariable Integer studentId) {
        log.info("Getting student by id: {}", studentId);
        return studentService.getStudentById(studentId);
    }

    @DeleteMapping("student/{studentId}")
    public void deleteStudent(@PathVariable Integer studentId) {
        log.info("Deleting student by id: {}", studentId);
        studentService.deleteStudent(studentId);
    }

    @PutMapping("student/{studentId}")
    public StudentDto updateStudent(@PathVariable Integer studentId, @RequestBody StudentDto studentDto) {
        studentDto.setStudentId(studentId);
        log.info("Updating student by id: {}", studentDto);
        return studentService.saveStudent(studentDto);
    }


}
