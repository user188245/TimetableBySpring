package com.user188245.timetable.model.core.ajax.service;

import java.sql.SQLException;

import org.springframework.http.ResponseEntity;
import org.springframework.security.access.annotation.Secured;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Service;

import com.user188245.timetable.model.dto.response.DataResponse;

@Service
public interface ReadableService<T> {
	
	@Secured("ROLE_READ")
	public ResponseEntity<DataResponse<Iterable<T>>> readAll(String username) throws SQLException;

}
