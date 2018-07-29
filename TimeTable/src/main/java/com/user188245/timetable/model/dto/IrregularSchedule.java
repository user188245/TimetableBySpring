package com.user188245.timetable.model.dto;


import java.time.LocalDate;

import javax.persistence.Convert;
import javax.persistence.Entity;

import com.user188245.timetable.model.core.LocalDateAttributeConverter;


@Entity
public class IrregularSchedule extends Schedule{

	private static final long serialVersionUID = -213239292855485763L;
	
	private String name;
	
	private String text;
	
	@Convert(converter = LocalDateAttributeConverter.class)
	private LocalDate date;
	
	public IrregularSchedule() {super();}
	
//	public IrregularSchedule(String name, String location, ScheduleTime scheduleTime, String text, LocalDate date) {
//		super(scheduleTime, location);
//		this.name = name;
//		this.text = text;
//		this.date = date;
//	}

	public IrregularSchedule(String name, String location, ScheduleTime scheduleTime,String text, String date) {
		super(scheduleTime, location);
		this.name = name;
		this.text = text;
		this.date = LocalDate.parse(date);
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
		this.date = LocalDate.parse(dateFormat);
	}
	
//	public void setDate(LocalDate date) {
//		this.date = date;
//	}
	

}
