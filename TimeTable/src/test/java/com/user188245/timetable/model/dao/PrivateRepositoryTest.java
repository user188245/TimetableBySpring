package com.user188245.timetable.model.dao;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import com.user188245.timetable.model.dto.User;

public abstract class PrivateRepositoryTest {
	
	@Autowired
	UserRepository dao;
	
	public static final String username = "Tester000";
	
	private long id;
	
	@Before
	public void addUser() {
		User dto = User.build(username, "123456789", "abc@def.com", "hello");
		dto = dao.save(dto);
		id = dto.getId();
	}
	
	@Test
	public abstract void test();
	
	
	@After
	public void removeUser() {
		dao.deleteById(id);
	}

}
