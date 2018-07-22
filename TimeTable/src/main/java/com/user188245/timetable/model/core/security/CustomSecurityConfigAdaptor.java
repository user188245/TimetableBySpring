package com.user188245.timetable.model.core.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;

import com.user188245.timetable.model.dto.User;

@EnableWebSecurity
public class CustomSecurityConfigAdaptor extends WebSecurityConfigurerAdapter{
	
	@Autowired
	private UserDetailsService userDetailsService;

	@Override
	protected void configure(HttpSecurity http) throws Exception {
		http
		.authorizeRequests()
			//permit All person regardless authority.
			.antMatchers("/js/**", "/styles/**", "/login", "/signup").permitAll()
			//permit USER regardless authority
			.anyRequest().hasAuthority("ROLE_USER")
			.antMatchers("/login", "/signup").not().hasAuthority("ROLE_USER")
			.and()
		//if not authorized, forword to login page immediately
		.formLogin()
			.loginPage("/login")
			.successForwardUrl("/")
			.failureUrl("/login?error")
			.and()
		//logout
		.logout()
			.logoutUrl("/login?logout")
			.logoutSuccessUrl("/login")
			.invalidateHttpSession(true)
			.and()
		//if 
		.exceptionHandling().accessDeniedPage("/index");
	}
	
}