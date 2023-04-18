package be.ipam.vaxcentre.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import be.ipam.vaxcentre.model.Centre;
import be.ipam.vaxcentre.model.Person;
import be.ipam.vaxcentre.repository.CentreRepository;
import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class CentreServiceImpl implements CentreService{

	//@Autowired // Replaced by @AllArgsConstructor 
	private final CentreRepository centreRep;
	
	@Override
	public List<Centre> getAllCentre() {
		return centreRep.findAll();
	}

	@Override
	public Optional<Centre> findCentreById(Long id) {
		return centreRep.findById(id);
	}

	@Override
	public Centre addCentre(Centre centre) {
		return centreRep.save(centre);
	}

	@Override
	public void deleteCentreById(Long id) {
		centreRep.deleteById(id);
	}

	@Override
	public Centre updateCentre(Centre centre) {
		return centreRep.saveAndFlush(centre);
	}

}
