package orm.controller;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import orm.dto.GroupPojo;
import orm.exception.EntityIdDoesNotExistException;
import orm.service.GroupService;
import orm.service.StudentService;

import java.util.List;

@RestController
@RequestMapping("/api/groups")
public class GroupController {

    @Autowired
    GroupService groupService;
    @Autowired
    StudentService studentService;


    @GetMapping("/get-all")
    public List<GroupPojo> findAll(@RequestBody String name) {
        return groupService.findAll(name);
    }

    @GetMapping("/get/{id}")
    public GroupPojo findById(@PathVariable Long id) throws EntityIdDoesNotExistException {
        return groupService.findById(id);
    }

    @PostMapping("/create")
    public GroupPojo create(@RequestBody GroupPojo pojo) {
        return groupService.create(pojo);
    }

    @DeleteMapping("/delete/{id}")
    public Boolean delete(@PathVariable Long id) {
        return groupService.delete(id);
    }

    @PutMapping("/update/{id}")
    public GroupPojo update(@PathVariable Long id, @RequestBody GroupPojo pojo) throws EntityIdDoesNotExistException {
        return groupService.update(id, pojo);
    }

}
