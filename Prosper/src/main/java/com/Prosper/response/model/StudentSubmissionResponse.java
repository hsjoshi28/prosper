package com.Prosper.response.model;

import lombok.Setter;

public class StudentSubmissionResponse {

	@Setter
	public Long studentSubmissionResponseId;

	@Setter
	public String userName;

	@Setter
	public String name;

	@Setter
	public String courseName;
	
	@Setter 
	public String assignmentTitle;

	@Setter
	public String textSubmission;

	@Setter
	public String fileName;

}
