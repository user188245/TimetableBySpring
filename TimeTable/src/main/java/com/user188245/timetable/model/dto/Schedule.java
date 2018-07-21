package com.user188245.timetable.model.dto;

import javax.persistence.Column;
import javax.persistence.Embedded;
import javax.persistence.MappedSuperclass;

@MappedSuperclass
abstract class Schedule extends BasicDTO{

	private static final long serialVersionUID = 1366816777146753249L;

	@Column(nullable = false)
	@Embedded
	protected ScheduleTime scheduleTime;
	
	protected String location;
	
	public Schedule() {}

	public Schedule(ScheduleTime scheduleTime, String location) {
		this.scheduleTime = scheduleTime;
		this.location = location;
	}

	public ScheduleTime getScheduleTime() {
		return scheduleTime;
	}

	public void setScheduleTime(ScheduleTime scheduleTime) {
		this.scheduleTime = scheduleTime;
	}

	public String getLocation() {
		return location;
	}

	public void setLocation(String location) {
		this.location = location;
	}
	
	
	
}
