package com.it.inventory.inventoryit.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.it.inventory.inventoryit.dao.ModelRepository;
import com.it.inventory.inventoryit.entity.DeviceModel;

@Service
public class ModelServiceImpl implements ModelService {
	
	
	@Autowired
	private ModelRepository modelRepository;
	
	@Override
	@Transactional
	public void save(DeviceModel theModel) {
		modelRepository.save(theModel);

	}

	@Override
	@Transactional
	public DeviceModel findById(int theId) {
		
		Optional <DeviceModel> theResult = modelRepository.findById(theId);
		
		DeviceModel theModel=null;
		if (theResult.isPresent()) {
			theModel=theResult.get();
			
		} else {
			// we didn't find the model
			throw new RuntimeException("Did not find model id - " + theId);
		}
		return theModel;
	}

	@Override
	@Transactional
	public List<DeviceModel> findAll() {
		return modelRepository.findAll();
		
	}

	@Override
	@Transactional
	public void deleteById(int theId) {
		Optional <DeviceModel> theResult = modelRepository.findById(theId);
		
		if (theResult.isPresent()) {
			modelRepository.deleteById(theId);
		} else {
			// we didn't find the model
			throw new RuntimeException("Did not find model id - " + theId);
		}

	}
	
	@Override
	@Transactional
	public List<DeviceModel> findByOrderByModelNameAsc(){
		return modelRepository.findByOrderByModelNameAsc();
	}

}
