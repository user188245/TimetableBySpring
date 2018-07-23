package com.user188245.timetable.model.dto;

import java.util.EnumSet;

import org.springframework.security.core.GrantedAuthority;


public enum Authority implements GrantedAuthority{
	READ,WRITE,SUPER;

	@Override
	public String getAuthority() {
		return this.name();
	}
	
	public static EnumSet<Authority> generateDefaultAuthoritySet(){
		return EnumSet.of(READ,WRITE);
	}

}
