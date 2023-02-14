package be.ipam.vaxcentre.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import be.ipam.vaxcentre.model.Centre;
import be.ipam.vaxcentre.model.Schedule;

public interface ScheduleRepository extends JpaRepository<Schedule, Long>{

}
