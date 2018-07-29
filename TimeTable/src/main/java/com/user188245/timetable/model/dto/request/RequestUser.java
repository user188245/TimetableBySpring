package com.user188245.timetable.model.dto.request;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

public class RequestUser extends Request{
	
	@NotBlank
	@Size(min=4, max=32)
	private String username;
	
	@NotBlank
	@Size(min=8, max=64)
	private String password;
	
	@NotBlank
	private String passwordValidation;
	
	@Email
	@NotBlank
	private String email;
	
	@Size(max=120)
	private String description;
	
	public RequestUser() {}
	
	public RequestUser(String username, String password, String passwordValidation, String email, String description) {
		this.username = username;
		this.password = password;
		this.passwordValidation = passwordValidation;
		this.email = email;
		this.description = description;
	}

	public String getUsername() {
		return username;
	}

	public String getPassword() {
		return password;
	}

	public String getPasswordValidation() {
		return passwordValidation;
	}

	public String getEmail() {
		return email;
	}

	public String getDescription() {
		return description;
	}
	
	
	

}
