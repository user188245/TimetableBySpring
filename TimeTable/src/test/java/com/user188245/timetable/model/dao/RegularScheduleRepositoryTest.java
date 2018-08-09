package com.user188245.timetable.model.dao;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.junit4.SpringRunner;

import com.user188245.timetable.model.dto.IrregularSchedule;
import com.user188245.timetable.model.dto.RegularSchedule;
import com.user188245.timetable.model.dto.ScheduleTime;
import com.user188245.timetable.model.dto.Week;

@RunWith(SpringRunner.class)
@SpringBootTest
@TestPropertySource("classpath:test.properties")
public class RegularScheduleRepositoryTest {
	
	@Autowired
	RegularScheduleRepository dao;


	@Test
	public void test() {
		//Insertion
		RegularSchedule dto = new RegularSchedule("장소", new ScheduleTime(10,30,12,0), Week.Monday, false);
		dto = dao.save(dto);
		long id = dto.getId();
		
		//Selection
		RegularSchedule real = dao.findById(id).get();
		
		assertNotNull(real);
		assertEquals("장소", real.getLocation());
		assertEquals(Week.Monday, real.getWeek());
		
		//Deletion
		dao.deleteById(id);
	}

}
