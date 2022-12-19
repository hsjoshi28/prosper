package com.Prosper.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.Prosper.entity.StudentMappedCourseEntity;

public interface StudentMappedCourseRepository extends JpaRepository<StudentMappedCourseEntity, Integer> {

	public List<StudentMappedCourseEntity> findByUserName(String userName);

}
