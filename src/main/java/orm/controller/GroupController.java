package orm.controller;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import orm.dto.GroupDto;
import orm.exception.EntityIdDoesNotExistException;
import orm.service.GroupService;
import orm.service.StudentService;

import java.util.List;

@RestController
@RequestMapping("/api/groups")
@CrossOrigin
public class GroupController {

    @Autowired
    GroupService groupService;
    @Autowired
    StudentService studentService;


    @GetMapping("/get-all")
    public List<GroupDto> findAll() {
        return groupService.findAll();
    }

    @GetMapping("/get")
    public GroupDto findById(@RequestParam(value = "id") Long id) throws EntityIdDoesNotExistException {
        return groupService.findById(id);
    }

    @PostMapping("/create")
    public GroupDto create(@RequestBody GroupDto pojo) {
        return groupService.create(pojo);
    }

    @DeleteMapping("/delete")
    public Boolean delete(@RequestParam(value = "id") Long id) {
        return groupService.delete(id);
    }

    @PutMapping("/update")
    public GroupDto update(@RequestParam(value = "id") Long id, @RequestBody GroupDto pojo) throws EntityIdDoesNotExistException {
        return groupService.update(id, pojo);
    }

}
