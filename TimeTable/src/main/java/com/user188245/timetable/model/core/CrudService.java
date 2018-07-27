package com.user188245.timetable.model.core;

import java.sql.SQLException;
import java.util.NoSuchElementException;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.user188245.timetable.model.core.exception.BadAccessException;
import com.user188245.timetable.model.dto.DataResponse;
import com.user188245.timetable.model.dto.Request;

@RestController
public interface CrudService<RQ extends Request, T, K> {
	
	public ResponseEntity<DataResponse<T>> read(String username, @RequestParam(value="id",required = false) K id) throws BadAccessException, NoSuchElementException, SQLException;
	
	public ResponseEntity<DataResponse<Iterable<T>>> readAll(String username) throws SQLException;
	
	public void create(String username, @RequestBody RQ request) throws BadAccessException,SQLException;
	
	public void update(String username, @RequestBody RQ request) throws BadAccessException,NoSuchElementException,SQLException;
	
	public void delete(String username, @RequestParam(value="id",required = true) K id) throws BadAccessException,NoSuchElementException,SQLException;

}
