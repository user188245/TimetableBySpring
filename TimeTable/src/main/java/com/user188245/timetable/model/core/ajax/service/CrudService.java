package com.user188245.timetable.model.core.ajax.service;

import java.sql.SQLException;
import java.util.NoSuchElementException;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;

import com.user188245.timetable.model.core.exception.BadAccessException;
import com.user188245.timetable.model.dto.request.Request;
import com.user188245.timetable.model.dto.response.DataResponse;

@Service
public interface CrudService<RQ extends Request, T, K> {
	
	public ResponseEntity<DataResponse<T>> read(String username, K id) throws BadAccessException, NoSuchElementException, SQLException;
	
	public ResponseEntity<DataResponse<Iterable<T>>> readAll(String username) throws SQLException;
	
	public void create(String username, @RequestBody RQ request) throws BadAccessException,SQLException;
	
	public void update(String username, @RequestBody RQ request) throws BadAccessException,NoSuchElementException,SQLException;
	
	public void delete(String username, @RequestParam(value="id",required = true) K id) throws BadAccessException,NoSuchElementException,SQLException;

}
