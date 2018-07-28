package com.user188245.timetable.model.core.security.service;

import java.sql.SQLException;

import org.springframework.stereotype.Service;

import com.user188245.timetable.model.dto.User;

@Service
public interface RegistrationService {
	
	public void signup(User user) throws SQLException;
	
	public boolean checkUsernameDuplication(String username);
	
	public boolean checkEmailDuplication(String email);
	
}
