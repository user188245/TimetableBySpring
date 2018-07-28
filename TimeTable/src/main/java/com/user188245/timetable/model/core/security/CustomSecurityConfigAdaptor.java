package com.user188245.timetable.model.core.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;

@EnableWebSecurity
public class CustomSecurityConfigAdaptor extends WebSecurityConfigurerAdapter{
	
	@Autowired
	private UserDetailsService userDetailsService;

	@Override
	protected void configure(HttpSecurity http) throws Exception {
		http
		.authorizeRequests()
			//permit All person regardless authority.
			.antMatchers("/js/**", "/styles/**", "/login", "/signup", "/favicon.png").permitAll()
			//permit USER regardless authority
			.anyRequest().hasAuthority("ROLE_READ")
			.and()
		//if not authorized, forword to login page immediately
		.formLogin()
			.loginPage("/login")
			.defaultSuccessUrl("/")
			.failureUrl("/login?error")
			.and()
		//logout
		.logout()
			.logoutUrl("/logout")
			.logoutSuccessUrl("/login?logout")
			.invalidateHttpSession(true)
			.and()
		//if 
		.exceptionHandling().accessDeniedPage("/index");
	}
	
}
