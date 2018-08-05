package com.user188245.timetable.model.dto;

import javax.persistence.Entity;
import javax.persistence.Index;
import javax.persistence.Table;

@Entity
@Table(indexes= {@Index(columnList = "username")})
public class OAuthUserMapping extends BasicDTO{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 271632441275504578L;

	private final String identifier;
	
	private final String authServer;

	public OAuthUserMapping(String username, String identifier, String authServer) {
		this.username = username;
		this.identifier = identifier;
		this.authServer = authServer;
	}

	public String getIdentifier() {
		return identifier;
	}

	public String getAuthServer() {
		return authServer;
	}
	
}
