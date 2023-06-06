package be.ipam.vaxcentre.service;

import java.util.List;
import java.util.Optional;

import org.hibernate.Hibernate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import be.ipam.vaxcentre.model.Person;
import be.ipam.vaxcentre.repository.CentreRepository;
import be.ipam.vaxcentre.repository.PersonRepository;
import jakarta.websocket.Session;
import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class PersonServiceImpl implements PersonService{
	//@Autowired 
	private final PersonRepository personRepo;

	@Override
	public Iterable<Person> findAllPersons() {
		return personRepo.findAll();
	}

	@Override
	public Optional<Person> findPersonById(Long id) {
		return personRepo.findById(id);
	}

	@Override

	public Person addPerson(Person person) {
		if(person.getSchedules() != null) {
			person.getSchedules().forEach(s->s.setPerson(person));	
		}
		Person p = personRepo.save(person);
		return p;
		
	}

	@Override
	public void deletePersonById(Long id) {
		personRepo.deleteById(id);
	}

	@Override
	public Person updatePerson(Person person) {
		return personRepo.saveAndFlush(person);
	}

	@Override
	public List<Person> findByLastnameAndFirstname(Person person) {
		return personRepo.findByNameAndFirstName(person.getLastname(),person.getFirstname());
	}

	@Override
	public Person findRandomPerson() {
		return personRepo.findRandomPerson();
	}

	@Override
	public List<Person> findAllPersons(int page, int size) {
		Pageable pageable = PageRequest.of(page-1, size, Sort.by("lastname").ascending());
		Page<Person> persons = personRepo.findAll(pageable);
		return persons.getContent();
	}

	@Override
	@Transactional
	public Optional<Person> findByLogin(String login) {
		Person per= personRepo.findByLogin(login);
		Hibernate.initialize(per.getAppRoles());
		return Optional.of(per);
	}
	
	
	
}
