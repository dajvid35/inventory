package com.it.inventory.inventoryit.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import com.it.inventory.inventoryit.entity.Computer;

public interface ComputerRepository extends JpaRepository<Computer, Integer> {

}
