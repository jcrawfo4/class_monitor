package class_monitor.service;

import class_monitor.controller.dtos.SchoolDto;
import class_monitor.dao.SchoolDao;
import class_monitor.dao.StudentDao;
import class_monitor.entity.School;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.NoSuchElementException;

@Service
public class SchoolService {

    @Autowired
    SchoolDao schoolDao;

    @Autowired
    StudentDao studentDao;

    @Transactional(readOnly = false)
    public SchoolDto saveSchool(SchoolDto schoolDto) {
        School school = schoolDto.toSchool();
        School databaseSchool = schoolDao.save(school);
        return new SchoolDto(databaseSchool);
    }


    public SchoolDto getSchoolById(Integer schoolId) {
        School school = findSchoolById(schoolId);
        return new SchoolDto(school);
    }

    private School findSchoolById(Integer schoolId) {
        return schoolDao.findById(schoolId)
                .orElseThrow(() -> new NoSuchElementException("School with ID=" + schoolId + " not found!"));
    }
}
