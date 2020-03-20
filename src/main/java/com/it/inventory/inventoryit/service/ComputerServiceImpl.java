package com.it.inventory.inventoryit.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.it.inventory.inventoryit.dao.ComputerRepository;
import com.it.inventory.inventoryit.entity.Computer;

@Service
public class ComputerServiceImpl implements ComputerService {
	
	@Autowired
	ComputerRepository computerRepository;
	
	@Override
	@Transactional
	public void save(Computer theComputer) {
		computerRepository.save(theComputer);

	}

	@Override
	@Transactional
	public Computer findById(int theId) {
		Optional<Computer> result = computerRepository.findById(theId);
		
		Computer theComputer = null;
		
		if(result.isPresent()) {
			theComputer = result.get();
		}
		else {
			// we didn't find the user
			throw new RuntimeException("Did not find  computer id - " + theId);
		}
		return theComputer;
	}

	@Override
	@Transactional
	public List<Computer> findAll() {
		
		return computerRepository.findAll();
	}

	@Override
	@Transactional
	public void deleteById(int theId) {
		Optional<Computer> result = computerRepository.findById(theId);
		
		
		
		if(result.isPresent()) {
			computerRepository.deleteById(theId);
		}
		else {
			// we didn't find the user
			throw new RuntimeException("Did not find iser id - " + theId);
		}

	}

}
