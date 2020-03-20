package com.it.inventory.inventoryit.controller;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.it.inventory.inventoryit.OtherCode.TmpId;
import com.it.inventory.inventoryit.entity.Computer;
import com.it.inventory.inventoryit.entity.ComputerReleases;
import com.it.inventory.inventoryit.entity.DeviceModel;
import com.it.inventory.inventoryit.entity.User;
import com.it.inventory.inventoryit.service.ComputerReleasesService;
import com.it.inventory.inventoryit.service.ComputerService;
import com.it.inventory.inventoryit.service.UserService;

@Controller
@RequestMapping("/ComputerRelease")
public class ComputerReleasesController {
	
	@Autowired
	private ComputerReleasesService theComputerReleasesService;
	
	@Autowired
	private ComputerService theComputerService;
	
	@Autowired
	private UserService theUserService;
	
	
	
	private TmpId tmpUserId;

	public ComputerReleasesController() {
		
	}
	
	@Autowired
	public ComputerReleasesController(TmpId tmpUserId) {
		this.tmpUserId = tmpUserId;
	}

	public TmpId getTmpComputerId() {
		return tmpUserId;
	}


	public void setTmpComputerId(TmpId tmpRenameId) {
		this.tmpUserId = tmpRenameId;
	}


	@GetMapping("/showRelease")
    public String showRelease(
            @RequestParam("ComputerID") Integer computerID,
            Model model)
    		// method shows ComputerRelease records for exact computer id
    
            throws Exception {
    	
    		// in this place method uses query method findByComputer to find exact records in ComputerRelease entity
            List<ComputerReleases> crlist = theComputerReleasesService.findByComputer(theComputerService.findById(computerID));
            
            Computer computer = theComputerService.findById(computerID);
            
            // model goes to computerHistoryList.html
            model.addAttribute("computerHistoryList",crlist);
            model.addAttribute("computer", computer);
            model.addAttribute("id",computerID);
            
            return "/computerRelease/computerHistoryList";

    }
    
    @GetMapping("/showRelease2")
    public String showReleaseTwo(
            @ModelAttribute("ComputerID") Integer computerID,
            Model model)
    		// method shows ComputerRelease records for exact computer id
    
            throws Exception {
    	
    		// in this place method uses query method findByComputer to find exact records in ComputerRelease entity
            List<ComputerReleases> crlist = theComputerReleasesService.findByComputer(theComputerService.findById(computerID));
            
            Computer computer = theComputerService.findById(computerID);
            
            
            // model goes to computerHistoryList.html
            model.addAttribute("computerHistoryList",crlist);
            model.addAttribute("computer", computer);
            model.addAttribute("id",computerID);
            
            return "/computerRelease/computerHistoryList";

    }
    
    @GetMapping("/showFormForAdd")
    
	public String showFormForAdd(@RequestParam("ComputerID") int theId, Model theModel) {
		
    	ComputerReleases cr = new ComputerReleases();
    	
    	tmpUserId.setTmpId(0);
    	
    	List<User> users = theUserService.findByOrderByLastNameAscFirstNameAsc();
    	
		
    	System.out.println("computerid= " + theId);
    	
		theModel.addAttribute("computerId", theId);
		theModel.addAttribute("computerRelease", cr);
		theModel.addAttribute("users", users);
		theModel.addAttribute("tmpUserId", tmpUserId);
		
		return "/computerRelease/addReleaseComputerForm";
	}
    
    @GetMapping("/showFormForUpdate")
    
   	public String showFormForUpdate(@RequestParam("ReleaseID") int theComputerReleaseId,
   									@RequestParam("ComputerID") int theId,
   									Model theModel) {
   		
       	ComputerReleases cr = theComputerReleasesService.findById(theComputerReleaseId);
       	
       	tmpUserId.setTmpId(cr.getUser().getId());
       	
       	List<User> users = theUserService.findByOrderByLastNameAscFirstNameAsc();
   		
       	System.out.println("computerid= " + cr.getComputer().getId());
       	
   		theModel.addAttribute("computerId", theId);
   		theModel.addAttribute("computerRelease", cr);
   		theModel.addAttribute("users", users);
   		theModel.addAttribute("tmpUserId", tmpUserId);
   		

   		
        
   		
   		return "/computerRelease/updateReleaseComputerForm";
   	}
    
    @PostMapping("/save")
   
    public String saveRelease(@ModelAttribute("computerRelease") ComputerReleases theComputerRelease,
    		@ModelAttribute("tmpUserId") TmpId tmpUserId,
    		@RequestParam("ComputerID") int theId,
    		RedirectAttributes redirectAttributes) throws Exception {
    	System.out.println("Id w save= " + theId);
    	System.out.println("tmpUserId= "+ tmpUserId.getTmpId());
    	
    	ComputerReleases cr = new ComputerReleases(
    			theComputerRelease.getReleaseDate(),
    			theComputerRelease.getReturningDate(),
				theComputerService.findById(theId),
				theUserService.findById(tmpUserId.getTmpId()));
    	theComputerReleasesService.save(cr);
    	redirectAttributes.addAttribute("ComputerID",theId);
    	return "redirect:/ComputerRelease/showRelease2";
    }
    
    @PostMapping("/update")
    
    public String updateRelease(@ModelAttribute("computerRelease") ComputerReleases theComputerRelease,
    		@ModelAttribute("tmpUserId") TmpId tmpUserId,
    		@RequestParam("ComputerID") int theId,
    		RedirectAttributes redirectAttributes) throws Exception {
    	System.out.println("Id w save= " + theId);
    	System.out.println("tmpUserId= "+ tmpUserId.getTmpId());
    	
    	
    	theComputerRelease.setUser(theUserService.findById(tmpUserId.getTmpId()));
    	theComputerRelease.setComputer(theComputerService.findById(theId));
    	
    	theComputerReleasesService.save(theComputerRelease);
    	redirectAttributes.addAttribute("ComputerID",theId);
    	return "redirect:/ComputerRelease/showRelease2";
    }
    
    
    
    @PostMapping("/test")
    public String testReleaseAdd(
            @RequestParam("computerID") Integer computerID,
            @RequestParam("userID") Integer userID,
            Model model) 
    		throws Exception {
    		ComputerReleases cr = new ComputerReleases(
    				new Date(),
    				new Date(),
    				theComputerService.findById(computerID),
    				theUserService.findById(userID));
    		
    		
           
    		theComputerReleasesService.save(cr);
            
            return "redirect:/computer/showComputerList";

    }
    
    @GetMapping("/delete")
    public String delete(
    					@RequestParam("computerReleaseId") Integer theId,
    					@RequestParam("ComputerID") Integer theComputerId,
    					RedirectAttributes redirectAttributes
    					) {
    	
    	System.out.println("computer id w delete= " + theComputerId);
    	theComputerReleasesService.deleteById(theId);
    	
    	redirectAttributes.addAttribute("ComputerID",theComputerId);
    	return "redirect:/ComputerRelease/showRelease2";
    	
    }
	
	
}
