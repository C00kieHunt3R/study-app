package orm.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import orm.entity.Student;

import java.util.List;
import java.util.Optional;

@Repository
public interface StudentRepository extends JpaRepository<Student, Long> {


    Long deleteAllById(Long id);

//    @Query(value = "select * from public.students where name ilike '%' || :name || '%'", nativeQuery = true)
//    List<Student> selectByName(String name);
//
//    @Query(value = "insert into public.students " +
//            "values (:student.name, :student.birthdate, :student.number) " +
//            "returning name, birthdate, number",
//            nativeQuery = true)
//    Student create(Student student);
//
//    @Query(value = "update public.students " +
//            "set name = :student.name, birthdate = :student.birthdate, number = :number " +
//            "where id = :student.id",
//            nativeQuery = true)
//    int update(Student student);
//
//    @Query(value = "delete from public.students " +
//            "where id = :id",
//            nativeQuery = true)
//    int delete(Long id);
//
//    int deleteStudentById();
}
