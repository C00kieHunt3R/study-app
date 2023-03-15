package orm.service;

import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import orm.dto.GroupPojo;
import orm.dto.StudentPojo;
import orm.entity.Group;
import orm.entity.Student;
import orm.exception.EntityBuildingException;
import orm.exception.EntityIdDoesNotExistException;
import orm.repository.GroupRepository;
import orm.repository.StudentRepository;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class StudentService {

    @Autowired
    StudentRepository studentRepository;
    @Autowired
    GroupRepository groupRepository;

    public StudentPojo create(long groupId, StudentPojo pojo) throws EntityBuildingException, EntityIdDoesNotExistException {
        Student student;
        try {
            student = StudentPojo.toEntity(pojo);
        } catch (Exception e) {
            throw new EntityBuildingException("Не удалось создать сущность из полученного объекта!");
        }
        Optional<Group> optionalGroup = groupRepository.findById(groupId);
        if (optionalGroup.isPresent()) {
            student.setGroup(optionalGroup.get());
            return StudentPojo.fromEntity(studentRepository.save(student));
        } else {
            throw new EntityIdDoesNotExistException(
                    String.format("Не удалось создать новый объект Студент. Группа с ID \"%s\" не существует", groupId)
            );
        }
    }

    public List<StudentPojo> findAllByGroup(long groupId) throws EntityIdDoesNotExistException {
        Optional<Group> optionalGroup = groupRepository.findById(groupId);
        if (optionalGroup.isPresent()) {
            Group group = optionalGroup.get();
            List<StudentPojo> students = new ArrayList<>();
            for (Student student:
                 group.getStudents()) {
                students.add(StudentPojo.fromEntity(student));
            }
            return students;
        } else {
            throw new EntityIdDoesNotExistException(String.format("Группа с ID %s не существует", groupId));
        }
    }

    public StudentPojo findById(long id) throws EntityIdDoesNotExistException {
        Optional<Student> optionalStudent = studentRepository.findById(id);
        if (optionalStudent.isPresent()) {
            Student student = optionalStudent.get();
            return StudentPojo.fromEntity(student);
        } else {
            throw new EntityIdDoesNotExistException(String.format("Студент с ID %s не существует", id));
        }

    }

    public StudentPojo update(long id, StudentPojo pojo) throws EntityIdDoesNotExistException {
        Optional<Student> optionalStudent = studentRepository.findById(id);
        if (optionalStudent.isPresent()) {
            Student student = optionalStudent.get();
            student.setName(pojo.getName());
            student.setBirthdate(pojo.getBirthdate());
            student.setNumber(pojo.getNumber());
            student.setBirthdate(pojo.getBirthdate());
            studentRepository.save(student);
            return StudentPojo.fromEntity(student);
        } else {
            throw new EntityIdDoesNotExistException(String.format("Студент с ID %s не существует", id));
        }
    }

    @Transactional
    public Boolean delete(long id) throws EntityIdDoesNotExistException {
        Optional<Student> optionalStudent = studentRepository.findById(id);
        if (optionalStudent.isPresent()) {
            studentRepository.deleteById(id);
            return true;
        } else {
            throw new EntityIdDoesNotExistException(String.format("Студент с ID %s не существует", id));
        }
    }

}
