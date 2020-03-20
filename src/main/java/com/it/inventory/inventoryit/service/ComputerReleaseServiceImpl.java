package com.it.inventory.inventoryit.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.it.inventory.inventoryit.dao.ComputerReleasesRepository;
import com.it.inventory.inventoryit.entity.Computer;
import com.it.inventory.inventoryit.entity.ComputerReleases;

@Service
public class ComputerReleaseServiceImpl implements ComputerReleasesService {
	
	@Autowired
	ComputerReleasesRepository theComputerReleasesRepository;
	
	@Override
	@Transactional
	public void save(ComputerReleases theComputerReleases) {
		theComputerReleasesRepository.save(theComputerReleases);

	}

	@Override
	@Transactional
	public ComputerReleases findById(int theId) {
		Optional<ComputerReleases> result = theComputerReleasesRepository.findById(theId);
		
		ComputerReleases theComputerReleases = null;
		
		if(result.isPresent()) {
			theComputerReleases = result.get();
		}
		else {
			// we didn't find the user
			throw new RuntimeException("Did not find iser id - " + theId);
		}
		return theComputerReleases;
	}

	@Override
	@Transactional
	public List<ComputerReleases> findAll() {
		
		return theComputerReleasesRepository.findAll();
	}

	@Override
	@Transactional
	public void deleteById(int theId) {
		Optional<ComputerReleases> result = theComputerReleasesRepository.findById(theId);
		
		if(result.isPresent()) {
			theComputerReleasesRepository.deleteById(theId);
		}
		else {
			// we didn't find the user
			throw new RuntimeException("Did not find iser id - " + theId);
		}

	}

	@Override
	@Transactional
	public List<ComputerReleases> findByComputer(Computer computer) {
		
		return theComputerReleasesRepository.findByComputer(computer);
	}

	

}
