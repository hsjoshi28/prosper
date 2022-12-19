package com.Prosper.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.Prosper.entity.AssignmentEntity;
import com.Prosper.entity.CourseEntity;
import com.Prosper.entity.UserEntity;

@Repository
public interface CourseRepository extends JpaRepository<CourseEntity, Integer>{
	
	public CourseEntity findByCourseTitle(String courseTitle);
	
	public CourseEntity findByCourseDescription(String courseDescription);
	
	@Query("SELECT c.courseTitle FROM CourseEntity c ")
	List<String> findCourseTitle();
	
	
}
