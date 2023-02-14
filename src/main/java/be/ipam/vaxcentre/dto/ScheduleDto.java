package be.ipam.vaxcentre.dto;

import java.time.LocalDate;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;

import be.ipam.vaxcentre.model.Centre;
import be.ipam.vaxcentre.model.Person;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ScheduleDto {
	private Long idSchedule;
	private CentreDto centre;
	@JsonBackReference
	private PersonDto person;
	private LocalDate scheduleDate;
}
