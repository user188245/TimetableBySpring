package com.user188245.timetable.controller.ajax;

import java.io.IOException;
import java.net.URL;

import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;

import com.mysql.jdbc.exceptions.jdbc4.MySQLIntegrityConstraintViolationException;
import com.user188245.timetable.controller.exception.BadRequestException;
import com.user188245.timetable.model.core.security.RegistrationService;
import com.user188245.timetable.model.dao.UserRepository;
import com.user188245.timetable.model.dto.RequestUser;
import com.user188245.timetable.model.dto.Response;
import com.user188245.timetable.model.dto.User;

@Controller
public class RegistrationController {
	
	
	
	@Autowired
	private RegistrationService registrator;
	
	@PostMapping(value="/signup")
	@ResponseBody
	public ResponseEntity<Response> requestSignup(
			@Valid @RequestBody RequestUser data, 
			BindingResult bindingResult,
			HttpServletResponse response
	) throws IOException {
		if(!data.getPassword().equals(data.getPasswordValidation())) {
			return Response.buildBadResponse(
				1001,
				"PasswordInvalidationError: both \"Password\" and \"PasswordValidation\" must be equivalent each other"
			);
		}else if(bindingResult.hasErrors()) {
			FieldError e = bindingResult.getFieldError();
			return Response.buildBadResponse(
				1002,
				"FieldConditionError: " + "[" +e.getField() + "] " + e.getDefaultMessage()
			);
		}
		if(registrator.checkUsernameDuplication(data.getUsername())) {
			return Response.buildBadResponse(
				1003,"AlreadyExistAccountParam: " + "Username is already used."
			);
		}else if(registrator.checkEmailDuplication(data.getEmail())) {
			return Response.buildBadResponse(
				1003,"AlreadyExistAccountParam: " + "Email is already used, Only Email can be use once"
			);
		}else {
			try {
				User user = User.build(data.getUsername(), data.getPassword(), data.getEmail(), data.getDescription());
				registrator.signup(user);
			}catch(Exception e) {
				return Response.buildBadResponse(
					1003,
					"AlreadyExistAccountParam: " + "Unknown Account error, maybe it is because of Database Problem"
				);
			}
		}
		return Response.buildValidResponseEntity(HttpStatus.OK);
	}

}
