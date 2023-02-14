package be.ipam.vaxcentre.model;

import java.time.LocalDate;


import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import jakarta.persistence.Transient;
import lombok.Data;

@Data
@Entity
@Table(name = "Schedules")
public class Schedule {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "IdSchedule")
	private Long idSchedule;
	@ManyToOne
	@JoinColumn(name = "CentreId")
	private Centre centre;
	@ManyToOne
	@JoinColumn(name = "PersonId")
	private Person person; 
	private LocalDate scheduleDate;
}
