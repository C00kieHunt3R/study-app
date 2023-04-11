package orm.dto;

import lombok.Getter;
import lombok.Setter;
import orm.model.Group;
import orm.model.Student;

import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
public class GroupDto {
    private long id;
    private String name;
    private List<StudentDto> students;

    public static Group toEntity(GroupDto pojo) {
        Group group = new Group();
        group.setId(pojo.getId());
        group.setName(pojo.getName());
        List<Student> studentList = new ArrayList<>();
        group.setStudents(studentList);
        for (StudentDto studentDto : pojo.getStudents()) {
            studentList.add(StudentDto.toEntity(studentDto));
        }
        return group;
    }

    public static GroupDto fromEntity(Group group) {
        GroupDto pojo = new GroupDto();
        pojo.setId(group.getId());
        pojo.setName(group.getName());
        List<StudentDto> students = new ArrayList<>();
        pojo.setStudents(students);
        for (Student student : group.getStudents()) {
            students.add(StudentDto.fromEntity(student));
        }
        return pojo;
    }
}
