package com.it.inventory.inventoryit.service;

import java.util.List;


import com.it.inventory.inventoryit.entity.DeviceModel;

public interface ModelService {
	public void save(DeviceModel theModel);
	
	public DeviceModel findById(int theId);
	
	public List<DeviceModel> findAll();
	
	public void deleteById(int theId);
	
	public List<DeviceModel> findByOrderByModelNameAsc();

}
