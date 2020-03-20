package com.it.inventory.inventoryit.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.it.inventory.inventoryit.dao.UserRepository;
import com.it.inventory.inventoryit.entity.User;

@Service
public class UserServiceImpl implements UserService {
	
	@Autowired
	private UserRepository userRepository;

	@Override
	@Transactional
	public void save(User theUser) {
		userRepository.save(theUser);

	}

	@Override
	@Transactional
	public List<User> findAll() {
		
		return userRepository.findAll();
	}

	@Override
	@Transactional
	public User findById(int theId) {
		Optional<User> result = userRepository.findById(theId);
		
		User theUser = null;
		
		if(result.isPresent()) {
			theUser = result.get();
		}
		else {
			// we didn't find the user
			throw new RuntimeException("Did not find iser id - " + theId);
		}
		
		return theUser;
	}

	@Override
	@Transactional
	public void deleteById(int theId) {
		Optional<User>  result= userRepository.findById(theId);
		
		
		
		if(result.isPresent()) {
			userRepository.deleteById(theId);
		}
		else {
			// we didn't find the user
			throw new RuntimeException("Did not find iser id - " + theId);
		}
		
		
		
		
		
	}

	@Override
	@Transactional
	public List<User> findByOrderByLastNameAscFirstNameAsc() {
		
		return userRepository.findByOrderByLastNameAscFirstNameAsc();
	}

	

}
