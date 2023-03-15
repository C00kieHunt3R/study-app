package orm.dto;

import lombok.Builder;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import orm.entity.Group;
import orm.entity.Student;

import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
public class GroupPojo {
    private long id;
    private String name;
    private List<StudentPojo> students;

    public static Group toEntity(GroupPojo pojo) {
        Group group = new Group();
        group.setId(pojo.getId());
        group.setName(pojo.getName());
        List<Student> studentList = new ArrayList<>();
        group.setStudents(studentList);
        for (StudentPojo studentPojo : pojo.getStudents()) {
            studentList.add(StudentPojo.toEntity(studentPojo));
        }
        return group;
    }

    public static GroupPojo fromEntity(Group group) {
        GroupPojo pojo = new GroupPojo();
        pojo.setId(group.getId());
        pojo.setName(group.getName());
        List<StudentPojo> students = new ArrayList<>();
        pojo.setStudents(students);
        for (Student student : group.getStudents()) {
            students.add(StudentPojo.fromEntity(student));
        }
        return pojo;
    }
}
