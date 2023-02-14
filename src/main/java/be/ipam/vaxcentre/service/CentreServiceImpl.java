package be.ipam.vaxcentre.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import be.ipam.vaxcentre.model.Centre;
import be.ipam.vaxcentre.repository.CentreRepository;

@Service
public class CentreServiceImpl implements CentreService{

	@Autowired CentreRepository centreRep;
	
	@Override
	public List<Centre> getAllCentre() {
		return centreRep.findAll();
	}

}
