package com.user188245.timetable.model.dto;

import java.util.Collections;
import java.util.EnumSet;
import java.util.Set;

import org.springframework.security.core.GrantedAuthority;


public enum Authority implements GrantedAuthority{
	READ,WRITE,SUPER;
	
	public final static long numberOfItem = Authority.values().length;

	@Override
	public String getAuthority() {
		return this.name();
	}
	
	public static Set<Authority> generateDefaultAuthoritySet(){
		return Collections.synchronizedSet(EnumSet.of(READ,WRITE));
	}

}
