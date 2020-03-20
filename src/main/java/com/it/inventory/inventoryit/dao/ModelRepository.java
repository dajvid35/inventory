package com.it.inventory.inventoryit.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.it.inventory.inventoryit.entity.DeviceModel;


public interface ModelRepository extends JpaRepository<DeviceModel, Integer> {
	List<DeviceModel> findByOrderByModelNameAsc();
}
