package be.ipam.vaxcentre;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.temporal.ChronoUnit;
import java.util.List;
import java.util.Random;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.Assertions;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import be.ipam.vaxcentre.model.Centre;
import be.ipam.vaxcentre.model.Person;
import be.ipam.vaxcentre.model.Schedule;
import be.ipam.vaxcentre.service.CentreService;
import be.ipam.vaxcentre.service.PersonService;
import be.ipam.vaxcentre.service.ScheduleService;

@SpringBootTest
class VaxcentreApplicationTests {

	@Autowired CentreService centreService;
	@Autowired PersonService personService;
	@Autowired ScheduleService scheduleService;
	
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
	
	@Test
	void find1000RandomPersonAndGiveSchedule() {
		for(int i =0;i<1000;i++) {
			Person p = personService.findRandomPerson();
			Schedule s = new Schedule();
			s.setPerson(p);
	        Random random = new Random();
	        LocalDate tomorrow = LocalDate.now().plusDays(1);
	        LocalDate tenMonthsLater = LocalDate.now().plusMonths(10);
	        long days = ChronoUnit.DAYS.between(tomorrow, tenMonthsLater);
	        int randomDays = random.nextInt((int) days);
	        LocalDate randomDate = tomorrow.plusDays(randomDays);
	        LocalTime randomTime = LocalTime.of(random.nextInt(10) + 9, random.nextInt(60));
	        LocalDateTime ldt = LocalDateTime.of(randomDate, randomTime);
	        s.setScheduleDate(ldt);
	        s.setPerson(p);
	        s.setCentre(centreService.findCentreById((long)1).get());
			scheduleService.addSchedule(s);
		}
	}
	
	

}
