package com.user188245.timetable.model.core.exception;

import org.springframework.http.HttpStatus;

public class PasswordInvalidationException extends CustomException {

	/**
	 * 
	 */
	private static final long serialVersionUID = -6246333038112779888L;
	
	public static final int errorCode = 1001;

	public PasswordInvalidationException(String message) {
		super(message);
		// TODO Auto-generated constructor stub
	}

	@Override
	public int getErrorCode() {
		// TODO Auto-generated method stub
		return errorCode;
	}
	
	@Override
	public HttpStatus getStatus() {
		return HttpStatus.BAD_REQUEST;
	}

}
