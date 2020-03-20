package com.it.inventory.inventoryit.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.it.inventory.inventoryit.dao.ManufacturerRepository;

import com.it.inventory.inventoryit.entity.Manufacturer;

@Service
public class ManufacturerServiceImpl implements ManufacturerService {
	
	
	@Autowired
	private ManufacturerRepository manufacturerRepository;
	
	@Override
	@Transactional
	public void save(Manufacturer theManufacturer) {
		manufacturerRepository.save(theManufacturer);

	}

	@Override
	@Transactional
	public Manufacturer findById(int theId) {
		
		Optional<Manufacturer> theResult = manufacturerRepository.findById(theId);
		
		Manufacturer theManufacturer=null;
		if (theResult.isPresent()) {
			theManufacturer=theResult.get();
			
		} else {
			// we didn't find the model
			throw new RuntimeException("Did not find model id - " + theId);
		}
		return theManufacturer;
	}

	@Override
	@Transactional
	public List<Manufacturer> findAll() {
		return manufacturerRepository.findAll();
		
	}

	@Override
	@Transactional
	public void deleteById(int theId) {
		Optional<Manufacturer> theResult = manufacturerRepository.findById(theId);
		
		if (theResult.isPresent()) {
			manufacturerRepository.deleteById(theId);
		} else {
			// we didn't find the model
			throw new RuntimeException("Did not find model id - " + theId);
		}

	}
	
	@Override
	@Transactional
	public List<Manufacturer> findByOrderByNameAsc(){
		return manufacturerRepository.findByOrderByNameAsc();
	}

}
