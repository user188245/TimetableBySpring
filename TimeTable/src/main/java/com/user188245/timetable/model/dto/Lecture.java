package com.user188245.timetable.model.dto;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;

@Entity
public class Lecture extends BasicDTO{
	
	private static final long serialVersionUID = 4931030828875208874L;

	private String name;
	
	private String instructor;
	
	private String homepage;
	
	@OneToMany(cascade = CascadeType.ALL, fetch=FetchType.EAGER)
	@JoinColumn(name = "username")
	private List<RegularSchedule> scheduleList;

	public Lecture() {}
	
	public Lecture(String name, String instructor, String homepage, List<RegularSchedule> scheduleList) {
		this.name = name;
		this.instructor = instructor;
		this.homepage = homepage;
		this.scheduleList = scheduleList;
	}
	
	public String getName() {
		return name;
	}

	public String getInstructor() {
		return instructor;
	}

	public String getHomepage() {
		return homepage;
	}

	public List<RegularSchedule> getScheduleList() {
		return scheduleList;
	}

	
}
