package com.user188245.timetable.controller.ajax;

import java.io.IOException;

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
import com.user188245.timetable.model.dao.UserRepository;
import com.user188245.timetable.model.dto.Request;
import com.user188245.timetable.model.dto.RequestUser;
import com.user188245.timetable.model.dto.User;

@Controller
public class RegistrationController {
	
	
	
	@Autowired
	private UserRepository userRepository;
	
	@SuppressWarnings("rawtypes")
	@PostMapping(value="/signup")
	@ResponseBody
	public ResponseEntity<Request> requestSignup(
			@Valid @RequestBody RequestUser data, 
			BindingResult bindingResult,
			HttpServletResponse response
	) throws IOException {
		if(!data.getPassword().equals(data.getPasswordValidation())) {
			return new ResponseEntity<Request>(
					new Request(1001,"PasswordInvalidationError: both \"Password\" and \"PasswordValidation\" must be equivalent each other"),
					HttpStatus.BAD_REQUEST
			);
		}else if(bindingResult.hasErrors()) {
			FieldError e = bindingResult.getFieldError();
			return new ResponseEntity<Request>(
					new Request(1002,"FieldConditionError: " + "[" +e.getField() + "] " + e.getDefaultMessage()),
					HttpStatus.BAD_REQUEST
			);
		}
		if(userRepository.checkUsernameDuplication(data.getUsername())) {
			new ResponseEntity<Request>(
					new Request(1003,"AlreadyExistAccountParam: " + "Username is already used."),
					HttpStatus.BAD_REQUEST
			);
		}else if(userRepository.checkEmailDuplication(data.getEmail())) {
			new ResponseEntity<Request>(
					new Request(1003,"AlreadyExistAccountParam: " + "Email is already used, Only Email can be use once"),
					HttpStatus.BAD_REQUEST
			);
		}else {
			User user = User.build(data.getUsername(), data.getPassword(), data.getEmail(), data.getDescription());
			userRepository.save(user);
		}
		return new ResponseEntity<Request>(HttpStatus.OK);
	}

}
