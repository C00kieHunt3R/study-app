package orm.controller;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ProblemDetail;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import orm.dto.StudentPojo;
import orm.exception.EntityBuildingException;
import orm.exception.EntityIdDoesNotExistException;
import orm.service.GroupService;
import orm.service.StudentService;

import java.util.List;

@RestController
@RequestMapping("api/students")
public class StudentController {

    @Autowired
    StudentService studentService;
    @Autowired
    GroupService groupService;


    @GetMapping("/get-all/{groupId}")
    public List<StudentPojo> findAllByGroup(@PathVariable Long groupId) throws EntityIdDoesNotExistException {
//        try {
//            return ResponseEntity.ok(studentService.findAllByGroup(groupId));
////            return studentService.findAllByGroup(groupId);
//        } catch (EntityIdDoesNotExistException e) {
//            return ResponseEntity.of(ProblemDetail.forStatusAndDetail(HttpStatus.NOT_FOUND, e.getMessage())).build();
//        }
        return studentService.findAllByGroup(groupId);
    }

    @GetMapping("/get/{id}")
    public StudentPojo findById(@PathVariable Long id) throws EntityIdDoesNotExistException {
        return studentService.findById(id);
    }

    @PostMapping("/create/{groupId}")
    public String create(@PathVariable Long groupId, @RequestBody StudentPojo pojo) {
        try {
            return studentService.create(groupId, pojo).toString();
        } catch (EntityBuildingException
                 | EntityIdDoesNotExistException e) {
            return e.getMessage();
        }
    }

    @PutMapping("/update/{id}")
    public Long update(@PathVariable Long id, @RequestBody StudentPojo pojo) throws EntityIdDoesNotExistException {
        return studentService.update(id, pojo);
    }

    @DeleteMapping("/delete/{id}")
    public Long delete(@PathVariable Long id) {
        return studentService.delete(id);
    }


}
