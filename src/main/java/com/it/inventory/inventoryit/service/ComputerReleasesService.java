package com.it.inventory.inventoryit.service;

import java.util.List;

import com.it.inventory.inventoryit.entity.Computer;
import com.it.inventory.inventoryit.entity.ComputerReleases;

public interface ComputerReleasesService {
	public void save(ComputerReleases theComputerReleases);
	
	public ComputerReleases findById(int theId);
	
	public List<ComputerReleases> findAll();
	
	public void deleteById(int theId);
	
	public List<ComputerReleases> findByComputer(Computer computer);
	
	
	
}
