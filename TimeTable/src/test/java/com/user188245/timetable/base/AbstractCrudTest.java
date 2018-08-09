package com.user188245.timetable.base;

import javax.servlet.Filter;

import org.junit.Before;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockHttpServletRequestBuilder;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

@TestPropertySource("classpath:test.properties")
public abstract class AbstractCrudTest{
	
	private MockMvc mockMvc;
	
	protected static final String username = "TestUsername";
	
	@Autowired
	protected WebApplicationContext context;
		
	@Autowired
	private Filter springSecurityFilterChain;
	
	private ObjectMapper objectMapper;
	
	@Before
	public void setUp() throws Exception {
		mockMvc = MockMvcBuilders
				.webAppContextSetup(context)
				.addFilter(springSecurityFilterChain)
				.build();
		setObjectMapper();
	}
	
	public void setObjectMapper() {
		objectMapper = new ObjectMapper();
	}

	@Test
	public void test() throws Exception {
		createTest();
		readTest();
		updateTest();
		deleteTest();
	}
	
	public abstract void createTest() throws Exception;
	
	public abstract void readTest() throws Exception;
	
	public abstract void updateTest() throws Exception;
	
	public abstract void deleteTest() throws Exception;
	
	public String toJson(Object instance) throws JsonProcessingException {
		return objectMapper.writeValueAsString(instance);
	}
	
	public MockMvc getMockMvc() {
		return mockMvc;
	}
	
	protected static MockHttpServletRequestBuilder post(String targetURI) {
		return org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post(targetURI + "/add");
	}
	
	protected static MockHttpServletRequestBuilder get(String targetURI) {
		return org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get(targetURI + "/get");
	}
	
	protected static MockHttpServletRequestBuilder put(String targetURI) {
		return org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post(targetURI + "/update");
	}
	
	protected static MockHttpServletRequestBuilder patch(String targetURI) {
		return org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post(targetURI + "/update");
	}
	
	protected static MockHttpServletRequestBuilder delete(String targetURI) {
		return org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post(targetURI + "/remove");
	}
	
	

}
