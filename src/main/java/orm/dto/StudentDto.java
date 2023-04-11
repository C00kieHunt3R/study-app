package orm.dto;

import lombok.Builder;
import lombok.Data;
import orm.model.Student;

import java.util.Date;

@Data
@Builder
public class StudentDto {
    private long id;
    private String name;
    private Date birthdate;
    private int number;

    public static StudentDto fromEntity(Student student) {
        return StudentDto.builder()
                .id(student.getId())
                .name(student.getName())
                .birthdate(student.getBirthdate())
                .number(student.getNumber())
                .build();
    }

    public static Student toEntity(StudentDto pojo) {
        return Student.builder()
                .id(pojo.getId())
                .name(pojo.getName())
                .birthdate(pojo.getBirthdate())
                .number(pojo.getNumber())
                .build();
    }
}
