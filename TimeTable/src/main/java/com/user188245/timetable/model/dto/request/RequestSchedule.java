package com.user188245.timetable.model.dto.request;

import com.user188245.timetable.model.dto.IrregularSchedule;

public class RequestSchedule extends Request{
	
	private IrregularSchedule schedule;

	public IrregularSchedule getSchedule() {
		return schedule;
	}

	public void setSchedule(IrregularSchedule schedule) {
		this.schedule = schedule;
	}
	
	

}
