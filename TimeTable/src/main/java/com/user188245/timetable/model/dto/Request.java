package com.user188245.timetable.model.dto;

public class Request<T> {
	
	private final int errorCode;
	
	private final String message;
	
	private final T data;

	public Request(T data) {
		this.data = data;
		this.errorCode = 0;
		this.message = null;
	}
	
	public Request(int errorCode, String message) {
		this.data = null;
		this.errorCode = errorCode;
		this.message = message;
	}

	public int getErrorCode() {
		return errorCode;
	}

	public String getMessage() {
		return message;
	}

	public T getData() {
		return data;
	}

}
