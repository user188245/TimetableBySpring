package com.user188245.timetable;

import org.springframework.security.web.context.AbstractSecurityWebApplicationInitializer;

import com.user188245.timetable.model.core.security.CustomSecurityConfig;

public class SecurityWebApplicationInitializer extends AbstractSecurityWebApplicationInitializer {
	
	public SecurityWebApplicationInitializer() {
        super(CustomSecurityConfig.class);
    }
	
}
