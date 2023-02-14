package be.ipam.vaxcentre.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import be.ipam.vaxcentre.model.Centre;

public interface CentreRepository extends JpaRepository<Centre, Long>{

}