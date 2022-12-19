package com.Prosper.controller;

import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ByteArrayResource;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.Prosper.entity.ModuleEntity;
import com.Prosper.service.ModuleService;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import lombok.extern.log4j.Log4j2;

@NoArgsConstructor
@AllArgsConstructor
@Log4j2
@RestController
@CrossOrigin
@RequestMapping("/module")
public class ModuleController {

	@Autowired
	private ModuleService moduleService ;
	
	private static final Logger logger = LogManager.getLogger(ModuleController.class);
	
	@PostMapping("/add/{courseTitle}/{moduleTitle}/{moduleDescription}")
	public ResponseEntity<String> addModuleController(@PathVariable String courseTitle,@PathVariable String moduleTitle, 
			@PathVariable String moduleDescription, @RequestParam("file") MultipartFile file) throws Exception{
		
		logger.info("Controller: Module /add [POST] courseTitle:"+courseTitle+" moduleTitle "+moduleTitle);
		String response = moduleService.addModuleService(courseTitle, moduleTitle, moduleDescription, file);
		
		if (response == "Similar module exists") {
			return new ResponseEntity<>(response, HttpStatus.CONFLICT);
		}
		return new ResponseEntity<>(response, HttpStatus.OK);
	}
	
	@GetMapping("/get/{courseTitle}")
	public ResponseEntity<List<ModuleEntity>> getModuleController(@PathVariable String courseTitle){
		return new ResponseEntity<>(moduleService.getModuleService(courseTitle), HttpStatus.OK);
	}
	
	@GetMapping("/downloadModule/{moduleEntityId}")
	public ResponseEntity<Resource> downloadFile(@PathVariable Long moduleEntityId) {
//		logger.info("Assignment Controller : /downloadFile [GET] assignmentId="+moduleEntityId);
		// Load file from database
		ModuleEntity dbFile = moduleService.getFile(moduleEntityId);
		
		if(dbFile.fileType == null) {
			return ResponseEntity.noContent().build();
		}

		return ResponseEntity.ok().contentType(MediaType.parseMediaType(dbFile.fileType))
				.header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=\"" + dbFile.fileName + "\"")
				.body(new ByteArrayResource(dbFile.data));
	}
}
