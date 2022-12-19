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
import org.springframework.http.ResponseEntity.BodyBuilder;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.Prosper.entity.AssignmentEntity;
import com.Prosper.entity.SubmitAssignmentEntity;
import com.Prosper.request.model.AssignmentRequest;
import com.Prosper.response.model.AssignmentResponse;
import com.Prosper.response.model.StudentSubmissionResponse;
import com.Prosper.response.model.UploadFileResponse;
import com.Prosper.service.AssignmentService;
import com.Prosper.service.SubmitAssignmentService;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import lombok.extern.log4j.Log4j2;

@NoArgsConstructor
@AllArgsConstructor
@Log4j2
@RestController
@CrossOrigin
@RequestMapping("/assignment")
public class AssignmentController {
	
	private static final Logger logger = LogManager.getLogger(AssignmentController.class);

	@Autowired
	private AssignmentService assignmentService;

	@GetMapping("/get")
	public ResponseEntity<List<AssignmentEntity>> getassignmentData(@RequestParam String courseTitle) {
		logger.info("Assignment Controller : /get [GET] courseTitle="+courseTitle);
		List<AssignmentEntity> response = assignmentService.getAssignment(courseTitle);
		return new ResponseEntity<>(response, HttpStatus.OK);
	}

	@PostMapping("/uploadAssignment/{assignmentTitle}/{courseTitle}/{dueDate}/{assignmentDescription}")
	public ResponseEntity<String> uploadFile(@PathVariable String assignmentTitle,@PathVariable String courseTitle, 
			@PathVariable String assignmentDescription, @PathVariable String dueDate, @RequestParam("file") MultipartFile file)
			throws Exception {
		logger.info("Assignment Controller : /uploadAssignment [POST] courseTitle="+courseTitle+" assignmentTitle= "+assignmentTitle+" file="+file.getName());
		String response = assignmentService.storeFile(file, assignmentTitle, courseTitle, dueDate, assignmentDescription);
		if(response == "Similar assignment Exists") {
			return new ResponseEntity<>("Similar assignment Exists",HttpStatus.CONFLICT);
		}
		return new ResponseEntity<>("Assignment uploaded successfully",HttpStatus.OK);
	}

	@GetMapping("/downloadFile/{assignmentId}")
	public ResponseEntity<Resource> downloadFile(@PathVariable Long assignmentId) {
		logger.info("Assignment Controller : /downloadFile [GET] assignmentId="+assignmentId);
		// Load file from database
		AssignmentEntity dbFile = assignmentService.getFile(assignmentId);
		
		if(dbFile.fileType == null) {
			return ResponseEntity.noContent().build();
		}

		return ResponseEntity.ok().contentType(MediaType.parseMediaType(dbFile.fileType))
				.header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=\"" + dbFile.fileName + "\"")
				.body(new ByteArrayResource(dbFile.data));
	}
	
	// Above Controllers will be used for submitting assignments 
	
	@Autowired
	private SubmitAssignmentService submitAssignmentService;
	
	@PostMapping("/submitAssignment/{assignmentTitle}/{courseTitle}/{userName}/{text}")
	public ResponseEntity<String> submitAssignmentController(@PathVariable String assignmentTitle,@PathVariable String courseTitle, 
			@PathVariable String userName, @PathVariable String text, @RequestParam(name="file",required=false) MultipartFile file)
			throws Exception {
		logger.info("Assignment Controller : /submitAssignment [POST] courseTitle="+courseTitle+" assignmentTitle= "+assignmentTitle+" userName "+userName+ "file "+ file);
		String response = submitAssignmentService.submitAssignmentService(file, assignmentTitle, courseTitle, userName, text);
		if(response == "Similar assignment Exists") {
			return new ResponseEntity<>("Similar assignment Exists",HttpStatus.CONFLICT);
		}
		return new ResponseEntity<>("Student Assignment uploaded successfully",HttpStatus.OK);
	}
	
	@GetMapping("/downloadAssignment/{submitAssignmentId}")
	public ResponseEntity<Resource> downloadStudentFile(@PathVariable Long submitAssignmentId) {
		logger.info("Assignment Controller : /downloadAssignmentFile [GET] submitAssignmentId="+submitAssignmentId);
		// Load file from database
		SubmitAssignmentEntity dbFile = submitAssignmentService.getFile(submitAssignmentId);
		
		if(dbFile.fileType == null) {
			return ResponseEntity.noContent().build();
		}

		return ResponseEntity.ok().contentType(MediaType.parseMediaType(dbFile.fileType))
				.header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=\"" + dbFile.fileName + "\"")
				.body(new ByteArrayResource(dbFile.data));
	}
	
	@PutMapping("/grade")
	public String gradeStudentAssignmentController(@RequestParam Long  submitAssignmentId, @RequestParam String  grade) {
		logger.info("Assignment Controller : /grade [PUT] courseTitle="+submitAssignmentId+" grade "+grade);
		submitAssignmentService.gradeAssignmentService(submitAssignmentId, grade);
		return "OK";
	}
	
	@GetMapping("/getStudentSubmission")
	public List<StudentSubmissionResponse> getStudentSubmissionController(@RequestParam String courseTitle) {
		logger.info("Assignment Controller : /getStudentSubmission [GET] courseTitle="+courseTitle);
		return submitAssignmentService.getStudentSubmissionService(courseTitle);
	}
	
	@GetMapping("/getGrade")
	public List<SubmitAssignmentEntity> getStudenGradeController(@RequestParam String courseTitle, @RequestParam String userName) {
		logger.info("Assignment Controller : /getStudentSubmission [GET] courseTitle="+courseTitle);
		return submitAssignmentService.getStudentGradeService(courseTitle, userName);
	}

}
