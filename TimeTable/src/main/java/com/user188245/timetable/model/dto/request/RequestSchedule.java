package com.user188245.timetable.model.dto.request;

import com.user188245.timetable.model.dto.IrregularSchedule;

public class RequestSchedule extends Request{
	
	private IrregularSchedule schedule;
	
	public RequestSchedule() {}

	public RequestSchedule(IrregularSchedule schedule) {
		this.schedule = schedule;
	}

	public IrregularSchedule getSchedule() {
		return schedule;
	}
	
	

}
