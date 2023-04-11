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
    public List<GroupDto> findAll(@RequestBody String name) {
        return groupService.findAll(name);
    }

    @GetMapping("/get/{id}")
    public GroupDto findById(@PathVariable Long id) throws EntityIdDoesNotExistException {
        return groupService.findById(id);
    }

    @PostMapping("/create")
    public GroupDto create(@RequestBody GroupDto pojo) {
        return groupService.create(pojo);
    }

    @DeleteMapping("/delete/{id}")
    public Boolean delete(@PathVariable Long id) {
        return groupService.delete(id);
    }

    @PutMapping("/update/{id}")
    public GroupDto update(@PathVariable Long id, @RequestBody GroupDto pojo) throws EntityIdDoesNotExistException {
        return groupService.update(id, pojo);
    }

}
