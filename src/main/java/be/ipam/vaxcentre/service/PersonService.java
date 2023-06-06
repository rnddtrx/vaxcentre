package be.ipam.vaxcentre.service;


import java.util.List;
import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import be.ipam.vaxcentre.model.Person;
import be.ipam.vaxcentre.repository.PersonRepository;

public interface PersonService {
	public Iterable<Person> findAllPersons();
	public Iterable<Person> findAllPersons(int page, int size);
	//CRUD
	public Optional<Person> findPersonById(Long id);
	public Optional<Person> findByLogin(String login);
	public Person addPerson(Person person);
	public void deletePersonById(Long id);
	public Person updatePerson(Person person);
	public List<Person> findByLastnameAndFirstname(Person person);
	public Person findRandomPerson();
}
