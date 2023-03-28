package be.ipam.vaxcentre.service;

import java.util.Optional;

import be.ipam.vaxcentre.model.Person;
import be.ipam.vaxcentre.model.Schedule;

public interface ScheduleService {
	public Iterable<Schedule> findAllSchedules();
	//CRUD
	public Optional<Schedule> findScheduleById(Long id);
	public Schedule addSchedule(Schedule schedule);
	public void deleteScheduleById(Long id);
	public Schedule updateSchedule(Schedule schedule);
}
