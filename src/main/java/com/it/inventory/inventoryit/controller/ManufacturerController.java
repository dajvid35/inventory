package com.it.inventory.inventoryit.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;


import com.it.inventory.inventoryit.entity.Manufacturer;

import com.it.inventory.inventoryit.service.ManufacturerService;


@Controller
@RequestMapping("/manufacturer")
public class ManufacturerController {
	@Autowired
	private ManufacturerService manufacturerService;
	
	@GetMapping("/showFormForAdd")
	public String showFormForAdd(Model theModel) {
		
		// method sends model to addUserForm.html
		
		Manufacturer manufacturer = new Manufacturer();
		
		theModel.addAttribute("manufacturer",manufacturer);
		return "manufacturer/addManufacturerForm";
	}
	

	@PostMapping("/save")
	public String saveManufacturer(@ModelAttribute("manufacturer") Manufacturer theManufacturer) {
		
		// ModelAttribute comes from addUserForm
		
		manufacturerService.save(theManufacturer);
		
		return "redirect:/hello-world";
	}
}
