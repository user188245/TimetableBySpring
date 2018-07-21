package com.user188245.timetable.model.dto;

import javax.persistence.Column;
import javax.persistence.Embeddable;

@Embeddable
public class ScheduleTime {

	@Column(columnDefinition="tinyint")
	private int startHour;
	
	@Column(columnDefinition="tinyint")
	private int startMinute;
	
	@Column(columnDefinition="tinyint")
	private int endHour;
	
	@Column(columnDefinition="tinyint")
	private int endMinute;
	
	public ScheduleTime() {}

	public ScheduleTime(int startHour, int startMinute, int endHour, int endMinute) {
		setTime(startHour, startMinute, endHour, endMinute);
	}
	
	public void setTime(int startHour, int startMinute, int endHour, int endMinute) {
		this.startHour = startHour;
		this.startMinute = startMinute;
		this.endHour = endHour;
		this.endMinute = endMinute;
	}

	public int getStartHour() {
		return startHour;
	}

	public int getStartMinute() {
		return startMinute;
	}

	public int getEndHour() {
		return endHour;
	}

	public int getEndMinute() {
		return endMinute;
	}

}
