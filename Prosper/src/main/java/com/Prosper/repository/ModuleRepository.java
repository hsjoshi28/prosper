package com.Prosper.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.Prosper.entity.ModuleEntity;

public interface ModuleRepository extends JpaRepository<ModuleEntity, Integer>{
	
ModuleEntity findByModuleTitleAndCourseTitle(String moduleTitle, String courseTitle);

ModuleEntity findByModuleEntityId(Long moduleEntityId);

List<ModuleEntity> findByCourseTitle(String courseTitle);

}
