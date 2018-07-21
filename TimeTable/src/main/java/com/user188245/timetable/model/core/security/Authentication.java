package com.user188245.timetable.model.core.security;

import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;

public interface Authentication {
	
	public void configure(AuthenticationManagerBuilder auth);
	
	public boolean checkExists(String username);
	
}
