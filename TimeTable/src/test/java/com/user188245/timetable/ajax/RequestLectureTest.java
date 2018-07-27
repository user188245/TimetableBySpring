package com.user188245.timetable.ajax;

import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.csrf;
import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.user;
import static org.springframework.security.test.web.servlet.response.SecurityMockMvcResultMatchers.authenticated;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.patch;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.ArrayList;
import java.util.List;

import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;

import com.user188245.timetable.base.AbstractCrudTest;
import com.user188245.timetable.model.dto.Lecture;
import com.user188245.timetable.model.dto.RegularSchedule;
import com.user188245.timetable.model.dto.RequestLecture;
import com.user188245.timetable.model.dto.ScheduleTime;
import com.user188245.timetable.model.dto.Week;

@RunWith(SpringRunner.class)
@SpringBootTest
@ContextConfiguration
public class RequestLectureTest extends AbstractCrudTest{
	
	protected String targetURI = "/ajax/lecture";
	
	private long id = 1;

	@Override
	public void createTest() throws Exception {
		Lecture l = createLectureExample();
		l.setId(id);
		RequestLecture lecture = new RequestLecture(l);
		String json = toJson(lecture);
		
		getMockMvc().perform(post(targetURI)
				.with(csrf().asHeader())
				.with(user(username).roles("READ","WRITE"))
				.contentType("application/json")
				.content(json)
		)
		.andExpect(authenticated().withUsername(username))
		.andExpect(jsonPath("$.errorCode").value(0))
		.andExpect(status().isOk());
	}
	
	@Override
	public void readTest() throws Exception {
		getMockMvc().perform(get(targetURI)
				.with(csrf().asHeader())
				.with(user(username).roles("READ","WRITE"))
		)
		.andExpect(status().isOk())
		.andExpect(authenticated().withUsername(username))
		.andExpect(jsonPath("$.errorCode").value(0))
		.andExpect(jsonPath("$.data[0].name").value("취침학개론"));
	}

	@Override
	public void updateTest() throws Exception {
		Lecture l = createLectureExample();
		l.setId(id);
		l.setName("취침학원론");
		l.setInstructor("최아무개");
		l.getScheduleList().get(1).setLocation("쾌몽관 1층 숙면홀");
		RequestLecture lecture = new RequestLecture(l);
		String json = toJson(lecture);
		
		getMockMvc().perform(patch(targetURI)
				.with(csrf().asHeader())
				.with(user(username).roles("READ","WRITE"))
				.contentType("application/json")
				.content(json)
		)
		.andExpect(status().isOk())
		.andExpect(authenticated().withUsername(username))
		.andExpect(jsonPath("$.errorCode").value(0));
	}

	@Override
	public void deleteTest() throws Exception {
		getMockMvc().perform(delete(targetURI)
				.with(csrf().asHeader())
				.with(user(username).roles("READ","WRITE"))
				.param("id", "1")
		)
		.andExpect(status().isOk())
		.andExpect(authenticated().withUsername(username))
		.andExpect(jsonPath("$.errorCode").value(0));
	}

	
	private Lecture createLectureExample() {
		List<RegularSchedule> schedule = new ArrayList<>();
		schedule.add(new RegularSchedule("제1 취침관 201호", new ScheduleTime(10,0,11,30), Week.Monday, false));
		schedule.add(new RegularSchedule("제3 숙면관 403호", new ScheduleTime(10,0,12,30), Week.Wednesday, false));
		return new Lecture("취침학개론","최드르렁","http://homepage.com",schedule);
	}


	

}
