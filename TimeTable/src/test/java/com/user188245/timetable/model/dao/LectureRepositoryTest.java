package com.user188245.timetable.model.dao;

import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.List;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.user188245.timetable.base.UserRequiredTestBase;
import com.user188245.timetable.model.core.exception.BadAccessException;
import com.user188245.timetable.model.dto.Lecture;
import com.user188245.timetable.model.dto.RegularSchedule;
import com.user188245.timetable.model.dto.ScheduleTime;
import com.user188245.timetable.model.dto.Week;

@RunWith(SpringRunner.class)
@SpringBootTest
public class LectureRepositoryTest extends UserRequiredTestBase{

	@Autowired
	LectureRepository dao;

	@Override
	public void test() {
		//Insertion
		List<RegularSchedule> schedule = new ArrayList<>();
		schedule.add(new RegularSchedule("제1 취침관 201호", new ScheduleTime(10,0,11,30), Week.Monday, false));
		schedule.add(new RegularSchedule("제3 숙면관 403호", new ScheduleTime(10,0,12,30), Week.Wednesday, false));
		Lecture dto = new Lecture("취침학개론","최드르렁","http://homepage.com",schedule);
		dto.setUsername(username);
		try {
			dto = dao.save(username,dto);
		} catch (BadAccessException e) {
			fail(e.getMessage());
		}
		long id = dto.getId();
		
		//Selection
		Lecture real = dao.findById(id).get();
		
		assertNotNull(real);
		assertEquals("취침학개론", real.getName());
		assertEquals(2, real.getScheduleList().size());
		assertEquals("제3 숙면관 403호", real.getScheduleList().get(1).getLocation());
		
		//Deletion
		dao.deleteById(id);
		
	}
}
