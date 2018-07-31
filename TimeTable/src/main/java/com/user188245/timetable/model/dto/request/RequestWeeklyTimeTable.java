package com.user188245.timetable.model.dto.request;

public class RequestWeeklyTimeTable extends Request{
	
	// for regularSchedule
	private long id;
	
	private boolean activated;
	
	public RequestWeeklyTimeTable() {}

	public RequestWeeklyTimeTable(Long id, boolean activated) {
		this.id = id;
		this.activated = activated;
	}

	public Long getId() {
		return id;
	}

	public boolean isActivated() {
		return activated;
	}
	
	

}
