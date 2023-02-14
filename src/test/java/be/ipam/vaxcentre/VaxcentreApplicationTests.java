package be.ipam.vaxcentre;

import java.util.List;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import be.ipam.vaxcentre.model.Centre;
import be.ipam.vaxcentre.service.CentreService;

@SpringBootTest
class VaxcentreApplicationTests {

	@Autowired CentreService centreService;
	
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

}
