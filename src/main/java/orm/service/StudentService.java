package orm.service;

import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import orm.dto.StudentDto;
import orm.model.Group;
import orm.model.Student;
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

    public StudentDto create(long groupId, StudentDto dto) throws EntityBuildingException, EntityIdDoesNotExistException {
        Student student;
        try {
            student = StudentDto.toEntity(dto);
        } catch (Exception e) {
            throw new EntityBuildingException("Не удалось создать сущность из полученного объекта!");
        }
        Optional<Group> optionalGroup = groupRepository.findById(groupId);
        if (optionalGroup.isPresent()) {
            student.setGroup(optionalGroup.get());
            return StudentDto.fromEntity(studentRepository.save(student));
        } else {
            throw new EntityIdDoesNotExistException(
                    String.format("Не удалось создать новый объект Студент. Группа с ID \"%s\" не существует", groupId)
            );
        }
    }

    public List<StudentDto> findAllByGroup(long groupId) throws EntityIdDoesNotExistException {
        Optional<Group> optionalGroup = groupRepository.findById(groupId);
        if (optionalGroup.isPresent()) {
            Group group = optionalGroup.get();
            List<StudentDto> students = new ArrayList<>();
            for (Student student:
                 group.getStudents()) {
                students.add(StudentDto.fromEntity(student));
            }
            return students;
        } else {
            throw new EntityIdDoesNotExistException(String.format("Группа с ID %s не существует", groupId));
        }
    }

    public StudentDto findById(long id) throws EntityIdDoesNotExistException {
        Optional<Student> optionalStudent = studentRepository.findById(id);
        if (optionalStudent.isPresent()) {
            Student student = optionalStudent.get();
            return StudentDto.fromEntity(student);
        } else {
            throw new EntityIdDoesNotExistException(String.format("Студент с ID %s не существует", id));
        }

    }

    public StudentDto update(long id, StudentDto pojo) throws EntityIdDoesNotExistException {
        Optional<Student> optionalStudent = studentRepository.findById(id);
        if (optionalStudent.isPresent()) {
            Student student = optionalStudent.get();
            student.setName(pojo.getName());
            student.setBirthdate(pojo.getBirthdate());
            student.setNumber(pojo.getNumber());
            student.setBirthdate(pojo.getBirthdate());
            studentRepository.save(student);
            return StudentDto.fromEntity(student);
        } else {
            throw new EntityIdDoesNotExistException(String.format("Студент с ID %s не существует", id));
        }
    }

    public Boolean delete(long id) throws EntityIdDoesNotExistException {
        Student student = getEntity(id);
        studentRepository.deleteById(student.getId());
        return true;
    }

    private Student getEntity(Long id) {
        return studentRepository.findById(id).orElseThrow(() -> new EntityIdDoesNotExistException(
                String.format("Сущность Студент с ID=%d не существует", id)
        ));
    }

}
