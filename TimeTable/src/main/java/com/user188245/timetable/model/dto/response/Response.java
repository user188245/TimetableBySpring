package com.user188245.timetable.model.dto.response;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

public class Response {
	
	private final int errorCode;
	
	private final String message;
	
	private final String redirect;
	
	protected Response(String redirect) {
		this(0,"No Error",redirect);
	}
	
	protected Response() {
		this(null);
	}
	
	protected Response(int errorCode, String message, String redirect) {
		this.errorCode = errorCode;
		this.message = message;
		this.redirect = redirect;
	}
	
	protected Response(int errorCode, String message) {
		this(errorCode, message, null);
	}
	
	public static ResponseEntity<Response> buildInvalidResponseEntity(int errorCode, String message, String redirect, HttpStatus httpStatus){
		return new ResponseEntity<Response>(
				new Response(errorCode,message),httpStatus
		);
	}
	
	public static ResponseEntity<Response> buildBadResponse(int errorCode, String message){
		return buildInvalidResponseEntity(errorCode, message, null, HttpStatus.BAD_REQUEST);
	}
	
	public static ResponseEntity<Response> buildBadResponse(int errorCode, String message, String redirect){
		return buildInvalidResponseEntity(errorCode, message, redirect, HttpStatus.BAD_REQUEST);
	}
	
	public static ResponseEntity<Response> buildValidResponseEntity(HttpStatus httpStatus, String redirect){
		return new ResponseEntity<Response>(
				new Response(redirect),httpStatus
		);
	}
	
	public static ResponseEntity<Response> buildOK(){
		return buildValidResponseEntity(HttpStatus.OK, null);
	}
	
	public static ResponseEntity<Response> buildOKAndRedirect(String redirect){
		return buildValidResponseEntity(HttpStatus.OK, redirect);
	}


	public int getErrorCode() {
		return errorCode;
	}

	public String getMessage() {
		return message;
	}
	
	public String getRedirect() {
		return redirect;
	}

}
