package com.user188245.timetable.controller.ajax;

import java.io.IOException;
import java.security.Principal;
import java.sql.SQLException;

import javax.validation.Valid;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.oauth2.client.authentication.OAuth2AuthenticationToken;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.user188245.timetable.controller.MainController;
import com.user188245.timetable.model.core.exception.AlreadyExistAccountParamException;
import com.user188245.timetable.model.core.exception.CustomException;
import com.user188245.timetable.model.core.exception.FieldConditionException;
import com.user188245.timetable.model.core.exception.PasswordInvalidationException;
import com.user188245.timetable.model.core.security.service.CustomUserDetailsService;
import com.user188245.timetable.model.core.security.service.RegistrationService;
import com.user188245.timetable.model.dao.OAuthUserMappingRepository;
import com.user188245.timetable.model.dto.Authority;
import com.user188245.timetable.model.dto.User;
import com.user188245.timetable.model.dto.request.RequestUser;
import com.user188245.timetable.model.dto.response.Response;

@Controller
@RequestMapping("/signup")
public class RegistrationController {
	
	@Autowired
	private RegistrationService registrator;
	
	@Autowired
	private OAuthUserMappingRepository oauthRepository;
	
	private final Logger logger = LoggerFactory.getLogger(RegistrationController.class);
	
	@PostMapping("/add")
	@ResponseBody
	public ResponseEntity<? extends Response> requestSignup(
			@Valid @RequestBody RequestUser data, 
			BindingResult bindingResult
	) throws IOException, PasswordInvalidationException, FieldConditionException, AlreadyExistAccountParamException {
		if(!data.getPassword().equals(data.getPasswordValidation())) {
			throw new PasswordInvalidationException("both \"Password\" and \"PasswordValidation\" must be equivalent each other");
		}else if(bindingResult.hasErrors()) {
			FieldError e = bindingResult.getFieldError();
			throw new FieldConditionException("FieldConditionError: " + "[" +e.getField() + "] " + e.getDefaultMessage());
		}
		if(registrator.checkUsernameDuplication(data.getUsername())) {
			throw new AlreadyExistAccountParamException("Username is already used");
		}else {
			try {
				User user = User.build(data.getUsername(), data.getPassword(), data.getEmail(), data.getDescription());
				registrator.signup(user);
			}catch(SQLException e) {
				throw new AlreadyExistAccountParamException("Unknown Account error, maybe it is because of Database Problem");
			}
		}
		return Response.buildValidResponseEntity(HttpStatus.OK, "/login");
	}
	
	@PostMapping("/social/add")
	@ResponseBody
	public ResponseEntity<? extends Response> requestSocialSignup(
			@RequestParam String username, 
			Principal principal
	) throws IOException, PasswordInvalidationException, FieldConditionException, AlreadyExistAccountParamException {
		if(principal != null && principal instanceof OAuth2AuthenticationToken){
			OAuth2AuthenticationToken token = (OAuth2AuthenticationToken)principal;
			User user = (User)token.getPrincipal();
			if(username.length() < 4 && username.length() > 32) {
				throw new FieldConditionException("FieldConditionError: " + "length of username must be between 4 and 32");
			}
			user.setUsername(username);
			if(registrator.checkUsernameDuplication(username)) {
				throw new AlreadyExistAccountParamException("Username is already used");
			}else {
				try {
					user.setAuthorityFlag(3);
					user.generatePassword();
					oauthRepository.registUsername(user.getEmail(), username);
					registrator.signup(user);
				}catch(SQLException e) {
					throw new AlreadyExistAccountParamException("Unknown Account error, maybe it is because of Database Problem");
				}
			}
			token.setAuthenticated(false);
			return Response.buildValidResponseEntity(HttpStatus.OK, "/");
		}else
			return Response.buildBadResponse(1000, "Social Registration Failed");
	}
	
	@ResponseBody
	@ExceptionHandler(CustomException.class)
	public ResponseEntity<Response> customExceptionHandler(CustomException e){
		logger.error(e.getClass().getName() + " : " + e.getLocalizedMessage());
		return Response.buildBadResponse(
				e.getErrorCode(),
				e.getMessage()
				);
	}

}
