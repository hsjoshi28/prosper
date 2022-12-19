package com.Prosper.entity;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Lob;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.Setter;

@Entity
public class AssignmentEntity {
	@Id
	 @GeneratedValue
	 public Long assignmentId;
	
	@Getter @Setter(AccessLevel.PACKAGE)
	 public String assignmentTitle;
	
	@Getter @Setter(AccessLevel.PACKAGE)
	 public String assignmentDescription;
	
	@Getter @Setter(AccessLevel.PACKAGE)
	public String courseTitle;
	
	@Getter @Setter(AccessLevel.PACKAGE)
	public String dueDate;
	
	@Getter @Setter(AccessLevel.PACKAGE)
	public String fileName;

	@Getter @Setter(AccessLevel.PACKAGE)
	public String fileType;

	@Getter @Setter(AccessLevel.PACKAGE)
    @Lob
    public byte[] data;

    public AssignmentEntity() {

    }

    public AssignmentEntity(String fileName, String fileType, byte[] data, String assignmentTitle, String courseTitle ,String dueDate, String assignmentDescription) {
        this.fileName = fileName;
        this.fileType = fileType;
        this.data = data;
        this.assignmentTitle = assignmentTitle;
        this.courseTitle = courseTitle;
        this.assignmentDescription = assignmentDescription;
        this.dueDate = dueDate;
    }
}
