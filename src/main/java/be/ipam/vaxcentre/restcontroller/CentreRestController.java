package be.ipam.vaxcentre.restcontroller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import be.ipam.vaxcentre.service.CentreService;

@RestController
@RequestMapping("/api")
public class CentreRestController {

	@Autowired CentreService centreService;
	
	
	@GetMapping("/greeting")
	public String greeting(@RequestParam(value = "name", defaultValue = "Patient") String name) {
		return "Bonjour " + name + "!";
	}
  
	@GetMapping("/centres")
		public String getCentres() {
			return centreService.getAllCentre().toString();
    
	}
  
}

