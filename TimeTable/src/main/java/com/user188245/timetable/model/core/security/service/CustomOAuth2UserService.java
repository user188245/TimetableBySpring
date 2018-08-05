package com.user188245.timetable.model.core.security.service;

import java.util.NoSuchElementException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.oauth2.client.oidc.userinfo.OidcUserRequest;
import org.springframework.security.oauth2.client.userinfo.OAuth2UserService;
import org.springframework.security.oauth2.core.OAuth2AuthenticationException;
import org.springframework.security.oauth2.core.oidc.user.OidcUser;
import org.springframework.stereotype.Service;
import com.user188245.timetable.model.dao.OAuthUserMappingRepository;
import com.user188245.timetable.model.dao.UserRepository;
import com.user188245.timetable.model.dto.OAuthUserMapping;
import com.user188245.timetable.model.dto.User;

@Service
public class CustomOAuth2UserService implements OAuth2UserService<OidcUserRequest, OidcUser>{
	
	@Autowired
	OAuthUserMappingRepository oAuthUserMappingRepository;
	
	@Autowired
	UserRepository userRepository;
	
	@Override
	public OidcUser loadUser(OidcUserRequest userRequest) throws OAuth2AuthenticationException {
		// TODO Auto-generated method stub
		String id = userRequest.getIdToken().getEmail();
		String authServer = userRequest.getClientRegistration().getRegistrationId();
		User user;
		try {
			user = buildUser(id,authServer);
		}catch(NoSuchElementException e) {
			user = User.buildDummy();
		}
		user.setToken(userRequest.getIdToken());
		user.generateUserInfo();
		return user;
	}
	
	public User buildUser(String identifier, String authServer) throws NoSuchElementException {
		OAuthUserMapping mapping;
		try {
			mapping = oAuthUserMappingRepository.findByIdentifierAndAuthServer(identifier,authServer).get();
		}catch(NoSuchElementException e) {
			mapping = new OAuthUserMapping(null, identifier, authServer);
			oAuthUserMappingRepository.save(mapping);
		}
		return userRepository.findByUsername(mapping.getUsername()).get().flagPatchToSet();
		
	}
	

}
