package al.crystal.conferenceApp.repository;

import al.crystal.conferenceApp.dto.UserDto;
import al.crystal.conferenceApp.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {

    //    @Query(value = "Select * from user u where u.email =:email and u.password =:password and u.dtype in ('o','p')", nativeQuery = true)
    User findByEmailAndPassword(String email, String password);

    @Query(value = "Select u.dtype from user u where u.email =:email", nativeQuery = true)
    String findDTypeOfLoggedUser(String email);

    User findByEmail(String email);

}
