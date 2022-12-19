package com.Prosper.response.model;

public class FireBaseMapping {
	
	private String courseTitle;
	
	private String userName;

	public String getCourseTitle() {
		return courseTitle;
	}

	public void setCourseTitle(String courseTitle) {
		this.courseTitle = courseTitle;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public FireBaseMapping(String courseTitle, String userName) {
		this.courseTitle = courseTitle;
		this.userName = userName;
	}
	
	

}
