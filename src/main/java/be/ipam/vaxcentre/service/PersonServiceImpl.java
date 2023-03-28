package be.ipam.vaxcentre.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import be.ipam.vaxcentre.model.Person;
import be.ipam.vaxcentre.repository.PersonRepository;
import jakarta.websocket.Session;

@Service
@Transactional
public class PersonServiceImpl implements PersonService{
	@Autowired 
	private PersonRepository personRepo;

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
	
	
	
}
