package be.ipam.vaxcentre.service;

import org.springframework.stereotype.Service;

import be.ipam.vaxcentre.repository.AppRoleRepository;
import be.ipam.vaxcentre.repository.CentreRepository;
import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class AppRoleServiceImpl implements AppRoleService {
	//@Autowired 
	private final AppRoleRepository appRoleRepo;

}
