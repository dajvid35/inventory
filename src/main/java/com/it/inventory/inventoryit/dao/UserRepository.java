package com.it.inventory.inventoryit.dao;



import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.it.inventory.inventoryit.entity.User;



public interface UserRepository extends JpaRepository<User, Integer> {
	List<User> findByOrderByLastNameAscFirstNameAsc();
}
