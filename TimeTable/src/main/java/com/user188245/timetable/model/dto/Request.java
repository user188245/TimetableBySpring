package com.user188245.timetable.model.dto;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

public class Request {
	
	private final int errorCode;
	
	private final String message;
	
	protected Request() {
		this.errorCode = 0;
		this.message = "No Error";
	}
	
	protected Request(int errorCode, String message) {
		this.errorCode = errorCode;
		this.message = message;
	}
	
	public static ResponseEntity<Request> buildBadRequestEntity(int errorCode, String message){
		return new ResponseEntity<Request>(
				new Request(errorCode,message),HttpStatus.BAD_REQUEST
		);
	}
	
	public static ResponseEntity<Request> buildGoodRequestEntity(){
		return new ResponseEntity<Request>(
				new Request(),HttpStatus.OK
		);
	}

	public int getErrorCode() {
		return errorCode;
	}

	public String getMessage() {
		return message;
	}

}
