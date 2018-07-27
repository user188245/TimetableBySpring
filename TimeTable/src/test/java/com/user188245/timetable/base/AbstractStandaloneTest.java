package com.user188245.timetable.base;

import org.junit.Before;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

public class AbstractStandaloneTest<C> {
	
	private MockMvc mockMvc;
	
	@Autowired
	private C controller;
	
	ObjectMapper objectMapper;

	@Before
	public void setUp() throws Exception {
		objectMapper = new ObjectMapper();
		mockMvc = MockMvcBuilders.standaloneSetup(controller).build();
	}
	
	public String toJson(Object instance) throws JsonProcessingException {
		return objectMapper.writeValueAsString(instance);
	}
	
	public MockMvc getMockMvc() {
		return mockMvc;
	}
}
