package com.Prosper.entity;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Lob;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.Setter;

@Entity
public class SubmitAssignmentEntity {
	
	@Id
	@GeneratedValue
	public Long submitAssignmentEntityId;
	
	@Getter @Setter (AccessLevel.PACKAGE)
	 public String assignmentTitle;
	
	@Getter @Setter (AccessLevel.PACKAGE)
	 public String courseTitle;
	
	@Getter @Setter (AccessLevel.PACKAGE)
	 public String userName;
	
	@Getter @Setter (AccessLevel.PACKAGE)
	 public String textSubmission;
	
	@Getter @Setter(AccessLevel.PACKAGE)
	public String grade;
	
	@Getter @Setter(AccessLevel.PACKAGE)
	public String fileName;

	@Getter @Setter(AccessLevel.PACKAGE)
	public String fileType;

	@Getter @Setter(AccessLevel.PACKAGE)
    @Lob
    public byte[] data;
	
	public SubmitAssignmentEntity () {}

	public SubmitAssignmentEntity(String assignmentTitle, String courseTitle, String userName, String fileName,
			String fileType, byte[] data, String textSubmission) {
		this.assignmentTitle = assignmentTitle;
		this.courseTitle = courseTitle;
		this.userName = userName;
		this.fileName = fileName;
		this.fileType = fileType;
		this.data = data;
		this.textSubmission = textSubmission;
	}

	public SubmitAssignmentEntity(String assignmentTitle, String courseTitle, String userName, String textSubmission) {
		this.assignmentTitle = assignmentTitle;
		this.courseTitle = courseTitle;
		this.userName = userName;
		this.textSubmission = textSubmission;
	}
	
	
	

}
