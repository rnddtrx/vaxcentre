package be.ipam.vaxcentre;

import java.util.List;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.Assertions;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import be.ipam.vaxcentre.model.Centre;
import be.ipam.vaxcentre.model.Person;
import be.ipam.vaxcentre.service.CentreService;
import be.ipam.vaxcentre.service.PersonService;

@SpringBootTest
class VaxcentreApplicationTests {

	@Autowired CentreService centreService;
	@Autowired PersonService personService;
	
	@Test
	void contextLoads() {
	}
	
	@Test
	void getAllCenterTest() {
		List<Centre> centres = centreService.getAllCentre();
		for(Centre centre : centres) {
			System.out.println(centre.getName());
		}
	}
	
	@Test
	void getByLastnameAndFirstName() {
		Person p = new Person();
		p.setFirstname("Patricia");
		p.setLastname("Marín");
		var listPerson = personService.findByLastnameAndFirstname(p);
		Assertions.assertTrue(listPerson.size()>0,"List Size > 0 ");
	}

}
