package com.user188245.timetable.model.dao;

import static org.junit.Assert.*;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.user188245.timetable.model.dto.IrregularSchedule;
import com.user188245.timetable.model.dto.ScheduleTime;
import com.user188245.timetable.model.dto.User;

import static org.junit.Assert.assertEquals;

@RunWith(SpringRunner.class)
@SpringBootTest
public class MainDAOTests {
	
	@Autowired
	IrregularScheduleRepository isrdao;
	
	@Autowired
	UserRepository userdao;
	
	@Test
	public void testIrregularSchedule() {
		//Insertion
		IrregularSchedule dto = new IrregularSchedule("임시일정0", "장소0", new ScheduleTime(10,0,11,30), "2017-12-30", "메모0");
		dto = isrdao.save(dto);
		long id = dto.getId();
		
		//Selection
		IrregularSchedule real = isrdao.findById(id).get();
		
		assertNotNull(real);
		assertEquals("임시일정0", real.getName());
		assertEquals("2017-12-30", real.getDate());
		
		//Deletion
		isrdao.deleteById(id);
	}
	
	@Test
	public void testUserSchedule() {
		//Insertion
		User dto = User.build("superman", "123456789", "abc@def.com", "hello");
		dto = userdao.save(dto);
		long id = dto.getId();
		
		//Selection
		User real = userdao.findByUsername("superman").get();
		
		assertNotNull(real);
		assertEquals("superman", real.getUsername());
		assertEquals("abc@def.com", real.getEmail());
		assertFalse(real.getAuthorities().isEmpty());
		
		
		//Deletion
		userdao.deleteById(id);
	}
}
