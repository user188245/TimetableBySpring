package com.user188245.timetable.model.dao;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.junit4.SpringRunner;

import com.user188245.timetable.model.dao.UserRepository;
import com.user188245.timetable.model.dto.IrregularSchedule;
import com.user188245.timetable.model.dto.ScheduleTime;
import com.user188245.timetable.model.dto.User;

@RunWith(SpringRunner.class)
@SpringBootTest
@TestPropertySource("classpath:test.properties")
public class UserRepositoryTest {

	@Autowired
	UserRepository dao;
	
	@Test
	public void test() {
		//Insertion
		User dto = User.build("superman", "123456789", "abc@def.com", "hello");
		dto = dao.save(dto);
		long id = dto.getId();
		
		//Selection
		User real = dao.findByUsername("superman").get();
		
		assertNotNull(real);
		assertEquals("superman", real.getUsername());
		assertEquals("abc@def.com", real.getEmail());
		assertFalse(real.flagPatchToSet().getAuthorities().isEmpty());
		
		
		//Deletion
		dao.deleteById(id);
	}

}
