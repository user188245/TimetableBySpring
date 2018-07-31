package com.user188245.timetable.model.core.ajax.service;

import java.sql.SQLException;
import java.util.NoSuchElementException;

import org.springframework.http.ResponseEntity;
import org.springframework.security.access.annotation.Secured;

import com.user188245.timetable.model.core.exception.BadAccessException;
import com.user188245.timetable.model.dto.response.DataResponse;

public interface MultiReadableService<T, K> extends ReadableService<T> {
	
	@Secured("ROLE_READ")
	public ResponseEntity<DataResponse<Iterable<T>>> read(String username, K findBy) throws BadAccessException, NoSuchElementException, SQLException;


}
