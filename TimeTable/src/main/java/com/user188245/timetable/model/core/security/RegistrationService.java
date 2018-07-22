package com.user188245.timetable.model.core.security;

import org.springframework.stereotype.Service;

import com.user188245.timetable.model.dto.User;

@Service
public interface RegistrationService {
	
	public void signup(User user) throws Exception;
	
	public boolean checkUsernameDuplication(String username);
	
	public boolean checkEmailDuplication(String email);
	
}
