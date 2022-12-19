package com.Prosper.entity;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Lob;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.Setter;

@Entity
public class ModuleEntity {
	
	@Id
	 @GeneratedValue
	 public Long moduleEntityId;
	
	@Getter @Setter(AccessLevel.PACKAGE)
	 public String courseTitle;
	
	@Getter @Setter(AccessLevel.PACKAGE)
	 public String moduleTitle;
	
	@Getter @Setter(AccessLevel.PACKAGE)
	 public String moduleDescription;
	
	@Getter @Setter(AccessLevel.PACKAGE)
	public String fileName;

	@Getter @Setter(AccessLevel.PACKAGE)
	public String fileType;

	@Getter @Setter(AccessLevel.PACKAGE)
    @Lob
    public byte[] data;

	public ModuleEntity(String courseTitle, String moduleTitle, String moduleDescription, String fileName,
			String fileType, byte[] data) {
		this.courseTitle = courseTitle;
		this.moduleTitle = moduleTitle;
		this.moduleDescription = moduleDescription;
		this.fileName = fileName;
		this.fileType = fileType;
		this.data = data;
	}
	
	public ModuleEntity() {}
	
	

}
