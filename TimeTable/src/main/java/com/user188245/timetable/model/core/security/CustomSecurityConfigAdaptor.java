package com.user188245.timetable.model.core.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

import com.user188245.timetable.model.core.security.service.CustomOAuth2UserService;
import com.user188245.timetable.model.dto.User;

@EnableWebSecurity
@EnableGlobalMethodSecurity(securedEnabled=true)
public class CustomSecurityConfigAdaptor extends WebSecurityConfigurerAdapter{
	
	@Autowired
	private CustomOAuth2UserService oAuth2UserService;

	@Override
	protected void configure(HttpSecurity http) throws Exception {
		http
		.authorizeRequests()
			//permit All person regardless authority.
			.antMatchers("/js/**", "/styles/**", "/login", "/signup", "/favicon.ico").permitAll()
			.antMatchers("/login/**", "/signup/**").permitAll()
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
		//OAuth2
		.oauth2Login()
			.loginPage("/login")
			.userInfoEndpoint()
				.oidcUserService(oAuth2UserService)
				.customUserType(User.class, "google")
				.and()
		.and()
		//if 
		.exceptionHandling()
			.accessDeniedPage("/index");
	}
	
}
