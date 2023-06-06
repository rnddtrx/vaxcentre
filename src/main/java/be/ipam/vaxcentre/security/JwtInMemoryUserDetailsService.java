package be.ipam.vaxcentre.security;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import be.ipam.vaxcentre.model.Person;
import be.ipam.vaxcentre.repository.AppRoleRepository;
import be.ipam.vaxcentre.repository.PersonRepository;
import be.ipam.vaxcentre.service.PersonService;



@Service
public class JwtInMemoryUserDetailsService implements UserDetailsService {

  @Autowired
  PersonService personService;
 
  
  @Autowired
  private BCryptPasswordEncoder passwordEncoder;
  

  // Trouver un user dans la db
  @Override
  public UserDetails loadUserByUsername(String login) throws UsernameNotFoundException {
    
	Optional<Person> per = personService.findByLogin(login);
	
	
	if(per.isEmpty())
			throw new UsernameNotFoundException("User with login : " + login +" not found !");
	else {
		Person pers = per.get();
		JwtUserDetails ud = new JwtUserDetails(
				pers.getIdPerson(), pers.getLogin(), pers.getHashedPassword(),
				pers.getAppRoles().stream().map(ar->new SimpleGrantedAuthority(ar.getRoleName())).collect(Collectors.toList()));
		ud.getAuthorities().forEach(a->System.out.print(a.getAuthority()));
		return ud;
	}
	
		  
  }
  //Sauver un user

	//public Long saveUser(Person pers) {
	//	String passwd= pers.getHashedPassword();
	//	String encodedPasswod = passwordEncoder.encode(passwd);
	//	pers.setHashedPassword(encodedPasswod);
	//	pers = personRepo.save(pers);
	//	return pers.getIdPerson();
	//}
  
}