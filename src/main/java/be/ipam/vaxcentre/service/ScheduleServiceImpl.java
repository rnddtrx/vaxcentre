package be.ipam.vaxcentre.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import be.ipam.vaxcentre.model.Person;
import be.ipam.vaxcentre.model.Schedule;
import be.ipam.vaxcentre.repository.PersonRepository;
import be.ipam.vaxcentre.repository.ScheduleRepository;
import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class ScheduleServiceImpl implements ScheduleService{

	//@Autowired 
	private final ScheduleRepository scheduleRepo;
	
	@Override
	public Iterable<Schedule> findAllSchedules() {
		return scheduleRepo.findAll();

	}

	@Override
	public Optional<Schedule> findScheduleById(Long id) {
		return scheduleRepo.findById(id);
	}

	@Override
	public Schedule addSchedule(Schedule schedule) {
		return scheduleRepo.save(schedule);
	}

	@Override
	public void deleteScheduleById(Long id) {
		scheduleRepo.deleteById(id);
		
	}

	@Override
	public Schedule updateSchedule(Schedule schedule) {
		return scheduleRepo.saveAndFlush(schedule);
	}

}
