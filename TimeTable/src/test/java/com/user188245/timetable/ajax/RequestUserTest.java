package com.user188245.timetable.ajax;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.test.web.servlet.setup.StandaloneMockMvcBuilder;

import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.csrf;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.request;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.user188245.timetable.controller.ajax.RegistrationController;
import com.user188245.timetable.model.dto.RequestUser;



@RunWith(SpringRunner.class)
@SpringBootTest
public class RequestUserTest {
	
	private MockMvc mockMvc;
	private RegistrationController registrationController;
	
	ObjectMapper objectMapper;

	@Before
	public void setUp() throws Exception {
		objectMapper = new ObjectMapper();
		registrationController = new RegistrationController();
		mockMvc = MockMvcBuilders.standaloneSetup(registrationController).build();
	}

	@Test
	public void signup() throws Exception {
		RequestUser user = new RequestUser("john","12345","12345","abc@def.com","Hello World!");
		String userJson = objectMapper.writeValueAsString(user);
		
		mockMvc.perform(post("/signup")
			.with(csrf().asHeader())
			.param("data", userJson))
			.andExpect(status().isOk())
			.andExpect(request().attribute("data", "3"));
		
		
	}

}
