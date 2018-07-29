package com.user188245.timetable.controller.ajax;

import java.io.IOException;
import java.sql.SQLException;

import javax.validation.Valid;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;

import com.user188245.timetable.controller.MainController;
import com.user188245.timetable.model.core.exception.AlreadyExistAccountParamException;
import com.user188245.timetable.model.core.exception.CustomException;
import com.user188245.timetable.model.core.exception.FieldConditionException;
import com.user188245.timetable.model.core.exception.PasswordInvalidationException;
import com.user188245.timetable.model.core.security.service.RegistrationService;
import com.user188245.timetable.model.dto.User;
import com.user188245.timetable.model.dto.request.RequestUser;
import com.user188245.timetable.model.dto.response.Response;

@Controller
public class RegistrationController {
	
	@Autowired
	private RegistrationService registrator;
	
	private final Logger logger = LoggerFactory.getLogger(RegistrationController.class);
	
	@PostMapping(value="/signup")
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
		}else if(registrator.checkEmailDuplication(data.getEmail())) {
			throw new AlreadyExistAccountParamException("Email is already used, Only Email can be use once");
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
