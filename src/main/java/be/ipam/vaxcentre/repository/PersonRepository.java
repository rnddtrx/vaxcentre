package be.ipam.vaxcentre.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;


import be.ipam.vaxcentre.model.Centre;
import be.ipam.vaxcentre.model.Person;

@Repository
public interface PersonRepository extends JpaRepository<Person, Long>{
	 
	@Query("SELECT p FROM Person p WHERE p.lastname LIKE concat('%', :lastname, '%') AND LOWER(p.firstname) LIKE concat('%', LOWER(:firstname), '%')")
	List<Person> findByNameAndFirstName(String lastname,String firstname);
}
