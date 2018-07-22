package com.user188245.timetable.model.dto;

import java.util.EnumSet;

import org.springframework.security.core.GrantedAuthority;

public enum Authority implements GrantedAuthority{
	ROLE_USER, ROLE_READONLY_USER;

	@Override
	public String getAuthority() {
		return this.name();
	}
	
	public static EnumSet<Authority> generateDefaultAuthoritySet(){
		return EnumSet.allOf(Authority.class);
	}

}
