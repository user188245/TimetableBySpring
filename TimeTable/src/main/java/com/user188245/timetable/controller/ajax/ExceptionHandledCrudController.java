package com.user188245.timetable.controller.ajax;

import java.sql.SQLException;
import java.util.NoSuchElementException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.dao.InvalidDataAccessApiUsageException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

import com.user188245.timetable.controller.MainController;
import com.user188245.timetable.model.core.exception.CustomException;
import com.user188245.timetable.model.dto.BasicDTO;
import com.user188245.timetable.model.dto.Request;
import com.user188245.timetable.model.dto.Response;

public abstract class ExceptionHandledCrudController<T extends Request> implements SecurityCrudController<T, Long> {
	
	private final Logger logger = LoggerFactory.getLogger(MainController.class);

	@ResponseBody
	@ExceptionHandler(CustomException.class)
	public ResponseEntity<Response> customExceptionHandler(CustomException e) {
		logger.error(e.getClass().getName() + " : " + e.getLocalizedMessage());
		return Response.buildBadResponse(e.getErrorCode(), e.getMessage());
	}
	
	@ResponseBody
	@ExceptionHandler(SQLException.class)
	public ResponseEntity<Response> sqlExceptionHandler(SQLException e) {
		logger.error(e.getClass().getName() + " : " + e.getLocalizedMessage());
		return Response.buildBadResponse(1006, "Wrong DB Access or Transaction");
	}
	
	@ResponseBody
	@ExceptionHandler(NoSuchElementException.class)
	public ResponseEntity<Response> noSuchElementExceptionHandler(NoSuchElementException e) {
		logger.error(e.getClass().getName() + " : " + e.getLocalizedMessage());
		return Response.buildInvalidResponseEntity(1007, "Not Found", HttpStatus.NOT_FOUND);
	}
	
	@ResponseBody
	@ExceptionHandler(Exception.class)
	public ResponseEntity<Response> exceptionHandler(Exception e) {
		logger.error(e.getClass().getName() + " : " + e.getLocalizedMessage());
		logger.error("check : ", e);
		return Response.buildBadResponse(1000, "Unknown Error");
	}
	
	@ResponseBody
	@ExceptionHandler(InvalidDataAccessApiUsageException.class)
	public ResponseEntity<Response> invalidDataAccessApiUsageExceptionHandler(InvalidDataAccessApiUsageException e) {
		logger.error(e.getClass().getName() + " : " + e.getLocalizedMessage());
		logger.error("check : ", e);
		return Response.buildBadResponse(1050, "Check if there is missing parameters. See : " + e.getLocalizedMessage());
	}
	
	
	
}
