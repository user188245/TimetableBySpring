package com.user188245.timetable.model.core.exception;

public class BadAccessException extends CustomException {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 3087885617712729337L;

	public static final int errorCode = 1005;

	public BadAccessException(String message) {
		super(message);
	}

	@Override
	public int getErrorCode() {
		// TODO Auto-generated method stub
		return errorCode;
	}

}
