package com.user188245.timetable.model.dto.response;

import com.user188245.timetable.model.dto.IrregularSchedule;
import com.user188245.timetable.model.dto.Lecture;

public class WeeklyTimeTable {
	
	private final Iterable<Lecture> lectureList;
	
	private final Iterable<IrregularSchedule> exceptionalScheduleList;

	public WeeklyTimeTable(Iterable<Lecture> lectureList, Iterable<IrregularSchedule> exceptionalScheduleList) {
		this.lectureList = lectureList;
		this.exceptionalScheduleList = exceptionalScheduleList;
	}

	public Iterable<Lecture> getLectureList() {
		return lectureList;
	}

	public Iterable<IrregularSchedule> getExceptionalScheduleList() {
		return exceptionalScheduleList;
	}

}
