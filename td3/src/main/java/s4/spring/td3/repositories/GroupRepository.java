package s4.spring.td3.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import s4.spring.td3.entities.Groupe;

public interface GroupRepository extends JpaRepository<Groupe, Integer> {
	List<Groupe> findByEmail(String email);
    List<Groupe> findByName(String name);
}
