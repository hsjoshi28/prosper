package com.Prosper.service;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;

import com.Prosper.entity.SubmitAssignmentEntity;
import com.Prosper.entity.UserEntity;
import com.Prosper.repository.SubmitAssignmentRepository;
import com.Prosper.repository.UserRepository;
import com.Prosper.response.model.StudentSubmissionResponse;

@Service
public class SubmitAssignmentService {
	
	@Autowired
	private SubmitAssignmentRepository submitAssignmentRepository;
	
	public String submitAssignmentService(MultipartFile file, String assignmentTitle, String courseTitle, String userName,
			String textSubmission) throws Exception {
//		System.out.println(file.getSize());
		// Normalize file name
		if (file != null && file.getSize() != 0) {
			String fileName = StringUtils.cleanPath(file.getOriginalFilename());
			try {
				// Check if the file's name contains invalid characters
				if (fileName.contains("..")) {
					throw new Exception("Sorry! Filename contains invalid path sequence " + fileName);
				}

				SubmitAssignmentEntity submitAssignmentEntity = new SubmitAssignmentEntity(assignmentTitle, courseTitle, userName, fileName, file.getContentType(), file.getBytes(), textSubmission);
				submitAssignmentRepository.save(submitAssignmentEntity);
				return "Submit: File and Assignment OK";
			} catch (IOException ex) {
				throw new Exception("Could not store file " + fileName + ". Please try again!", ex);
			}
		} else {
			SubmitAssignmentEntity submitAssignmentEntity = new SubmitAssignmentEntity();
			submitAssignmentEntity.assignmentTitle = assignmentTitle;
			submitAssignmentEntity.courseTitle = courseTitle;
			submitAssignmentEntity.userName = userName;
			submitAssignmentEntity.textSubmission = textSubmission;
			submitAssignmentRepository.save(submitAssignmentEntity);
			return "Submit Text Submission OK";
		}
	}

	public SubmitAssignmentEntity getFile(Long assignmentId) {
		return submitAssignmentRepository.findById(assignmentId).orElseThrow();
	}

	public String gradeAssignmentService(Long submitAssignmentId, String grade) {
		SubmitAssignmentEntity assignmnetDb = submitAssignmentRepository.findBySubmitAssignmentEntityId(submitAssignmentId);
		assignmnetDb.grade = grade;
		submitAssignmentRepository.save(assignmnetDb);
		return "OK";
	}

	@Autowired
	private UserRepository userRepository;
	
	public List<StudentSubmissionResponse> getStudentSubmissionService(String courseTitle) {
		List<StudentSubmissionResponse> submissionList = new ArrayList<>();
		
		List<SubmitAssignmentEntity> entity = submitAssignmentRepository.findByCourseTitle(courseTitle);
		
		for(SubmitAssignmentEntity ent : entity) {
			UserEntity uEntity = userRepository.findByUserName(ent.userName);
			StudentSubmissionResponse submissionResponse = new StudentSubmissionResponse();
			
			submissionResponse.studentSubmissionResponseId = ent.submitAssignmentEntityId;
			submissionResponse.name = uEntity.name;
			submissionResponse.courseName = courseTitle;
			submissionResponse.assignmentTitle = ent.assignmentTitle;
			submissionResponse.textSubmission = ent.textSubmission;
			submissionResponse.userName= ent.userName;
			submissionResponse.fileName = ent.fileName;
			submissionList.add(submissionResponse);
		}
		
		return submissionList;
		
		
	}

	public List<SubmitAssignmentEntity> getStudentGradeService(String courseTitle, String userName) {
		List<SubmitAssignmentEntity> gradeList = submitAssignmentRepository.findByCourseTitleAndUserName(courseTitle, userName);
		return gradeList;
	}

}
