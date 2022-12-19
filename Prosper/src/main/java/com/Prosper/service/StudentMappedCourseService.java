package com.Prosper.service;

import java.util.List;
import java.util.concurrent.ExecutionException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.Prosper.entity.StudentMappedCourseEntity;
import com.Prosper.repository.StudentMappedCourseRepository;
import com.Prosper.request.model.UserRequest;
import com.Prosper.response.model.FireBaseMapping;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import lombok.extern.log4j.Log4j2;

@NoArgsConstructor
@AllArgsConstructor
@Log4j2
@Service
public class StudentMappedCourseService {
	
	@Autowired
	private StudentMappedCourseRepository mappedCourseRepository;

//	 @Autowired
//	 private FireBaseMappingService fireBaseMappingService;
	
	private StudentMappedCourseEntity mappedCourseEntity;
	

	public String mapStudentCourseService(UserRequest userRequest) throws InterruptedException, ExecutionException {
		mappedCourseEntity = new StudentMappedCourseEntity();
		List<StudentMappedCourseEntity> entity = mappedCourseRepository.findByUserName(userRequest.userName);
		for(StudentMappedCourseEntity entity1 : entity) {
			if(entity1.courseName.matches(userRequest.courseName)) {
				return "already mapped";
			}
		}
		mappedCourseEntity.userName = userRequest.userName;
		mappedCourseEntity.courseName = userRequest.courseName;
//		FireBaseMapping fbmapping = new FireBaseMapping(userRequest.courseName, userRequest.userName); 
//		fireBaseMappingService.savePatientDetails(fbmapping);
		mappedCourseRepository.save(mappedCourseEntity);
		
		
		return "OK";
	}

}
