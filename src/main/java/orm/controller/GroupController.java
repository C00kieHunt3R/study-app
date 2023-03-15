package orm.controller;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import orm.dto.GroupPojo;
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


    @GetMapping
    public List<GroupPojo> findAll(String name) {
//        List<GroupPojo> pojos = groupService.findAll(name);
        return groupService.findAll(name);

    }

    @PostMapping("/create")
    public long create(@RequestBody GroupPojo pojo) {
        return groupService.create(pojo);
    }

    @DeleteMapping("/delete/{id}")
    public Long delete(@PathVariable Long id) {
        return groupService.delete(id);
    }


}
