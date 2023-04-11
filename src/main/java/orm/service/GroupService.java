package orm.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import orm.dto.GroupDto;
import orm.model.Group;
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

    public List<GroupDto> findAll(String name) {
        List<GroupDto> result = new ArrayList<>();
        for (Group group : name == null ? groupRepository.findAll() : groupRepository.findAllByNameContainingIgnoreCase(name)) {
            result.add(GroupDto.fromEntity(group));
        }
        return result;
    }

    public GroupDto create(GroupDto pojo) {
        if (pojo.getStudents() == null) {
            pojo.setStudents(new ArrayList<>());
        }
        return GroupDto.fromEntity(groupRepository.save(GroupDto.toEntity(pojo)));
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


    public GroupDto update(long id, GroupDto pojo) throws EntityIdDoesNotExistException {
//        groupRepository.findById(id).ifPresentOrElse(group -> {
//                    group.setName(pojo.getName());
//                    groupRepository.save(group);
//                    return GroupPojo.fromEntity(group);
//                },
//                () -> {
//                    throw new EntityIdDoesNotExistException(String.format("Группа с ID %s не существует", id));
//                });
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
            return GroupDto.fromEntity(group);
        } else {
            throw new EntityIdDoesNotExistException(String.format("Группа с ID %s не существует", id));
        }
    }

    public GroupDto findById(Long id) throws EntityIdDoesNotExistException {
        Optional<Group> optionalGroup = groupRepository.findById(id);
        if (optionalGroup.isPresent()) {
            return GroupDto.fromEntity(optionalGroup.get());
        } else {
            throw new EntityIdDoesNotExistException(String.format("Группа с ID %s не существует", id));
        }
    }


}
