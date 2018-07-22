package com.user188245.timetable.model.dao;

import java.util.Optional;

import org.springframework.data.jpa.repository.Query;

import com.user188245.timetable.model.dto.Authority;
import com.user188245.timetable.model.dto.User;

public interface UserRepository extends TimeTableRepository<User>{

	@Query("Select s from User s where s.username = ?1")
	public Optional<User> findByUsername(String username);
	
	@Query("Select s from User s where s.email = ?1")
	public Optional<User> findByEmail(String email);
	
}
