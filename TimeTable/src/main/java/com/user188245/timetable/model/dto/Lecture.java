package com.user188245.timetable.model.dto;

import java.util.List;

import javax.persistence.MappedSuperclass;
import javax.persistence.OneToMany;

@MappedSuperclass
public class Lecture extends BasicDTO{
	
	private static final long serialVersionUID = 4931030828875208874L;

	private String name;
	
	private String instructor;
	
	private String homepage;
	
	@OneToMany
	private List<RegularSchedule> scheduleList;

}
