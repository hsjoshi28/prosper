package com.Prosper.controller;

import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.Prosper.entity.AssignmentEntity;
import com.Prosper.entity.CourseEntity;
import com.Prosper.request.model.CourseRequest;
import com.Prosper.response.model.CourseResponse;
import com.Prosper.service.CourseService;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import lombok.extern.log4j.Log4j2;

@NoArgsConstructor
@AllArgsConstructor
@Log4j2
@RestController
@CrossOrigin
@RequestMapping("/course")
public class CourseController {
	
	private static final Logger logger = LogManager.getLogger(CourseController.class);
	@Autowired
	private CourseService courseService;
	
	@PostMapping("/add")
	public ResponseEntity<CourseResponse> courseData(@RequestBody CourseRequest courseRequestModel) {
		logger.info("Course Controller : /add [POST] courseTitle="+courseRequestModel.courseTitle);
		CourseResponse response =  courseService.courseRegisterService(courseRequestModel); 
		if(response.response == "Course already exists!") {
			return new ResponseEntity<>(response, HttpStatus.CONFLICT);
		}
		return new ResponseEntity<>(response, HttpStatus.OK);
	}
	
	@GetMapping("/get")
	public ResponseEntity<List<String>> getcourseData() {
		logger.info("Course Controller : /get LIST of courses String [GET]  ");
		return new ResponseEntity<>(courseService.getCourseService(), HttpStatus.OK);
	}
	
	@GetMapping("/get/{courseTitle}")
	public ResponseEntity<CourseEntity> getCourseDetails(@PathVariable String courseTitle){
		logger.info("Course Controller : /get/{courseTitle} [GET] courseTitle="+courseTitle);
		return new ResponseEntity<>(courseService.getCourseDetailsService(courseTitle), HttpStatus.OK);
	}
	
	//Below will be used to get course Details for student and instructor
	@GetMapping("/getCourse")
	public List<CourseEntity> getCoursesByUserName(@RequestParam String userName){
		logger.info("Course Controller : /getCourse [GET] userName="+userName);
		return courseService.getCourseDetailsByUsername(userName);
	}
	
}
