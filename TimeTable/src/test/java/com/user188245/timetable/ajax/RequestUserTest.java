package com.user188245.timetable.ajax;


import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import static org.hamcrest.CoreMatchers.containsString;
import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.csrf;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.user188245.timetable.controller.ajax.RegistrationController;
import com.user188245.timetable.model.dto.RequestUser;



@RunWith(SpringRunner.class)
@SpringBootTest
@ContextConfiguration
public class RequestUserTest {
	
	private MockMvc mockMvc;
	
	@Autowired
	private RegistrationController registrationController;
	
	ObjectMapper objectMapper;

	@Before
	public void setUp() throws Exception {
		objectMapper = new ObjectMapper();
		mockMvc = MockMvcBuilders.standaloneSetup(registrationController).build();
	}

	@Test
	public void signupValid() throws Exception {
		RequestUser user = new RequestUser("john","123456789","123456789","abc@def.com","Hello World!");
		
		String userJson = objectMapper.writeValueAsString(user);
		
		mockMvc.perform(post("/signup")
				.with(csrf().asHeader())
				.contentType("application/json")
				.content(userJson)
			)
			.andExpect(status().isOk())
			.andExpect(content().string(containsString("No Error")));
	}
	
	@Test
	public void signupInvalid1() throws Exception {
		RequestUser user = new RequestUser("max7723","123456789","123456123456789","max@def.com","Hello World!");
		String userJson = objectMapper.writeValueAsString(user);
		
		mockMvc.perform(post("/signup")
				.with(csrf().asHeader())
				.content(userJson)
				.contentType("application/json")
			)
			.andExpect(status().is4xxClientError())
			.andExpect(content().string(containsString("1001")));
	}
	
	@Test
	public void signupInvalid2() throws Exception {
		RequestUser user = new RequestUser("simon","123456789","123456789","malformed email","Hello World!");
		String userJson = objectMapper.writeValueAsString(user);
		
		mockMvc.perform(post("/signup")
				.with(csrf().asHeader())
				.content(userJson)
				.contentType("application/json")
			)
			.andExpect(status().is4xxClientError())
			.andExpect(content().string(containsString("1002")));
	}
	
	@Test
	public void signupInvalid3() throws Exception {
		RequestUser user = new RequestUser("paul","123456789","123456789","paul@def.com","hello");
		String userJson = objectMapper.writeValueAsString(user);
		
		mockMvc.perform(post("/signup")
				.with(csrf().asHeader())
				.content(userJson)
				.contentType("application/json")
			)
			.andExpect(status().isOk());
		
		user = new RequestUser("paul","abcdabcd","abcdabcd","qwerty@email.com","It's Paul!");
		userJson = objectMapper.writeValueAsString(user);
		
		mockMvc.perform(post("/signup")
				.with(csrf().asHeader())
				.content(userJson)
				.contentType("application/json")
			)
			.andExpect(status().is4xxClientError())
			.andExpect(content().string(containsString("1003")));
	}

}
