package orm.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import orm.dto.GroupPojo;
import orm.dto.StudentPojo;
import orm.entity.Group;
import orm.entity.Student;
import orm.exception.EntityIdDoesNotExistException;
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

    public GroupPojo create(GroupPojo pojo) {
        if (pojo.getStudents() == null) {
            pojo.setStudents(new ArrayList<>());
        }
        return GroupPojo.fromEntity(groupRepository.save(GroupPojo.toEntity(pojo)));
    }

    @Transactional
    public Boolean delete(Long id) {
        Optional<Group> optionalGroup = groupRepository.findById(id);
        if (optionalGroup.isPresent()) {
            groupRepository.deleteById(id);
            return true;
        }
        return false;
    }


    public GroupPojo update(long id, GroupPojo pojo) throws EntityIdDoesNotExistException {
        Optional<Group> optionalGroup = groupRepository.findById(id);
        if (optionalGroup.isPresent()) {
            Group group = optionalGroup.get();
            group.setName(pojo.getName());
//            List<Student> students = new ArrayList<>();
//            for (StudentPojo studentPojo:
//                    pojo.getStudents()) {
//                students.add(StudentPojo.toEntity(studentPojo));
//            }
//            group.setStudents(students);
            groupRepository.save(group);
            return GroupPojo.fromEntity(group);
        } else {
            throw new EntityIdDoesNotExistException(String.format("Группа с ID %s не существует", id));
        }
    }

    public GroupPojo findById(Long id) throws EntityIdDoesNotExistException {
        Optional<Group> optionalGroup = groupRepository.findById(id);
        if (optionalGroup.isPresent()) {
            return GroupPojo.fromEntity(optionalGroup.get());
        } else {
            throw new EntityIdDoesNotExistException(String.format("Группа с ID %s не существует", id));
        }
    }


}
