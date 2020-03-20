package com.it.inventory.inventoryit.controller;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.InputStreamResource;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.it.inventory.inventoryit.entity.ComputerReleases;
import com.it.inventory.inventoryit.service.StorageService;
import com.it.itinventory.inventoryit.fileUpload.FileResponse;


@Controller
@RequestMapping("/upload2")
public class FileController2 {
	
    private StorageService storageService;
    //przekaż w konstruktorze ścieżkę do wybranego katalogu
    public FileController2(StorageService storageService) {
        this.storageService = storageService;
    }
    
    @GetMapping("/test")
    public String test () {
    	return "hello-world";
    }
    
    @GetMapping("/listFiles")
    public String listAllFiles(Model model, 
    		@ModelAttribute("ReleaseID") int theId) {
    	System.out.println("inside listallfiles");
      /* model.addAttribute("files", storageService.loadAll().map(
                path -> ServletUriComponentsBuilder.fromPath("d:/yourApplicationName2/")
        		//path -> ServletUriComponentsBuilder.fromCurrentContextPath()
                        .path("/upload2/download/")
                        .path(path.getFileName().toString())
                        .toUriString())
                .collect(Collectors.toList()));*/
    	
    	/*model.addAttribute("files",Stream.of(new File("d:/yourApplicationName2/").listFiles())
        .filter(file -> !file.isDirectory())
        .map(File::getName)
        .collect(Collectors.toSet()));*/
    	
    	System.out.println("Folder id="+theId);
    	
    	 File file2 = new File("d:/"+theId+"/");
         if (!file2.exists()) {
             if (file2.mkdir()) {
                 System.out.println("Directory is created!");
             } else {
                 System.out.println("Failed to create directory!");
             }
         }
    	
    	model.addAttribute("files",Stream.of(new File("d:/"+theId+"/").listFiles())
    	        .filter(file -> !file.isDirectory())
    	        .map(
    	        		file -> "http://localhost:8080/upload2/download/" + file.getName()
                        
                        )
    	        		
    	        .collect(Collectors.toSet()));
    	model.addAttribute("theFolderId",theId);
    	
        return "files/listFiles";
    }
    
    @PostMapping("/listFilesPost")
    public String listAllFilesPost(@RequestParam("ReleaseID") int theId,
    		RedirectAttributes redirectAttributes) {
    	redirectAttributes.addAttribute("ReleaseID",theId);
    	return "redirect:/upload2/listFiles";
    }

    @GetMapping("/download/{filename:.+}")
    @ResponseBody
    public ResponseEntity<InputStreamResource> downloadFile(@PathVariable String filename,
    		@RequestParam("TheFolderId") Integer theFolderId) throws IOException {
    	System.out.println(filename);
        //Resource resource = storageService.loadAsResource(filename);
        
        File file = new File("d:/"+theFolderId+"/" + filename);
        InputStreamResource resource = new InputStreamResource(new FileInputStream(file));
        
        return ResponseEntity.ok()
        		.header(HttpHeaders.CONTENT_DISPOSITION,
                        "attachment;filename=" + file.getName())
                .body(resource);
    }
    
    

    @PostMapping("/upload-file")
    @ResponseBody
    public void uploadFile(@RequestParam("file") MultipartFile file,
    		@RequestParam("TheFolderId") Integer theFolderId) throws IllegalStateException, IOException {
        String name = file.getOriginalFilename();
        File tmpFile = new File("d:/"+theFolderId+"/" + name);
        tmpFile.mkdirs();
        file.transferTo(tmpFile);
        
        

        String uri = ServletUriComponentsBuilder.fromCurrentContextPath()
                .path("/download/")
                .path(name)
                .toUriString();

        //return new FileResponse(name, uri, file.getContentType(), file.getSize());
    }

    /*
    @PostMapping("/upload-multiple-files")
    @ResponseBody
    public List<FileResponse> uploadMultipleFiles(@RequestParam("files") MultipartFile[] files) {
        return Arrays.stream(files)
                .map(file -> uploadFile(file))
                .collect(Collectors.toList());
    }*/

}
