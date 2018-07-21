package com.user188245.timetable.model.dto;

public enum Week {
	Monday, Tuesday, Wednesday, Thursday, Friday, Saturday, Sunday;
	
	public static Week of(int number) {
		return Week.values()[number];
	}
	
}
