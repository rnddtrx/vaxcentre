package be.ipam.vaxcentre.restcontroller;

import java.util.ArrayList;
import java.util.List;

import org.modelmapper.ModelMapper;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import be.ipam.vaxcentre.dto.PersonDto;
import be.ipam.vaxcentre.model.Person;
import be.ipam.vaxcentre.service.PersonService;

import jakarta.validation.Valid;
import lombok.AllArgsConstructor;

@AllArgsConstructor
@RestController
@RequestMapping("api/persons")
public class PersonRestController {
	//@Autowired 
	private final PersonService personService;
	//@Autowired
	private final ModelMapper mapper;
	
	private final BCryptPasswordEncoder passwordEncoder;
	
	
	private PersonDto convertToDto(Person entity) {
		return mapper.map(entity, PersonDto.class);
	};
	
	private Person convertToEntity(PersonDto dto) {
		Person p = mapper.map(dto, Person.class);
		p.setHashedPassword(passwordEncoder.encode(p.getHashedPassword()));
		return p;
	};
	
	//R All
	@GetMapping()
	public List<PersonDto> getPersons() {
		List<PersonDto> persons = new ArrayList<PersonDto>();
		personService.findAllPersons().forEach(p->{persons.add(convertToDto(p));});
		return persons;
	}
	
	@GetMapping("/{page}/{size}")
	public List<PersonDto> getPersonsWithPaging(@PathVariable("page") int page,@PathVariable("size") int size) {
		List<PersonDto> persons = new ArrayList<PersonDto>();
		personService.findAllPersons(page,size).forEach(p->{persons.add(convertToDto(p));});
		return persons;
	}
	
	
	//R
	@GetMapping("/{id}")
	public PersonDto getPersonById(@PathVariable("id") Long id) {
		return convertToDto(personService.findPersonById(id).get());
	}
	
	//C
    @PostMapping("/register")
	public PersonDto postPerson(@RequestBody PersonDto personDto) {
		//System.out.println(personDto.toString());
		Person entity = convertToEntity(personDto);
		System.out.print(entity.toString());
		Person person = personService.addPerson(entity);
		return convertToDto(person);
	}
	
	//D
	@DeleteMapping("/{id}")
	public void postPerson(@PathVariable("id") Long id) {
		personService.deletePersonById(id);
	}
	
	//U
	@PutMapping("/{id}")
	public PersonDto putPerson(@Valid @PathVariable("id") Long id,@RequestBody PersonDto personDto) {
		
		if(!id.equals(personDto.getIdPerson()))throw new
		ResponseStatusException(
		HttpStatus.BAD_REQUEST,
		"id does not match"
		);
		
		Person person = convertToEntity(personDto);
		return convertToDto(personService.updatePerson(person));
	}
	
	@PostMapping("/findByLastnameAndFirstname")
	public List<PersonDto> findByLastnameAndFirstname(@RequestBody Person personToFind){
		List<Person> personsFound = personService.findByLastnameAndFirstname(personToFind);
		List<PersonDto> personsDtoFound = new ArrayList<PersonDto>();
		personsFound.forEach(p->personsDtoFound.add(convertToDto(p)));
		return personsDtoFound;
	}
	
}
