package com.user188245.timetable.model.core.security.service;

import java.util.NoSuchElementException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.oauth2.client.userinfo.OAuth2UserRequest;
import org.springframework.security.oauth2.client.userinfo.OAuth2UserService;
import org.springframework.security.oauth2.core.OAuth2AuthenticationException;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.stereotype.Service;

import com.user188245.timetable.model.dao.OAuthUserMappingRepository;
import com.user188245.timetable.model.dao.UserRepository;
import com.user188245.timetable.model.dto.OAuthUserMapping;
import com.user188245.timetable.model.dto.User;

@Service
public class CustomOAuth2UserService implements OAuth2UserService<OAuth2UserRequest,OAuth2User>{
	
	@Autowired
	OAuthUserMappingRepository oAuthUserMappingRepository;
	
	@Autowired
	UserRepository userRepository;

	@Override
	public OAuth2User loadUser(OAuth2UserRequest userRequest) throws OAuth2AuthenticationException, NoSuchElementException {
		// TODO Auto-generated method stub
		String id = userRequest.getClientRegistration().getClientId();
		String authServer = userRequest.getClientRegistration().getRegistrationId();
		return buildUser(id,authServer);
	}
	
	public User buildUser(String identifier, String authServer) throws NoSuchElementException {
		OAuthUserMapping mapping;
		try {
			mapping = oAuthUserMappingRepository.findByIdentifierAndAuthServer(identifier,authServer).get();
		}catch(NoSuchElementException e) {
			mapping = new OAuthUserMapping(null, identifier, authServer);
			oAuthUserMappingRepository.save(mapping);
		}
		return userRepository.findByUsername(mapping.getUsername()).get();
	}

}
