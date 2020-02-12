package s4.spring.td2.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import s4.spring.td2.entities.User;

public interface UserRepository extends JpaRepository<User, Integer>{
	List<User> findByEmail(String email);
}
