package com.user188245.timetable.model.dto;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.MappedSuperclass;

@MappedSuperclass
abstract class BasicDTO implements Serializable{
	
	private static final long serialVersionUID = 1333072222490717778L;
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	protected Long id;
	
	public Long getId() {
		return this.id;
	}
	
}
