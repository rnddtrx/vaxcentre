package be.ipam.vaxcentre.restcontroller;

import java.util.ArrayList;
import java.util.List;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


import be.ipam.vaxcentre.dto.ScheduleDto;

import be.ipam.vaxcentre.model.Schedule;
import be.ipam.vaxcentre.service.ScheduleService;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;

@AllArgsConstructor
@RestController
@RequestMapping("/api/schedules")
public class ScheduleRestController {
	//@Autowired 
	private final ScheduleService scheduleService;
	//@Autowired
	private final ModelMapper mapper;
	
	private ScheduleDto convertToDto(Schedule entity) {
		return mapper.map(entity, ScheduleDto.class);
	};	
	
	private Schedule convertToEntity(ScheduleDto dto) {
		return mapper.map(dto, Schedule.class);
	};
	
	//READ ALL
	@GetMapping()
	public List<ScheduleDto> getSchedules() {
		List<ScheduleDto> schedules = new ArrayList<ScheduleDto>();
		scheduleService.findAllSchedules().forEach(s->{schedules.add(convertToDto(s));});
		return schedules;
    
	}
	
	//R
	@GetMapping("/{id}")
	public ScheduleDto getScheduleById(@PathVariable("id") Long id) {
		return convertToDto(scheduleService.findScheduleById(id).get());
	}
	

	//C
	@PostMapping
	public ScheduleDto postSchedule(@RequestBody ScheduleDto scheduleDto) {
		System.out.println(scheduleDto.toString());
		Schedule entity = convertToEntity(scheduleDto);
		System.out.print(entity.toString());
		Schedule schedule = scheduleService.addSchedule(entity);
		return convertToDto(schedule);
	}
	
	//D
	@DeleteMapping("/{id}")
	public void postSchedule(@PathVariable("id") Long id) {
		scheduleService.deleteScheduleById(id);
	}
	
	//U
	@PutMapping("/{id}")
	public ScheduleDto putSchedule(@Valid @PathVariable("id") long id,@RequestBody ScheduleDto scheduleDto) {
		Schedule schedule = convertToEntity(scheduleDto);
		return convertToDto(scheduleService.updateSchedule(schedule));
	}

	
}
