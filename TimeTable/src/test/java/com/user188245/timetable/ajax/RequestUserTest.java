package com.user188245.timetable.ajax;


import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;

import static org.hamcrest.CoreMatchers.containsString;
import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.csrf;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;

import com.user188245.timetable.base.AbstractStandaloneTest;
import com.user188245.timetable.controller.ajax.RegistrationController;
import com.user188245.timetable.model.dto.request.RequestUser;



@RunWith(SpringRunner.class)
@SpringBootTest
public class RequestUserTest extends AbstractStandaloneTest<RegistrationController>{
	
	@Test
	public void signupValid() throws Exception {
		RequestUser user = new RequestUser("john","123456789","123456789","abc@def.com","Hello World!");
		
		String userJson = toJson(user);
		
		getMockMvc().perform(post("/signup")
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
		String userJson = toJson(user);
		
		getMockMvc().perform(post("/signup")
				.with(csrf().asHeader())
				.content(userJson)
				.contentType("application/json")
			)
			.andExpect(status().is4xxClientError())
			.andExpect(jsonPath("$.errorCode").value(1001));
	}
	
	@Test
	public void signupInvalid2() throws Exception {
		RequestUser user = new RequestUser("simon","123456789","123456789","malformed email","Hello World!");
		String userJson = toJson(user);
		
		getMockMvc().perform(post("/signup")
				.with(csrf().asHeader())
				.content(userJson)
				.contentType("application/json")
			)
			.andExpect(status().is4xxClientError())
			.andExpect(jsonPath("$.errorCode").value(1002));
	}
	
	@Test
	public void signupInvalid3() throws Exception {
		RequestUser user = new RequestUser("paul","123456789","123456789","paul@def.com","hello");
		String userJson = toJson(user);
		
		getMockMvc().perform(post("/signup")
				.with(csrf().asHeader())
				.content(userJson)
				.contentType("application/json")
			)
			.andExpect(status().isOk())
			.andExpect(jsonPath("$.errorCode").value(0));
		
		user = new RequestUser("paul","abcdabcd","abcdabcd","qwerty@email.com","It's Paul!");
		userJson = toJson(user);
		
		getMockMvc().perform(post("/signup")
				.with(csrf().asHeader())
				.content(userJson)
				.contentType("application/json")
			)
			.andExpect(status().is4xxClientError())
			.andExpect(jsonPath("$.errorCode").value(1003));
	}

}
