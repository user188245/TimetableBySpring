package com.user188245.timetable.model.core.ajax.service;

import java.sql.SQLException;
import java.util.NoSuchElementException;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.user188245.timetable.model.core.exception.BadAccessException;
import com.user188245.timetable.model.dto.request.Request;
import com.user188245.timetable.model.dto.response.DataResponse;

@Service
public interface SingleReadableService<T, K> extends ReadableService<T> {
	
	public ResponseEntity<DataResponse<T>> read(String username, K id) throws BadAccessException, NoSuchElementException, SQLException;
	
	
	
}
