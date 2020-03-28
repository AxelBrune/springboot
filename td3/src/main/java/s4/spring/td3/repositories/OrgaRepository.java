package s4.spring.td3.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import s4.spring.td3.entities.Organization;
import s4.spring.td3.entities.User;

public interface OrgaRepository extends JpaRepository<Organization, Integer> {
    List<Organization> findByDomain(String domain);
    List<Organization> findByName(String name);
   /* @Query("select o.* from Organization o where o.name like %?1% ")
    public List<Organization> search(String text);*/
}