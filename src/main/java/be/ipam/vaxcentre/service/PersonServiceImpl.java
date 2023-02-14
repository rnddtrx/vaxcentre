package be.ipam.vaxcentre.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import be.ipam.vaxcentre.model.Person;
import be.ipam.vaxcentre.repository.PersonRepository;

@Service
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
		return personRepo.save(person);
	}

	@Override
	public void deletePersonById(Long id) {
		personRepo.deleteById(id);
	}

	@Override
	public Person updatePerson(Person person) {
		return personRepo.saveAndFlush(person);
	}
	
	
	
}
