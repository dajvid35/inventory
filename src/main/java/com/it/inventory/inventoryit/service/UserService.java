package com.it.inventory.inventoryit.service;

import java.util.List;
import java.util.Optional;

import com.it.inventory.inventoryit.entity.User;

public interface UserService {
	public void save(User theUser);
	
	public List<User> findAll();
	
	
	
	public void deleteById(int theId);

	public User findById(int theId);
	public List<User> findByOrderByLastNameAscFirstNameAsc();
}
