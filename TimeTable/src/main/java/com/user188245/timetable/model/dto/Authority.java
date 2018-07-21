package com.user188245.timetable.model.dto;

import java.util.Set;
import java.util.EnumSet;

import org.springframework.security.core.GrantedAuthority;

public enum Authority implements GrantedAuthority{
	Lecture_READ,Lecture_CREATE,Lecture_DELETE,Lecture_UPDATE,
	Schedule_READ,Schedule_CREATE,Schedule_DELETE,Schedule_UPDATE,
	Calendar_READ,Calendar_CREATE,Calendar_DELETE,Calendar_UPDATE;

	@Override
	public String getAuthority() {
		return this.name();
	}
	
	public static Set<Authority> generateDefaultAuthoritySet(){
		return EnumSet.allOf(Authority.class);
	}

}
