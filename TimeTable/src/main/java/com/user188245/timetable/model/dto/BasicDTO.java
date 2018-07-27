package com.user188245.timetable.model.dto;

import java.io.Serializable;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.MappedSuperclass;

@MappedSuperclass
public abstract class BasicDTO implements Serializable{
	
	private static final long serialVersionUID = 1333072222490717778L;
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	protected Long id;
	
	@JoinColumn(referencedColumnName = "username")
	protected String username;
	
	public void setUsername(String username) {
		this.username = username;
	}
	
	public void setId(Long id) {
		this.id = id;
	}
	
	public Long getId() {
		return this.id;
	}
	
	public String getUsername() {
		return this.username;
	}
	
}
