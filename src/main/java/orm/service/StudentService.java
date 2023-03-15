package orm.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
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
public class StudentService {

    @Autowired
    StudentRepository studentRepository;
    @Autowired
    GroupRepository groupRepository;

    public Long create(long groupId, StudentPojo pojo) {
        Student student = StudentPojo.toEntity(pojo);
        Optional<Group> optionalGroup = groupRepository.findById(groupId);
        if (optionalGroup.isPresent()) {
            student.setGroup(optionalGroup.get());
            return studentRepository.save(student).getId();
        } else return 0l;
        //student.setGroup(groupRepository.findById(groupId).orElseThrow());
        //return StudentPojo.fromEntity(studentRepository.save(student));
    }

    public List<StudentPojo> findAllByGroup(long groupId) {
        Optional<Group> optionalGroup = groupRepository.findById(groupId);
        if (optionalGroup.isPresent()) {
            Group group = optionalGroup.get();
            List<StudentPojo> students = new ArrayList<>();
            for (Student student:
                 group.getStudents()) {
                students.add(StudentPojo.fromEntity(student));
            }
            return students;
        } else return null;
    }

    public StudentPojo findById(long id) {
        Optional<Student> optionalStudent = studentRepository.findById(id);
        return optionalStudent.map(StudentPojo::fromEntity).orElse(null);

    }

    public long update(long id, StudentPojo pojo) {
        Optional<Student> optionalStudent = studentRepository.findById(id);
        if (optionalStudent.isPresent()) {
            Student student = optionalStudent.get();
            student.setName(pojo.getName());
            student.setBirthdate(pojo.getBirthdate());
            student.setNumber(pojo.getNumber());
            student.setBirthdate(pojo.getBirthdate());
            studentRepository.save(student);
            return student.getId();
        } else return 0;

    }

    public void delete(long id) {
        studentRepository.deleteById(id);
    }

}
