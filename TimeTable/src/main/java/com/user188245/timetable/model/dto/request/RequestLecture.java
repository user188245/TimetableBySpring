package com.user188245.timetable.model.dto.request;

import com.user188245.timetable.model.dto.Lecture;

public class RequestLecture extends Request{
	
	private Lecture lecture;

	public RequestLecture(Lecture lecture) {
		this.lecture = lecture;
	}

	public Lecture getLecture() {
		return lecture;
	}

}
