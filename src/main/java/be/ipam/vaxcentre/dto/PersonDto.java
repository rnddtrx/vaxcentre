package be.ipam.vaxcentre.dto;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonManagedReference;

import be.ipam.vaxcentre.model.Schedule;
import be.ipam.vaxcentre.dto.ScheduleDto;
import jakarta.persistence.OneToMany;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class PersonDto {
	private long idPerson;
	private String Lastname;
	private String Firstname;
	private List<ScheduleDto> schedules;
	private String login;
	private String hashedPassword;
	

}
