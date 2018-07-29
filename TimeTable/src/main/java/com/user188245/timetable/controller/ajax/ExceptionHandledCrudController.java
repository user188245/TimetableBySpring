package com.user188245.timetable.controller.ajax;

import java.sql.SQLException;
import java.time.format.DateTimeParseException;
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
import com.user188245.timetable.model.dto.request.Request;
import com.user188245.timetable.model.dto.response.Response;

public abstract class ExceptionHandledCrudController<T extends Request> implements SecurityCrudController<T, Long> {
	
	private final Logger logger = LoggerFactory.getLogger(MainController.class);

	@ResponseBody
	@ExceptionHandler(CustomException.class)
	public ResponseEntity<Response> customExceptionHandler(CustomException e) {
		logger.error(e.getClass().getName() + " : " + e.getLocalizedMessage());
		return Response.buildInvalidResponseEntity(e.getErrorCode(), e.getMessage(), null, e.getStatus());
	}
	
	@ResponseBody
	@ExceptionHandler(SQLException.class)
	public ResponseEntity<Response> sqlExceptionHandler(SQLException e) {
		logger.error(e.getClass().getName() + " : " + e.getLocalizedMessage());
		return Response.buildInvalidResponseEntity(1006, "Wrong DB Access or Transaction", null, HttpStatus.INTERNAL_SERVER_ERROR);
	}
	
	@ResponseBody
	@ExceptionHandler(NoSuchElementException.class)
	public ResponseEntity<Response> noSuchElementExceptionHandler(NoSuchElementException e) {
		logger.error(e.getClass().getName() + " : " + e.getLocalizedMessage());
		return Response.buildInvalidResponseEntity(1007, "Not Found", null, HttpStatus.NOT_FOUND);
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
	
	@ResponseBody
	@ExceptionHandler(DateTimeParseException.class)
	public ResponseEntity<Response> dateTimeParseExceptionHandler(DateTimeParseException e) {
		logger.error(e.getClass().getName() + " : " + e.getLocalizedMessage());
		logger.error("check : ", e);
		return Response.buildBadResponse(1008, "Wrong Date Format, must be (\"yyyy-MM-dd\"). See : " + e.getLocalizedMessage());
	}
	
	
	
}
