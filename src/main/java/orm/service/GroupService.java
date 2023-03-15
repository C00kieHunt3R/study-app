package orm.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import orm.dto.GroupPojo;
import orm.dto.StudentPojo;
import orm.entity.Group;
import orm.entity.Student;
import orm.repository.GroupRepository;
import orm.repository.StudentRepository;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class GroupService {

    @Autowired
    private GroupRepository groupRepository;

    @Autowired
    private StudentRepository studentRepository;

    public List<GroupPojo> findAll(String name) {
        List<GroupPojo> result = new ArrayList<>();
        for (Group group : name == null ? groupRepository.findAll() : groupRepository.findAllByNameContainingIgnoreCase(name)) {
            result.add(GroupPojo.fromEntity(group));
        }
        return result;
    }

    public long create(GroupPojo pojo) {
        if (pojo.getStudents() == null) {
            pojo.setStudents(new ArrayList<>());
        }
        return groupRepository.save(GroupPojo.toEntity(pojo)).getId();
    }

    @Transactional
    public Long delete(Long id) {
        return groupRepository.deleteAllById(id);
    }


    public long update(long id, GroupPojo pojo) {
        Optional<Group> optionalGroup = groupRepository.findById(id);
        if (optionalGroup.isPresent()) {
            Group group = optionalGroup.get();
            group.setName(pojo.getName());
            List<Student> students = new ArrayList<>();
            for (StudentPojo studentPojo:
                    pojo.getStudents()) {
                students.add(StudentPojo.toEntity(studentPojo));
            }
            group.setStudents(students);
            return group.getId();
        } else return 0;
    }


}
