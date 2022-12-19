package com.Prosper.service;

import java.io.IOException;
import java.util.List;
import java.util.Optional;
import java.util.function.Consumer;
import java.util.stream.Stream;

import org.apache.catalina.connector.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.Prosper.entity.AssignmentEntity;
import com.Prosper.entity.CourseEntity;
import com.Prosper.entity.UserEntity;
import com.Prosper.repository.AssignmentRepository;
import com.Prosper.repository.CourseRepository;
import com.Prosper.request.model.AssignmentRequest;
import com.Prosper.request.model.CourseRequest;
import com.Prosper.response.model.AssignmentResponse;
import com.Prosper.response.model.CourseResponse;
import com.Prosper.response.model.UserResponse;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import lombok.extern.log4j.Log4j2;

@NoArgsConstructor
@AllArgsConstructor
@Log4j2
@Service
public class AssignmentService {
	@Autowired
	private AssignmentRepository assignmentRepository;

	private AssignmentEntity assignmentEntity = new AssignmentEntity();
	private AssignmentResponse assignmentResponse = new AssignmentResponse();

	public List<AssignmentEntity> getAssignment(String courseTitle) {
		List<AssignmentEntity> announcements = assignmentRepository.findByCourseTitle(courseTitle);

		return announcements;

	}

	public String storeFile(MultipartFile file, String assignmentTitle, String courseTitle, String dueDate,
			String assignmentDescription) throws Exception {
		List<AssignmentEntity> assignmentDBTitle = assignmentRepository.findByCourseTitle(courseTitle);
		for (AssignmentEntity entity : assignmentDBTitle) {
			if (entity.assignmentTitle.matches(assignmentTitle)) {
				entity.assignmentTitle = "Similar assignment Exists";
				return "Similar assignment Exists";
			}
		}
		// Normalize file name
		if (file != null) {
			String fileName = StringUtils.cleanPath(file.getOriginalFilename());
			try {
				// Check if the file's name contains invalid characters
				if (fileName.contains("..")) {
					throw new Exception("Sorry! Filename contains invalid path sequence " + fileName);
				}
				AssignmentEntity dbFile = new AssignmentEntity(fileName, file.getContentType(), file.getBytes(),
						assignmentTitle, courseTitle, dueDate, assignmentDescription);
				assignmentRepository.save(dbFile);
				return "File and Assignment OK";

			} catch (IOException ex) {
				throw new Exception("Could not store file " + fileName + ". Please try again!", ex);
			}
		} else {
			AssignmentEntity dbFile = new AssignmentEntity();
			dbFile.assignmentTitle = assignmentTitle;
			dbFile.courseTitle = courseTitle;
			dbFile.assignmentDescription = assignmentDescription;
			dbFile.dueDate = dueDate;
			assignmentRepository.save(dbFile);
			return "Only Assignment OK";
		}
	}

	public AssignmentEntity getFile(Long assignmentId) {
		return assignmentRepository.findById(assignmentId).orElseThrow();
	}
}
