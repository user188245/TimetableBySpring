package com.user188245.timetable.ajax;


import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.servlet.mvc.Controller;

import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.csrf;
import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.user;
import static org.springframework.security.test.web.servlet.setup.SecurityMockMvcConfigurers.springSecurity;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import com.fasterxml.jackson.databind.ObjectMapper;


@RunWith(SpringRunner.class)
@SpringBootTest
public abstract class RequestPrototype {

	private MockMvc mockMvc;
	
	@Autowired
	private WebApplicationContext context;
	
	protected String targetURI = "/";
	
	ObjectMapper objectMapper;

	@Before
	public void setUp() throws Exception {
		objectMapper = new ObjectMapper();
		mockMvc = MockMvcBuilders
				.webAppContextSetup(context)
				.apply(springSecurity())
				.build();
	}

	@Test
	public void getTest() throws Exception {
		mockMvc.perform(get(targetURI)
				.with(user("username"))
			)
			.andExpect(status().isOk());
	}
	
	@Test
	public void postTest() throws Exception {
		
		String json = null; // create own json sample
		
		mockMvc.perform(post(targetURI)
				.with(csrf().asHeader())
				.with(user("username"))
				.content(json)
				.contentType("application/json")
			)
			.andExpect(status().isOk())
			.andExpect(jsonPath("$.errorCode").value(0));
	}

}
