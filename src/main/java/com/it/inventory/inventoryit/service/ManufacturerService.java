package com.it.inventory.inventoryit.service;

import java.util.List;


import com.it.inventory.inventoryit.entity.DeviceModel;
import com.it.inventory.inventoryit.entity.Manufacturer;

public interface ManufacturerService {
	public void save(Manufacturer theManufacturer);
	
	public Manufacturer findById(int theId);
	
	public List<Manufacturer> findAll();
	
	public void deleteById(int theId);
	
	public List<Manufacturer> findByOrderByNameAsc();

}
