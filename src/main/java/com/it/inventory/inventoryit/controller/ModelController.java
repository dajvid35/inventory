package com.it.inventory.inventoryit.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.it.inventory.inventoryit.OtherCode.TmpId;
import com.it.inventory.inventoryit.entity.DeviceModel;
import com.it.inventory.inventoryit.entity.Manufacturer;
import com.it.inventory.inventoryit.entity.User;
import com.it.inventory.inventoryit.service.ManufacturerService;
import com.it.inventory.inventoryit.service.ModelService;

import ch.qos.logback.core.joran.conditional.ThenOrElseActionBase;

@Controller
@RequestMapping("/model")
public class ModelController {
	@Autowired
	private ModelService modelService;
	
	@Autowired
	private ManufacturerService manufacturerService;
	
	@GetMapping("/showFormForAdd")
	public String showFormForAdd(Model theModel) {
		
		// method sends model to addUserForm.html
		List<Manufacturer> manufacturers = manufacturerService.findByOrderByNameAsc(); 
		DeviceModel model = new DeviceModel();
		TmpId tmpManufacturerId = new TmpId();
		tmpManufacturerId.setTmpId(0);
		
		theModel.addAttribute("manufacturers",manufacturers);
		theModel.addAttribute("tmpManufacturerId", tmpManufacturerId);
		theModel.addAttribute("model",model);
		return "models/addModelForm";
	}
	

	@PostMapping("/save")
	public String saveUser(@ModelAttribute("model") DeviceModel theModel,
							@ModelAttribute("tmpManufacturerId") TmpId tmpManufacturerId) {
		
		// ModelAttribute comes from addUserForm
		
		theModel.setManufacturer(manufacturerService.findById(tmpManufacturerId.getTmpId()));
		
		
		modelService.save(theModel);
		
		return "redirect:/hello-world";
	}
}
