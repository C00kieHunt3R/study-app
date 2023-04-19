package orm.controller;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import orm.dto.StudentDto;
import orm.exception.EntityBuildingException;
import orm.exception.EntityIdDoesNotExistException;
import orm.service.GroupService;
import orm.service.StudentService;

import java.util.List;

@RestController
@RequestMapping("/api/students")
@CrossOrigin
public class StudentController {

    @Autowired
    StudentService studentService;
    @Autowired
    GroupService groupService;


    @GetMapping("/get-all/{groupId}")
    public List<StudentDto> findAllByGroup(@PathVariable Long groupId) throws EntityIdDoesNotExistException {
//        try {
//            return ResponseEntity.ok(studentService.findAllByGroup(groupId));
////            return studentService.findAllByGroup(groupId);
//        } catch (EntityIdDoesNotExistException e) {
//            return ResponseEntity.of(ProblemDetail.forStatusAndDetail(HttpStatus.NOT_FOUND, e.getMessage())).build();
//        }
        return studentService.findAllByGroup(groupId);
    }

    @GetMapping("/get/{id}")
    public StudentDto findById(@PathVariable Long id) throws EntityIdDoesNotExistException {
        return studentService.findById(id);
    }

    @PostMapping("/create")
    public StudentDto create(@RequestParam(value = "groupId") Long groupId, @RequestBody StudentDto dto) throws EntityBuildingException, EntityIdDoesNotExistException {
        return studentService.create(groupId, dto);
    }

    @PutMapping("/update")
    public StudentDto update(@RequestParam(value = "id") Long id, @RequestBody StudentDto dto) throws EntityIdDoesNotExistException {
        return studentService.update(id, dto);
    }

    @DeleteMapping("/delete")
    public Boolean delete(@RequestParam(value = "id") Long id) throws EntityIdDoesNotExistException {
        return studentService.delete(id);
    }


}
