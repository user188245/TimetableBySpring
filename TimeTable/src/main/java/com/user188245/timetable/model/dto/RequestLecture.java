package com.user188245.timetable.model.dto;

public class RequestLecture extends Request{
	
	private Lecture lecture;
	
	public RequestLecture() {}

	public RequestLecture(Lecture lecture) {
		this.lecture = lecture;
	}

	public Lecture getLecture() {
		return lecture;
	}

}
