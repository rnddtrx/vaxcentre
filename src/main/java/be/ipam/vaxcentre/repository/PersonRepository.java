package be.ipam.vaxcentre.repository;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
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
	
	
	@Query(value = "SELECT TOP 1 * FROM Persons ORDER BY NEWID()", nativeQuery = true)
	Person findRandomPerson();
	
	Page<Person> findAll(Pageable pageable);
	
	Person findByLogin(String login);
	
}

