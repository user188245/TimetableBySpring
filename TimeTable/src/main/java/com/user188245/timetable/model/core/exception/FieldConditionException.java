package com.user188245.timetable.model.core.exception;

public class FieldConditionException extends CustomException {

	/**
	 * 
	 */
	private static final long serialVersionUID = -7411691559756646951L;
	
	public static final int errorCode = 1002;

	public FieldConditionException(String message) {
		super(message);
		// TODO Auto-generated constructor stub
	}

	@Override
	public int getErrorCode() {
		// TODO Auto-generated method stub
		return errorCode;
	}

}
