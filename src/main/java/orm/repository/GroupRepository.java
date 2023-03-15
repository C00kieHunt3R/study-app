package orm.repository;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import orm.entity.Group;

import java.util.List;

@Repository
public interface GroupRepository extends JpaRepository<Group, Long> {

    List<Group> findAllByNameContainingIgnoreCase(String name);
    //Long deleteGroupById(Long id);

    Long deleteAllById(Long id);
    //Long deleteGroupById(long id);
//    @Query(value = "select * from public.groups " +
//            "where name ilike '%' || :name || '%'",
//            nativeQuery = true)
//    List<Group> selectByName(String name);
//
//    @Query(value = "insert into public.groups " +
//            "values (:student.name, :student.birthdate, :student.number) " +
//            "returning name",
//            nativeQuery = true)
//    Group create(Group group);
//
//    @Query(value = "update public.groups " +
//            "set name = :group.name " +
//            "where id = :student.id ",
//            nativeQuery = true)
//    int update(Group group);
//
//    @Query(value = "delete from public.groups " +
//            "where id = :id",
//            nativeQuery = true)
//    int delete(Long id);
}
