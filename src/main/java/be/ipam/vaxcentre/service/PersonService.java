package be.ipam.vaxcentre.service;


import java.util.List;
import java.util.Optional;

import be.ipam.vaxcentre.model.Person;
import be.ipam.vaxcentre.repository.PersonRepository;

public interface PersonService {
	public Iterable<Person> findAllPersons();
	//CRUD
	public Optional<Person> findPersonById(Long id);
	public Person addPerson(Person person);
	public void deletePersonById(Long id);
	public Person updatePerson(Person person);
	public List<Person> findByLastnameAndFirstname(Person person);
	public Person findRandomPerson();
}
