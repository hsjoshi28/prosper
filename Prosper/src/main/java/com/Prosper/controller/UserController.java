package com.Prosper.controller;


import java.util.List;
import java.util.concurrent.ExecutionException;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.boot.autoconfigure.security.oauth2.client.EnableOAuth2Sso;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
//import org.springframework.security.oauth2.config.annotation.web.configuration.EnableResourceServer;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.Prosper.entity.UserEntity;
import com.Prosper.request.model.UserRequest;
import com.Prosper.response.model.UserResponse;
import com.Prosper.service.StudentMappedCourseService;
import com.Prosper.service.UserService;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import lombok.extern.log4j.Log4j2;

@NoArgsConstructor
@AllArgsConstructor
@Log4j2
@RestController
@CrossOrigin
@RequestMapping("/user")
//@EnableOAuth2Sso
//@EnableResourceServer 
public class UserController {
	
	private static final Logger logger = LogManager.getLogger(UserController.class);
	
	@Autowired
	private UserService userService;

	@PostMapping("/register")
	public ResponseEntity<UserResponse> userData(@RequestBody UserRequest userRequestModel) {
		logger.info("controller : user/register [POST]");
		UserResponse userResponse = userService.userRegisterService(userRequestModel);
		if((userResponse.response == "Username already exists!")||(userResponse.response == "User email already exists!")) {
			return new ResponseEntity<>(userResponse,  HttpStatus.CONFLICT); // response code = 409
		} else {
			return new ResponseEntity<>(userResponse,  HttpStatus.OK); 
		}
	}
	
	@PostMapping("/authentication")
	public ResponseEntity<UserResponse> postUserAuthentication(@RequestBody UserRequest userRegisterRequest) {
		logger.info("controller : user/authentication [POST]");
		UserResponse userResponse = userService.postUserAuthenticationService(userRegisterRequest);
		if((userResponse.response == "Username does not exists, please register.") || (userResponse.response == "Incorrect password!")) {
			return new ResponseEntity<>(userResponse, HttpStatus.UNAUTHORIZED); 
		} else {
			return new ResponseEntity<>(userResponse, HttpStatus.OK); // HTTP Status = 200
		}
	}
	
	@CrossOrigin(origins = {"http://149.165.153.133:3000/*", "http://149.165.153.133:3000", "http://149.165.153.133:3000/passwordreset", "http://localhost:3000", "*"})
	@PutMapping("/forgot/password")
	public ResponseEntity<UserResponse> postForgotPassword(@RequestBody UserRequest userRegisterRequest) {
		logger.info("controller : user/forgot/password [PUT]");
		UserResponse userResponse =  userService.postForgotPasswordService(userRegisterRequest);
		if(userResponse.response == "Entered email does not exists! Please register") {
			return new ResponseEntity<>(userResponse, HttpStatus.UNAUTHORIZED);
		} else if (userResponse.response == "Error while sending email") {
			return new ResponseEntity<>(userResponse, HttpStatus.SERVICE_UNAVAILABLE);  // HTTP Response: 503
		} else {
			return new ResponseEntity<>(userResponse, HttpStatus.OK); 
		}
	}
	
	@PutMapping("/reset_password")
	public ResponseEntity<UserResponse> postResetPassword(@RequestBody UserRequest userRegisterRequest) {
		logger.info("controller : user/reset_password [PUT]");
		UserResponse userResponse = userService.postResetPasswordService(userRegisterRequest);
		if(userResponse.response == "Invalid or Used Token!!") {
			return new ResponseEntity<>(userResponse, HttpStatus.UNAUTHORIZED);
		} else {
			return new ResponseEntity<>(userResponse, HttpStatus.OK); // HTTP Status = 200
		}
	}
	
	@GetMapping("/student")
	public ResponseEntity<List<String>> getAllStudent(){
		logger.info("controller : user/student [GET]");
		List<String> userResponse = userService.getAllStudentService();
		return new ResponseEntity<>(userResponse, HttpStatus.OK);
	}
	
	@GetMapping("/instructor")
	public ResponseEntity<List<String>> getAllInstructor(){
		logger.info("controller : user/instructor [GET]");
		List<String> userResponse = userService.getAllInstructorService();
		return new ResponseEntity<>(userResponse, HttpStatus.OK);
	}
	
	@GetMapping("/all")
	public ResponseEntity<List<String>> getAllUser(){
		logger.info("controller : user/all [GET]");
		List<String> userResponse = userService.getAllUserService();
		return new ResponseEntity<>(userResponse, HttpStatus.OK);
	}
	
	@PutMapping("/create_instructor")
	public ResponseEntity<String> createInstructor(@RequestBody UserRequest userRequest){
		logger.info("controller : user/create_instructor [PUT]");
		String userResponse = userService.createInstructorService(userRequest.userName);
		if(userResponse == "Username invalid!!") {
			return new ResponseEntity<>(userResponse, HttpStatus.BAD_REQUEST);
		}else {
		return new ResponseEntity<>(userResponse, HttpStatus.OK);
		}
	}
	
	@PutMapping("/assign_instructor")
	public ResponseEntity<String> assignCourse(@RequestBody UserRequest userRequest){
		logger.info("controller : user/assign_instructor [PUT]");
		String userResponse = userService.assignCourseService(userRequest);
		if(userResponse == "User name does not exists") {
			return new ResponseEntity<>(userResponse, HttpStatus.BAD_REQUEST);
		}else {
			return new ResponseEntity<>(userResponse, HttpStatus.OK);
		}
	}
	
	@GetMapping("/getAlllDetails")
	public List<UserEntity> getAllUserDetails(){
		return userService.getAllUserDetailsService();
	}
	
	
	// Below will be used to Map Student and Courses...
	
	@Autowired
	private StudentMappedCourseService mappedCourseService;
	
	@PostMapping("/map_student")
	public ResponseEntity<String> mapStudentWithCourse(@RequestBody UserRequest userRequest) throws InterruptedException, ExecutionException{
		logger.info("controller: user/map_student [POST]");
		String userResponse = mappedCourseService.mapStudentCourseService(userRequest);
		if (userResponse == "already mapped") {
			return new ResponseEntity<>(userResponse, HttpStatus.CONFLICT);
		}
		
		return new ResponseEntity<>(userResponse, HttpStatus.OK);
	}
	
	
}
