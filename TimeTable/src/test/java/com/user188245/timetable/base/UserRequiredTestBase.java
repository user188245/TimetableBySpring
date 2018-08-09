package com.user188245.timetable.base;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.TestPropertySource;

import com.user188245.timetable.model.dao.UserRepository;
import com.user188245.timetable.model.dto.User;

@TestPropertySource("classpath:test.properties")
public abstract class UserRequiredTestBase {
	
	@Autowired
	UserRepository dao;
	
	public static final String username = "Tester000";
	public static final String password = "123456789";
	
	private long id;
	
	@Before
	public void addUser() {
		User dto = User.build(username, password, "abc@def.com", "hello");
		dto = dao.save(dto);
		id = dto.getId();
	}
	
	@Test
	public abstract void test() throws Exception;
	
	@After
	public void removeUser() {
		dao.deleteById(id);
	}

}
