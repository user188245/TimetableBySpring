package com.user188245.timetable.model.dto;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;

@Entity
public final class RegularSchedule extends Schedule{

	private static final long serialVersionUID = -2653688209184006138L;

	@Enumerated(EnumType.ORDINAL)
	private Week week;
	
	@Column
	private boolean isCanceled;
	
	public RegularSchedule() {super();}

	public RegularSchedule(String location, ScheduleTime scheduleTime, Week week, boolean isCanceled) {
		super(scheduleTime, location);
		this.week = week;
		this.isCanceled = isCanceled;
	}

	public Week getWeek() {
		return week;
	}

	public void setWeek(Week week) {
		this.week = week;
	}

	public boolean isCanceled() {
		return isCanceled;
	}

	public void toggleCanceled() {
		if(this.isCanceled)
			this.isCanceled = false;
		else
			this.isCanceled = true;
	}

	
}
