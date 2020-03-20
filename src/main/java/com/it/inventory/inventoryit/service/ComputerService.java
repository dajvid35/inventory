package com.it.inventory.inventoryit.service;

import java.util.List;

import com.it.inventory.inventoryit.entity.Computer;

public interface ComputerService {
	
	public void save(Computer theComputer);
	
	public Computer findById(int theId);
	
	public List<Computer> findAll();
	
	public void deleteById(int theId);

}
