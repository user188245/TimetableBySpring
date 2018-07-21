//package com.user188245.timetable.controller.exception;
//
//import org.springframework.web.bind.annotation.ControllerAdvice;
//import org.springframework.web.bind.annotation.ExceptionHandler;
//
//import com.user188245.timetable.model.dto.Request;
//
//@ControllerAdvice
//public class GlobalControllerExceptionAdviser {
//	
//	@SuppressWarnings("rawtypes")
//	@ExceptionHandler(BadRequestException.class)
//	public Request errorHandler(BadRequestException e){
//		System.out.println("NotPassed");
//		return new Request(e.getErrorCode(),e.getMessage());
//	}
//
//}
