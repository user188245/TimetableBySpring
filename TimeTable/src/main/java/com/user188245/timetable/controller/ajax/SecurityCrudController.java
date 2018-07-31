package com.user188245.timetable.controller.ajax;

import java.security.Principal;
import java.sql.SQLException;
import java.util.NoSuchElementException;

import javax.validation.Valid;

import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestBody;

import com.user188245.timetable.model.core.exception.BadAccessException;
import com.user188245.timetable.model.dto.request.Request;
import com.user188245.timetable.model.dto.response.Response;

public interface SecurityCrudController<T extends Request ,K> {
	
	public ResponseEntity<? extends Response> readAll(Principal principal) throws SQLException;
	
	public ResponseEntity<? extends Response> delete(Principal principal, K key) throws BadAccessException,NoSuchElementException,SQLException;
	
	public ResponseEntity<? extends Response> create(
			@Valid @RequestBody T data, 
			BindingResult bindingResult,
			Principal principal
	) throws BadAccessException,SQLException;
	
	public ResponseEntity<? extends Response> update(
			@Valid @RequestBody T data, 
			BindingResult bindingResult,
			Principal principal
	) throws BadAccessException,NoSuchElementException,SQLException;

}
