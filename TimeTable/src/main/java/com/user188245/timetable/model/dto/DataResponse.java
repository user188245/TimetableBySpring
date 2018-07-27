package com.user188245.timetable.model.dto;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

public class DataResponse<T> extends Response {
	
	private final T data;

	private DataResponse(T data) {
		this.data = data;
	}
	
	public T getData() {
		return data;
	}
	
	public static <S> ResponseEntity<DataResponse<S>> build(S data){
		return new ResponseEntity<DataResponse<S>>(
				new DataResponse<S>(data),HttpStatus.OK
		);
	}
	
	

}
