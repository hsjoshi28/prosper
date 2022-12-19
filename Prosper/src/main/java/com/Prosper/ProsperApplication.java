package com.Prosper;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
//import org.springframework.security.oauth2.config.annotation.web.configuration.EnableResourceServer;

import com.Prosper.service.RoleService;
import com.Prosper.service.UserService;

@SpringBootApplication
//@EnableResourceServer 
public class ProsperApplication implements CommandLineRunner{
	
	@Autowired
	private UserService userService = new UserService();
	@Autowired
	private RoleService roleSerive = new RoleService();

	public static void main(String[] args) {
		SpringApplication.run(ProsperApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		roleSerive.createRoles();
		userService.createAdmin();
	}

}
