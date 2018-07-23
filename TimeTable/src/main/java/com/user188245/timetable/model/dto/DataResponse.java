package com.user188245.timetable.model.dto;

public class DataResponse<T> extends Response {
	
	private final T data;

	public DataResponse(T data) {
		super();
		this.data = data;
	}
	
	public T getData() {
		return data;
	}

}
