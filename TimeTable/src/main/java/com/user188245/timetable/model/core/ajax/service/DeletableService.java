package com.user188245.timetable.model.core.ajax.service;

import java.sql.SQLException;
import java.util.NoSuchElementException;

import org.springframework.security.access.annotation.Secured;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestParam;

import com.user188245.timetable.model.core.exception.BadAccessException;

@Service
public interface DeletableService<T, K> {
	
	@Secured("ROLE_WRITE")
	public void delete(String username, @RequestParam(value="id",required = true) K id) throws BadAccessException,NoSuchElementException,SQLException;

}
