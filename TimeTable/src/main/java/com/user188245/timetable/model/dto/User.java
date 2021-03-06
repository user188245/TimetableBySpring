package com.user188245.timetable.model.dto;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Index;
import javax.persistence.Table;
import javax.persistence.Transient;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.CredentialsContainer;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.factory.PasswordEncoderFactories;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.oauth2.core.oidc.OidcIdToken;
import org.springframework.security.oauth2.core.oidc.OidcUserInfo;
import org.springframework.security.oauth2.core.oidc.user.OidcUser;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.validation.annotation.Validated;

import com.user188245.timetable.model.core.util.AuthorityBitEncoder;

@Entity
@Validated
@Table(indexes= {@Index(columnList = "username"),@Index(columnList = "email")})
public class User extends BasicDTO implements UserDetails, CredentialsContainer, OAuth2User, OidcUser{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 4203662766121311417L;

	@Column(nullable = false, unique = true)
	@NotBlank
	private String username;
	
	@NotNull
	@Size(min=8)
	private String password;
	
	@Column(nullable = false)
	@Email
	private String email;
	
	@Size(max=120)
	@Column(length=1024)
	private String description;
	
	@Transient
	private static PasswordEncoder passwordEncoder;
	
	@Autowired
	@Transient
	private static AuthorityBitEncoder authorityBitEncoder;
	
	@Transient
	private Set<? extends GrantedAuthority> authorities;
	
	@Transient
	private OidcIdToken idToken;
	
	@Transient
	private OidcUserInfo userInfo;
	
	@Column(nullable = false)
	private long authorityFlag;
	
	public User() {}

	private User(String username, String password, String email, String description, Set<Authority> authorities) {
		this.username = username;
		this.password = passwordEncoder.encode(password);
		this.email = email;
		this.description = description;
		setAuthorities(authorities);
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
	
	public User setAuthorityFlag(int authorityFlag) {
		this.authorityFlag = authorityFlag;
		return this;
	}

	public void setAuthorities(Set<Authority> authorities) {
		this.authorities = authorities;
		authorityFlag = authorityBitEncoder.encode(authorities);
	}
	
	public User flagPatchToSet() {
		bitEncoderLoad();
		authorities = authorityBitEncoder.decode(authorityFlag);
		return this;
	}

	@Override
	public String getPassword() {
		// TODO Auto-generated method stub
		return password;
	}
	
	public void setUsername(String username) {
		this.username = username;
	}

	@Override
	public String getUsername() {
		// TODO Auto-generated method stub
		return getName();
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
	
	private static void passwordEncoderLoad() {
		if(User.passwordEncoder == null) {
			User.generatePasswordEncoder();
		}
	}
	
	private static void bitEncoderLoad() {
		if(authorityBitEncoder == null) {
			authorityBitEncoder = new AuthorityBitEncoder();
		}
	}
	
	public static User build(
			String username, 
			String password,
			String email, 
			String description){
		passwordEncoderLoad();
		bitEncoderLoad();
		return new User(username,password,email,description,Authority.generateDefaultAuthoritySet());
	}
	
	public static User buildDummy() {
		return new User();
	}
	@Override
	public String getName() {
		if(username != null)
			return username;
		else
			return "N/A";
	}

	@Override
	public Map<String, Object> getAttributes() {
		// TODO Auto-generated method stub
		Map<String,Object> attr = new HashMap<String,Object>(3);
		attr.put("name", username);
		attr.put("email", email);
		attr.put("description", description);
		return attr;
	}

	@Override
	public Map<String, Object> getClaims() {
		return this.userInfo.getClaims();
	}

	@Override
	public OidcUserInfo getUserInfo() {
		return this.userInfo;
	}

	@Override
	public OidcIdToken getIdToken() {
		return this.idToken;
	}
	
	public void setToken(OidcIdToken token) {
		if(token.getEmailVerified())
			this.email = token.getEmail();
		this.idToken = token;
	}
	
	public void generateUserInfo() {
		this.userInfo = new OidcUserInfo(getAttributes());
	}
	
	public boolean checkRegistrationRequired() {
		return this.idToken != null && this.username == null;
	}
	
	public void generatePassword() {
		this.password = "\1\1\1\1\1\1\1\1\1\1\1\1\1\1\1\1\1\1\1\1";
	}

}
