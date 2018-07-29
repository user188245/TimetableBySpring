package com.user188245.timetable.model.core.exception;

import org.springframework.http.HttpStatus;

public abstract class CustomException extends Exception {

	/**
	 * 
	 */
	private static final long serialVersionUID = -475510413647702300L;
	
	public CustomException(String message) {
		super(message);
	}
	
	public abstract int getErrorCode();
	
	public abstract HttpStatus getStatus();

}
