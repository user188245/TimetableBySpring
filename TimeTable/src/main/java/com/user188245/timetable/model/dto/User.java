package com.user188245.timetable.model.dto;

import java.util.Collection;
import java.util.EnumSet;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.Index;
import javax.persistence.Table;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Email;
import javax.validation.constraints.Size;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.CredentialsContainer;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.factory.PasswordEncoderFactories;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.validation.annotation.Validated;

@Entity
@Validated
@Table(indexes= {@Index(columnList = "username"),@Index(columnList = "email")})
public class User extends BasicDTO implements UserDetails, CredentialsContainer{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 4203662766121311417L;

	@Column(nullable = false, unique = true)
	@NotBlank
	private String username;
	
	@Column(nullable = false)
	@NotBlank
	@NotNull
	@Size(min=8)
	private String password;
	
	@Column(nullable = false, unique = true)
	@Email
	private String email;
	
	@Size(max=120)
	private String description;
	
	@Autowired
	private static PasswordEncoder passwordEncoder;
	
	@Enumerated(EnumType.ORDINAL)
	@ElementCollection(targetClass=Authority.class, fetch=FetchType.EAGER)
	private Set<Authority> authorities;
	
	public User() {}

	private User(String username, String password, String email, String description, Set<Authority> authorities) {
		this.username = username;
		this.password = passwordEncoder.encode(password);
		this.email = email;
		this.description = description;
		this.authorities = authorities;
	}
	
	public static PasswordEncoder getPasswordEncoder() {
		return User.passwordEncoder;
	}
	
	public static void generatePasswordEncoder() {
		User.passwordEncoder = PasswordEncoderFactories.createDelegatingPasswordEncoder();
	}

	@Override
	public void eraseCredentials() {
		password = null;
	}

	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
		// TODO Auto-generated method stub
		return authorities;
	}

	@Override
	public String getPassword() {
		// TODO Auto-generated method stub
		return password;
	}

	@Override
	public String getUsername() {
		// TODO Auto-generated method stub
		return username;
	}
	
	public String getEmail() {
		return email;
	}
	
	public String getDescription() {
		return description;
	}

	@Override
	public boolean isAccountNonExpired() {
		// TODO Auto-generated method stub
		return true;
	}

	@Override
	public boolean isAccountNonLocked() {
		// TODO Auto-generated method stub
		return true;
	}

	@Override
	public boolean isCredentialsNonExpired() {
		// TODO Auto-generated method stub
		return true;
	}

	@Override
	public boolean isEnabled() {
		return true;
	}
	
	public static User build(
			String username, 
			String password,
			String email, 
			String description){
		if(User.passwordEncoder == null) {
			User.generatePasswordEncoder();
		}
		return new User(username,password,email,description,Authority.generateDefaultAuthoritySet());
	}

}
