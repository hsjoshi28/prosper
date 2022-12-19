package com.Prosper.entity;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.Setter;

@Entity
public class AnnouncementEntity {
	
	@Id
	 @GeneratedValue
	 public Long announcementId;
	
	@Getter @Setter(AccessLevel.PACKAGE)
	public String announcementTitle;
	
	@Getter @Setter(AccessLevel.PACKAGE)
	public String announcementDescription;
	
	@Getter @Setter(AccessLevel.PACKAGE)
	public String courseTitle;
	
	@Getter @Setter(AccessLevel.PACKAGE)
	public int isApproved;
	
	@Getter @Setter(AccessLevel.PACKAGE)
	public String timeStamp;
	
}
