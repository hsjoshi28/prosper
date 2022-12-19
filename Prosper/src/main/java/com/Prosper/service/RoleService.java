package com.Prosper.service;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.Prosper.entity.RoleEntity;
import com.Prosper.repository.RoleRepository;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import lombok.extern.log4j.Log4j2;

@NoArgsConstructor
@AllArgsConstructor
@Log4j2
@Service
public class RoleService {
	
	@Autowired
	private RoleRepository roleRepository;
	
	
	private RoleEntity roleEntity = new RoleEntity();
	
	private static final Logger logger = LogManager.getLogger(RoleService.class);
	
	public void createRoles() {
		roleEntity = new RoleEntity();
		roleEntity.roleId = (long) 1;
		roleEntity.RoleName = "Student";
		roleRepository.save(roleEntity);
		
		roleEntity = new RoleEntity();
		roleEntity.roleId = (long) 2;
		roleEntity.RoleName = "Instructor";
		roleRepository.save(roleEntity);
		
		roleEntity = new RoleEntity();
		roleEntity.roleId = (long) 3;
		roleEntity.RoleName = "Admin";
		roleRepository.save(roleEntity);
		
		logger.info("Roles created successfully");
	}

}
