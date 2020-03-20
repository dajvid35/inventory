package com.it.inventory.inventoryit.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.it.inventory.inventoryit.entity.Computer;
import com.it.inventory.inventoryit.entity.ComputerReleases;

public interface ComputerReleasesRepository extends JpaRepository<ComputerReleases, Integer> {

	List<ComputerReleases> findByComputer(Computer computer);
	
	

}
