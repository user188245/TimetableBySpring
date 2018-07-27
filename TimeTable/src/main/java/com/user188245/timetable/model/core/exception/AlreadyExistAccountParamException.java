package com.user188245.timetable.model.core.exception;

public class AlreadyExistAccountParamException extends CustomException {

	/**
	 * 
	 */
	private static final long serialVersionUID = -458066926333017762L;
	
	public static final int errorCode = 1003;

	public AlreadyExistAccountParamException(String message) {
		super(message);
		// TODO Auto-generated constructor stub
	}

	@Override
	public int getErrorCode() {
		// TODO Auto-generated method stub
		return errorCode;
	}

}
