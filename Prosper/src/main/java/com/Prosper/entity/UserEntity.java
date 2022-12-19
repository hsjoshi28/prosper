package com.Prosper.entity;

import java.time.LocalDateTime;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.PrePersist;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.Setter;

@Entity
public class UserEntity {
	
	 @Id
	 @GeneratedValue
	 public Long userId;
	 
	 @Getter @Setter(AccessLevel.PACKAGE)
	 public String userName;

	 @Getter @Setter(AccessLevel.PACKAGE)
	 public String emailId;
	 
	 @Getter @Setter(AccessLevel.PACKAGE)
	 public String name;
	 
	 @Getter @Setter(AccessLevel.PACKAGE)
	 public String password;
	 
	 @Getter @Setter(AccessLevel.PACKAGE)
	 public String contactNo;

	 @Getter @Setter(AccessLevel.PACKAGE)
	 public int roleId;
	 
	 @Getter @Setter(AccessLevel.PACKAGE)
	 public String resetPasswordToken;
	 
	 //Map Instructor with course.
	 @Getter @Setter(AccessLevel.PACKAGE)
	 public String courseName;
	 
	 @Getter @Setter(AccessLevel.PACKAGE)
	 public String loginToken;
	 
	 @Getter @Setter(AccessLevel.PACKAGE)
	 public String OTP;
	 
	 @Getter @Setter(AccessLevel.PACKAGE)
	 public LocalDateTime OTPTime;
	 
	 @PrePersist
	 void preInsert() {
	    if (this.roleId == 0) {
	        this.roleId = 1;
	    }
	 }
}
