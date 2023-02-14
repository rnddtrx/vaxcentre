package be.ipam.vaxcentre.dto;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;

import be.ipam.vaxcentre.model.Schedule;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CentreDto {
	private Long idCentre;
	private String name;
	@JsonBackReference
	private List<ScheduleDto> schedules;
}
