package com.Prosper.entity;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.Setter;

@Entity
public class CourseEntity {
	@Id
	 @GeneratedValue
	 public Long courseId;
	
	@Getter @Setter(AccessLevel.PACKAGE)
	 public String courseTitle;
	
	@Getter @Setter(AccessLevel.PACKAGE)
	 public String courseDescription;
}
