package class_monitor.controller;

import class_monitor.dtos.SchoolDto;
import class_monitor.service.SchoolService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/class_monitor")
@Slf4j
public class SchoolController {
    @Autowired
    SchoolService schoolService;

    @PostMapping("/school")
    @ResponseStatus(code = HttpStatus.CREATED)
    private SchoolDto createSchool(@RequestBody SchoolDto schoolDto) {
        log.info("Creating school data: {}", schoolDto);
        return schoolService.saveSchool(schoolDto);
    }

    @GetMapping("/school/{schoolId}")
    public SchoolDto getSchoolById(@PathVariable Integer schoolId) {
        log.info("Getting school by id: {}", schoolId);
        return schoolService.getSchoolById(schoolId);
    }

    @DeleteMapping("/school/{schoolId}")
    public void deleteSchool(@PathVariable Integer schoolId) {
        log.info("Deleting school by id: {}", schoolId);
        schoolService.deleteSchool(schoolId);
    }
}
