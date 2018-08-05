package com.user188245.timetable.model.core.security;

import java.util.Collection;
import java.util.EnumSet;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.mapping.GrantedAuthoritiesMapper;

import com.user188245.timetable.model.core.security.service.CustomOAuth2UserService;
import com.user188245.timetable.model.dto.Authority;
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
				//.userAuthoritiesMapper(userAuthoritiesMapper())
				.and()
		.and()
		//if 
		.exceptionHandling()
			.accessDeniedPage("/index");
	}

	private GrantedAuthoritiesMapper userAuthoritiesMapper() {
		return new GrantedAuthoritiesMapper() {
			@Override
			public Collection<? extends GrantedAuthority> mapAuthorities(
					Collection<? extends GrantedAuthority> authorities) {
				Set<Authority> auths = EnumSet.noneOf(Authority.class);
				authorities.forEach(x-> {
					if(x.getAuthority().equals("ROLE_USER")) {
						auths.add(Authority.ROLE_READ);
						auths.add(Authority.ROLE_WRITE);
					}else if(x.getAuthority().equals("ROLE_ADMIN")) {
						auths.add(Authority.ROLE_READ);
						auths.add(Authority.ROLE_WRITE);
						auths.add(Authority.ROLE_SUPER);
					}
				});
				return auths;
			}
		};
	}
	
}
