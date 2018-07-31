package com.user188245.timetable.model.dto.response;

import com.user188245.timetable.model.dto.IrregularSchedule;
import com.user188245.timetable.model.dto.Lecture;

public class WeeklyTimeTable {
	
	private final Lecture lecture;
	
	private final IrregularSchedule schedule;

	public WeeklyTimeTable(Lecture lecture, IrregularSchedule schedule) {
		this.lecture = lecture;
		this.schedule = schedule;
	}

	public Lecture getLecture() {
		return lecture;
	}

	public IrregularSchedule getSchedule() {
		return schedule;
	}
	
}
