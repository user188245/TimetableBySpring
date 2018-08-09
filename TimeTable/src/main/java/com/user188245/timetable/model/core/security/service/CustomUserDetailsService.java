package com.user188245.timetable.model.core.security.service;

import java.sql.SQLException;
import java.util.NoSuchElementException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.user188245.timetable.model.dao.UserRepository;
import com.user188245.timetable.model.dto.User;

@Service
public class CustomUserDetailsService implements UserDetailsService, RegistrationService{
	
	@Autowired
	private UserRepository userRepository; 

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		try{
			return userRepository.findByUsername(username).get().flagPatchToSet();
		}catch(NoSuchElementException e) {
			throw new UsernameNotFoundException("the user doesn't exists!");
		}
	}

	@Override
	public void signup(User user) throws SQLException{
		try {
			userRepository.save(user);
		}catch(Exception e) {
			throw e;
		}
	}

	@Override
	public boolean checkUsernameDuplication(String username){
		return userRepository.findByUsername(username).isPresent();
	}

	@Override
	public boolean checkEmailDuplication(String email){
		return userRepository.findByEmail(email).isPresent();
	}

}
