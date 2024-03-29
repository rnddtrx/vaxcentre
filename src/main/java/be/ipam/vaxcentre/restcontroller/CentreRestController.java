package be.ipam.vaxcentre.restcontroller;

import java.util.ArrayList;
import java.util.List;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.annotation.Secured;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import be.ipam.vaxcentre.dto.CentreDto;
import be.ipam.vaxcentre.dto.PersonDto;
import be.ipam.vaxcentre.model.Centre;
import be.ipam.vaxcentre.model.Person;
import be.ipam.vaxcentre.service.CentreService;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;

@AllArgsConstructor
@RestController
@RequestMapping("api/centres")
public class CentreRestController {

	//@Autowired 
	private final CentreService centreService;
	//@Autowired
	private final ModelMapper mapper;
	
	private CentreDto convertToDto(Centre entity) {
		return mapper.map(entity, CentreDto.class);
	};	
	
	private Centre convertToEntity(CentreDto dto) {
		return mapper.map(dto, Centre.class);
	};
	
	
	//READ ALL
	@GetMapping()
		public List<CentreDto> getCentres() {
			List<CentreDto> centres = new ArrayList<CentreDto>();
			centreService.getAllCentre().forEach(c->{centres.add(convertToDto(c));});
			return centres;
    
	}
	
	//R
	@GetMapping("/{id}")
	public CentreDto getCentreById(@PathVariable("id") Long id) {
		return convertToDto(centreService.findCentreById(id).get());
	}
	
	//C
	@PostMapping
	//@Secured("ROLE_ADMIN")
	@PreAuthorize("hasRole('ROLE_ADMIN')")
	public CentreDto postCentre(@RequestBody CentreDto centreDto) {
		System.out.println(centreDto.toString());
		Centre entity = convertToEntity(centreDto);
		System.out.print(entity.toString());
		Centre centre = centreService.addCentre(entity);
		return convertToDto(centre);
	}
	
	
	//D
	@DeleteMapping("/{id}")
	public void postPerson(@PathVariable("id") Long id) {
		centreService.deleteCentreById(id);
	}
	
	//U
	@PutMapping("/{id}")
	public CentreDto putCentre(@Valid @PathVariable("id") Long id,@RequestBody CentreDto centreDto) {
		
		if(!id.equals(centreDto.getIdCentre()))throw new
			ResponseStatusException(
			HttpStatus.BAD_REQUEST,
			"id does not match"
			);
		
		Centre centre = convertToEntity(centreDto);
		return convertToDto(centreService.updateCentre(centre));
	} 
	
	
  
}

