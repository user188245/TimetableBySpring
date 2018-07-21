package com.user188245.timetable.model.dto;

public enum Month {
	January, February, March, April, May, June, July, August, September, October, November, December;

	public static Month of(int number) {
		return Month.values()[number];
	}
}
