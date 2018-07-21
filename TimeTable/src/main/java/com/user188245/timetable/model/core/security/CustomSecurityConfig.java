package com.user188245.timetable.model.core.security;

import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

@EnableWebSecurity
public class CustomSecurityConfig extends WebSecurityConfigurerAdapter{

	@Override
	protected void configure(HttpSecurity http) throws Exception {
		http
		
		.authorizeRequests()
			//permit All person regardless authority.
			.antMatchers("/js/**", "/styles/**", "/login", "/signup").permitAll()
			//permit USER regardless authority
			.anyRequest().hasAuthority("ROLE_USER")
			.and()
		//if not authorized, forword to login page immediately
		.formLogin()
			
			.loginPage("/login").failureUrl("/login?error")
			.and()
		//logout
		.logout()
			.logoutUrl("/login?logout")
			.logoutSuccessUrl("/login")
			.invalidateHttpSession(true);
	}

	
	
}
