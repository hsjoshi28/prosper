package com.Prosper.service;

import java.io.IOException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;

import com.Prosper.entity.ModuleEntity;
import com.Prosper.repository.ModuleRepository;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import lombok.extern.log4j.Log4j2;

@NoArgsConstructor
@AllArgsConstructor
@Log4j2
@Service
public class ModuleService {

	@Autowired
	private ModuleRepository moduleRepository;

	public String addModuleService(String courseTitle, String moduleTitle, String moduleDescription,
			MultipartFile file) throws Exception {

		ModuleEntity entity = moduleRepository.findByModuleTitleAndCourseTitle(moduleTitle, courseTitle);

		if (entity == null) {
			
				String fileName = StringUtils.cleanPath(file.getOriginalFilename());
				try {
					// Check if the file's name contains invalid characters
					if (fileName.contains("..")) {
						throw new Exception("Sorry! Filename contains invalid path sequence " + fileName);
					}
					ModuleEntity moduleEntity = new ModuleEntity(courseTitle, moduleTitle, moduleDescription, fileName,
							file.getContentType(), file.getBytes());
					moduleRepository.save(moduleEntity);
					return "File and Assignment OK";

				} catch (IOException ex) {
					throw new Exception("Could not store file " + fileName + ". Please try again!", ex);

				}
			
		}
			return "Similar module exists";
	}

	public ModuleEntity getFile(Long moduleEntityId) {
		return moduleRepository.findByModuleEntityId(moduleEntityId);
	}

	public List<ModuleEntity> getModuleService(String courseTitle) {
		// TODO Auto-generated method stub
		List<ModuleEntity> entities = moduleRepository.findByCourseTitle(courseTitle);
		return entities;
	}
	
	
}
