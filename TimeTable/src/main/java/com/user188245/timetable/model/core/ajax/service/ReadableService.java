package com.user188245.timetable.model.core.ajax.service;

import java.sql.SQLException;

import org.springframework.http.ResponseEntity;

import com.user188245.timetable.model.dto.response.DataResponse;

public interface ReadableService<T> {
	
	public ResponseEntity<DataResponse<Iterable<T>>> readAll(String username) throws SQLException;

}
