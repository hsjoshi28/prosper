package com.Prosper.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.Prosper.entity.SubmitAssignmentEntity;
import com.Prosper.response.model.StudentSubmissionResponse;

@Repository
public interface SubmitAssignmentRepository extends JpaRepository<SubmitAssignmentEntity, Long> {
	
	public SubmitAssignmentEntity findBySubmitAssignmentEntityId(Long submitAssignmentEntityId);

	public List<SubmitAssignmentEntity> findByCourseTitle(String courseTitle);

	public List<SubmitAssignmentEntity> findByCourseTitleAndUserName(String courseTitle, String userName);

}
