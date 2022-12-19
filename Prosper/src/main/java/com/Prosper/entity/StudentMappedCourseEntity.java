package com.Prosper.entity;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.Setter;

@Entity
public class StudentMappedCourseEntity {
	@Id
	@GeneratedValue
	public Long StudentMappedCourseId;
	
	@Getter @Setter(AccessLevel.PACKAGE)
	 public String userName;
	
	@Getter @Setter(AccessLevel.PACKAGE)
	 public String courseName;

}
