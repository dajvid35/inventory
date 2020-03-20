package com.it.inventory.inventoryit.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.it.inventory.inventoryit.OtherCode.TmpId;
import com.it.inventory.inventoryit.entity.Computer;
import com.it.inventory.inventoryit.entity.DeviceModel;
import com.it.inventory.inventoryit.service.ComputerService;
import com.it.inventory.inventoryit.service.ModelService;
import com.it.inventory.inventoryit.service.UserService;

@Controller
@RequestMapping("/computer")
public class ComputerController {
	
	@Autowired
	private ComputerService theComputerService;
	
	@Autowired
	private UserService theUserService;
	
	@Autowired
	private ModelService theModelService;
	
	
	
	
	@GetMapping("/showFormForAdd")
	public String showFormForAdd(Model theModel) {
		
		Computer theComputer = new Computer();
		List<DeviceModel> models = theModelService.findByOrderByModelNameAsc();
		TmpId tmpModelId = new TmpId();
		tmpModelId.setTmpId(0);
		
		// sends model to computer/addComputerForm
		
		theModel.addAttribute("computer", theComputer);
		theModel.addAttribute("models", models);
		theModel.addAttribute("tmpModelId",tmpModelId);
		
		return "computer/addComputerForm";
	}
	
	
	
	@PostMapping("/save")
	public String save(@ModelAttribute("computer") Computer theComputer,
						@ModelAttribute("tmpModelId") TmpId tmpModelId) {
		
		// ModelAttribute comes from addComputerForm.html
		System.out.println("model id: " + tmpModelId);
		
		theComputer.setModel(theModelService.findById(tmpModelId.getTmpId()));
		theComputerService.save(theComputer);
		
		// redirection to computer-list.html
		
		return "redirect:/computer/showComputerList";
	}
	
	
	
	@GetMapping("/showComputerList")
	public String showComputerList(Model theModel) {
		
		// method shows list of all computers computer-list.html
		
		List<Computer> computers = theComputerService.findAll();
		
		theModel.addAttribute("computers", computers);
		
		return "computer/computer-list";
	}
	
	@GetMapping("/showFormForUpdate")
	public String showFormForUpdate(@RequestParam("computerId") int theId, Model theModel) {
		
		// updates exact existing database Computer object
		
		Computer theComputer = theComputerService.findById(theId);
		
		List<DeviceModel> models = theModelService.findByOrderByModelNameAsc();
		TmpId tmpModelId = new TmpId();
		tmpModelId.setTmpId(theComputer.getModel().getId());
		
		
		theModel.addAttribute("models", models);
		theModel.addAttribute("tmpModelId",tmpModelId);
		theModel.addAttribute("computer", theComputer);
		
		return "/computer/updateComputerForm";
	}
	
	@RequestMapping("/delete")
    public String kasuj(@RequestParam("computerId") Integer id, Model model){
        theComputerService.deleteById(id);

        model.addAttribute("computers", theComputerService.findAll());
        return "computer/computer-list";
    }
	 @GetMapping("/test")
	    public String test () {
	    	return "hello-world";
	    }

}
