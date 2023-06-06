package be.ipam.vaxcentre.model;

import java.util.List;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import jakarta.persistence.Transient;
import lombok.Data;

@Entity
@Data
@Table(name = "Persons")
public class Person {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "IdPerson")
	private Long idPerson;
	
	@Column(columnDefinition = "NVARCHAR(255)")
	private String lastname;
	@Column(columnDefinition = "NVARCHAR(255)")
	private String firstname;
	@OneToMany(mappedBy="person",cascade=CascadeType.ALL)
	List<Schedule> schedules;
	@Column(columnDefinition = "NVARCHAR(255)")
	private String login;
	@Column(columnDefinition = "NVARCHAR(255)")
	private String hashedPassword;
	@ManyToMany(cascade=CascadeType.ALL)
	List<AppRole> appRoles;
	
}
