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
import com.user188245.timetable.model.dto.ScheduleTime;

@RunWith(SpringRunner.class)
@SpringBootTest
@TestPropertySource("classpath:test.properties")
public class IrregularScheduleRepositoryTest {
	
	@Autowired
	IrregularScheduleRepository dao;

	@Test
	public void test() {
		//Insertion
		IrregularSchedule dto = new IrregularSchedule("임시일정0", "장소0", new ScheduleTime(10,0,11,30), "메모0", "2017-12-01");
		dto = dao.save(dto);
		long id = dto.getId();
		
		//Selection
		IrregularSchedule real = dao.findById(id).get();
		
		assertNotNull(real);
		assertEquals("임시일정0", real.getName());
		assertEquals("2017-12-01", real.getDate());
		
		//Deletion
		dao.deleteById(id);
	}

}
