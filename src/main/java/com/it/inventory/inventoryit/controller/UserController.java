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
import com.it.inventory.inventoryit.entity.User;
import com.it.inventory.inventoryit.service.UserService;

@Controller
@RequestMapping("/user")
public class UserController {
	
	@Autowired
	private UserService userService;
	
	
	// test save
	@PostMapping("/test")
	public String testSave(Model theModel) {
		User user=new User("Brad","Pitt");
		
		userService.save(user);
		return "hello-world";
	}
	
	@GetMapping("/showFormForAdd")
	public String showFormForAdd(Model theModel) {
		
		// method sends model to addUserForm.html
		
		User user = new User();
		
		theModel.addAttribute("user",user);
		return "users/addUserForm";
	}
	
	@GetMapping("/showFormForUpdate")
	public String showFormForUpdate(@RequestParam("userId") int theId, Model theModel) {
		
		// updates exact existing database Computer object
		
		User theUser = userService.findById(theId);
		
		System.out.println("update");

		theModel.addAttribute("user", theUser);
		
		return "/users/updateUserForm";
	}
	
	@PostMapping("/save")
	public String saveUser(@ModelAttribute("user") User theUser) {
		
		// ModelAttribute comes from addUserForm
		
		userService.save(theUser);
		
		return "redirect:/user/showUserList";
	}
	
	@RequestMapping("/delete")
    public String kasuj(@RequestParam("userId") Integer id, Model model){
        userService.deleteById(id);

        model.addAttribute("users", userService.findAll());
        return "computer/computer-list";
    }
	
	@GetMapping("/showUserList")
	public String showComputerList(Model theModel) {
		
		// method shows list of all computers computer-list.html
		
		List<User> users = userService.findAll();
		
		theModel.addAttribute("users", users);
		
		return "users/user-list";
	}
}
