package orm.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import orm.model.Student;

@Repository
public interface StudentRepository extends JpaRepository<Student, Long> {



}
