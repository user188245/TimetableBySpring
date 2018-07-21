package com.user188245.timetable.model.dto;

import java.sql.Date;

import javax.persistence.Entity;


@Entity
public class IrregularSchedule extends Schedule{

	private static final long serialVersionUID = -213239292855485763L;
	
	private String name;
	
	private String text;
	
	private Date date;
	
	public IrregularSchedule() {super();}
	
	public IrregularSchedule(String name, String location, ScheduleTime scheduleTime, Date date, String text) {
		super(scheduleTime, location);
		this.name = name;
		this.text = text;
		this.date = date;
	}

	public IrregularSchedule(String name, String location, ScheduleTime scheduleTime, String date, String text) {
		super(scheduleTime, location);
		this.name = name;
		this.text = text;
		this.date = Date.valueOf(date);
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getText() {
		return text;
	}

	public void setText(String text) {
		this.text = text;
	}
	
	public String getDate() {
		return date.toString();
	}

	public void setDate(String dateFormat) {
		this.date = Date.valueOf(dateFormat);
	}
	
	public void setDate(Date date) {
		this.date = date;
	}
	

}
