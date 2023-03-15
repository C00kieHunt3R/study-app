package orm.dto;

import lombok.Builder;
import lombok.Data;
import orm.entity.Group;
import orm.entity.Student;

import java.util.Date;

@Data
@Builder
public class StudentPojo {
    private long id;
    private String name;
    private Date birthdate;
    private int number;

    public static StudentPojo fromEntity(Student student) {
        return StudentPojo.builder()
                .id(student.getId())
                .name(student.getName())
                .birthdate(student.getBirthdate())
                .number(student.getNumber())
                .build();
    }

    public static Student toEntity(StudentPojo pojo) {
        return Student.builder()
                .id(pojo.getId())
                .name(pojo.getName())
                .birthdate(pojo.getBirthdate())
                .number(pojo.getNumber())
                .build();
    }
}
