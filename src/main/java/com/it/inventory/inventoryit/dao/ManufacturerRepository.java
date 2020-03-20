package com.it.inventory.inventoryit.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.it.inventory.inventoryit.entity.DeviceModel;
import com.it.inventory.inventoryit.entity.Manufacturer;


public interface ManufacturerRepository extends JpaRepository<Manufacturer, Integer> {
	List<Manufacturer> findByOrderByNameAsc();
}
