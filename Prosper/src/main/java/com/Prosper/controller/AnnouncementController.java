package com.Prosper.controller;

import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.Prosper.entity.AnnouncementEntity;
import com.Prosper.request.model.AnnouncementRequest;
import com.Prosper.response.model.AnnouncementResponse;
import com.Prosper.service.AnnouncementService;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import lombok.extern.log4j.Log4j2;

@NoArgsConstructor
@AllArgsConstructor
@Log4j2
@RestController
@CrossOrigin
@RequestMapping("/announcement")
public class AnnouncementController {
	
	private static final Logger logger = LogManager.getLogger(AnnouncementController.class);
	
	@Autowired
	private AnnouncementService announcementService;
	
	@PostMapping("/add")
	public ResponseEntity<AnnouncementResponse> addAnnouncement(@RequestBody AnnouncementRequest announcementRequest) {
		logger.info("controller : announcement/add [POST]");
		AnnouncementResponse announcementResponse = announcementService.addAnnouncement(announcementRequest);
		if (announcementResponse.response == "Announcement title already exists") {
			return new ResponseEntity<>(announcementResponse, HttpStatus.CONFLICT); // HTTP status: 409
		} else {
		return new ResponseEntity<>(announcementResponse, HttpStatus.OK); // HTTP status: 200
		}
	}
	
	@GetMapping("/get/approved")
	public ResponseEntity<List<AnnouncementEntity>> getApprovedAnnouncementController(@RequestParam String courseTitle) {
		logger.info("controller : announcement/get Approved [POST]");
		return new ResponseEntity<>(announcementService.getApprovedAnnouncementService(courseTitle), HttpStatus.OK);
	}
	
	@GetMapping("/get/unapproved")
	public ResponseEntity<List<AnnouncementEntity>> getUnapprovedAnnouncementController() {
		logger.info("controller : announcement/get Unapproved [POST]");
		return new ResponseEntity<>(announcementService.getUnapprovedAnnouncementService(), HttpStatus.OK);
	}
	
	@PutMapping("/approve/announcement")
	public ResponseEntity<String> AnnouncementApprove(@RequestParam Long announcementId){
		logger.info("controller : /approve/announcement AnnouncementApprove [POST] announcementId " +announcementId);
		String announcementResponse = announcementService.approveAnnouncement(announcementId);
		if(announcementResponse == "Announcement title not found") {
			return new ResponseEntity<>(announcementResponse, HttpStatus.NOT_FOUND);
		}else {
			return new ResponseEntity<>(announcementResponse, HttpStatus.OK);
		}
	}
	
	@GetMapping("/search")
	public ResponseEntity<List<AnnouncementEntity>> searchAnnouncementController(@RequestParam String announcementTitle){
		logger.info("controller : /search [GET]");
		List<AnnouncementEntity> announcementEntities = announcementService.searchAnnouncementTitleService(announcementTitle);
		return new ResponseEntity<>(announcementEntities, HttpStatus.OK);
	}
	
	@DeleteMapping("/delete")
	public ResponseEntity<String> deleteAnnouncementController(@RequestParam Long announcementId){
		logger.info("controller : /delete [DELETE] announcementId :"+announcementId);
		String response = announcementService.deleteAnnouncementService(announcementId);
		return new ResponseEntity<>(response, HttpStatus.OK);
	}

}
