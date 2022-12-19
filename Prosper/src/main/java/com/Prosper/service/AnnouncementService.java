package com.Prosper.service;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.Prosper.entity.AnnouncementEntity;
import com.Prosper.repository.AnnouncementRepository;
import com.Prosper.request.model.AnnouncementRequest;
import com.Prosper.response.model.AnnouncementResponse;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import lombok.extern.log4j.Log4j2;

@NoArgsConstructor
@AllArgsConstructor
@Log4j2
@Service
public class AnnouncementService {
	
private static final Logger logger = LogManager.getLogger(AnnouncementService.class);
	
	@Autowired
	private AnnouncementRepository announcementRepository;
	
	private AnnouncementEntity announcementEntity= new AnnouncementEntity();
	
	private AnnouncementResponse announcementResponse = new AnnouncementResponse();
	
	public AnnouncementResponse addAnnouncement(AnnouncementRequest announcementRequest) {
//		Long announcementIdDB = announcementRepository.findAnnouncementIdByAnnouncementTitle(announcementRequest.announcementTitle);
		
		announcementEntity = announcementRepository.findByAnnouncementTitleAndCourseTitle(announcementRequest.announcementTitle, announcementRequest.courseTitle);
		
		
		if (announcementEntity != null) {
			announcementResponse.response = "Announcement title already exists";
			logger.info("Announcement Exists (Service): announcementIdDB: "+announcementEntity.announcementId + " announcementTitle "+announcementRequest.announcementTitle);
			return announcementResponse;
		}
		else {
			SimpleDateFormat dateFormat = new SimpleDateFormat("MM-dd-yyyy hh:mm a ");
			Date date = new Date();
			String frmtdDate = dateFormat.format(date);
			
			announcementResponse = new AnnouncementResponse();
			announcementEntity = new  AnnouncementEntity();
			announcementEntity.courseTitle = announcementRequest.courseTitle;
			announcementEntity.announcementTitle = announcementRequest.announcementTitle;
			announcementEntity.announcementDescription = announcementRequest.announcementDescription;
			announcementEntity.isApproved = 0;
			announcementEntity.timeStamp = frmtdDate;
			
			announcementRepository.save(announcementEntity);
			
//			announcementIdDB = announcementRepository.findAnnouncementIdByAnnouncementTitle(announcementRequest.announcementTitle);
			announcementResponse.announcementId = announcementEntity.announcementId;
			announcementResponse.response = "Announcement saved successfully!";
			logger.info("Announcement Service: announcementIdDB: "+announcementEntity.announcementId + " announcementTitle "+announcementRequest.announcementTitle + "courseTitle:"+announcementEntity.announcementId);
			return announcementResponse;
		}
	}

	public List<AnnouncementEntity> getApprovedAnnouncementService(String courseTitle) {
		List<AnnouncementEntity> announcements = announcementRepository.findByCourseTitleAndIsApproved(courseTitle, 1);
		logger.info("Announcement Approved GET Service: courseTitle: "+courseTitle);
		return announcements;
	}
	
	public List<AnnouncementEntity> getUnapprovedAnnouncementService() {
		List<AnnouncementEntity> announcements = announcementRepository.findByIsApproved(0);
		logger.info("Announcement Unapproved GET Service ");
		return announcements;
	}

	public String approveAnnouncement(Long announcementId) {
		AnnouncementEntity announcementEntity = announcementRepository.findByAnnouncementId(announcementId);
		if (announcementEntity == null) {
			announcementResponse = new AnnouncementResponse();
			announcementResponse.response = "Announcement title not found";
			logger.info("Announcement Title Not Found : announcementId "+announcementId);
			return announcementResponse.response;
		} else {
			announcementResponse = new AnnouncementResponse();
			announcementEntity.isApproved = 1;
			announcementRepository.save(announcementEntity);
			logger.info("Announcement Title updated : announcementId "+announcementId);
			announcementResponse.response = "Announcement approved successfully";
			return announcementResponse.response;
		}
		
	}

	public List<AnnouncementEntity> searchAnnouncementTitleService(String announcementTitle) {
		List<AnnouncementEntity> announcementEntities = announcementRepository.searchByAnnouncementTitle(announcementTitle,1);
		logger.info("Announcement Search GET Service: announcementTitle: "+announcementTitle);
		return announcementEntities;
	}

	public String deleteAnnouncementService(Long announcementId) {
		AnnouncementEntity entity = announcementRepository.findByAnnouncementId(announcementId);
		announcementRepository.delete(entity);
		return "OK";
	}
	

}
