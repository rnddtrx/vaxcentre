package be.ipam.vaxcentre.service;

import java.util.List;
import java.util.Optional;

import be.ipam.vaxcentre.model.Centre;
import be.ipam.vaxcentre.model.Person;

public interface CentreService {
		public List<Centre> getAllCentre();
		//CRUD
		public Optional<Centre> findCentreById(Long id);
		public Centre addCentre(Centre centre);
		public void deleteCentreById(Long id);
		public Centre updateCentre(Centre centre);
}
