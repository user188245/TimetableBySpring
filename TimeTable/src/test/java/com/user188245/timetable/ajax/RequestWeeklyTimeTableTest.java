package com.user188245.timetable.ajax;

import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.csrf;
import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.user;
import static org.springframework.security.test.web.servlet.response.SecurityMockMvcResultMatchers.authenticated;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.ArrayList;
import java.util.List;

import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;

import com.jayway.jsonpath.JsonPath;
import com.user188245.timetable.base.AbstractCrudTest;
import com.user188245.timetable.model.dao.IrregularScheduleRepository;
import com.user188245.timetable.model.dao.LectureRepository;
import com.user188245.timetable.model.dto.IrregularSchedule;
import com.user188245.timetable.model.dto.Lecture;
import com.user188245.timetable.model.dto.RegularSchedule;
import com.user188245.timetable.model.dto.ScheduleTime;
import com.user188245.timetable.model.dto.Week;
import com.user188245.timetable.model.dto.request.RequestWeeklyTimeTable;

@RunWith(SpringRunner.class)
@SpringBootTest
@ContextConfiguration
public class RequestWeeklyTimeTableTest extends AbstractCrudTest{
	
	protected String targetURI = "/ajax/timetable";
	
	@Autowired
	LectureRepository dao1;
	
	@Autowired
	IrregularScheduleRepository dao2;
	
	private long id = 1;
	private long irrId = 2;
	private long rId = 3;
	
	private static final String time = "2020-12-03";

	@Override
	public void createTest() throws Exception {
		List<RegularSchedule> schedule = new ArrayList<>();
		schedule.add(new RegularSchedule("제1 취침관 201호", new ScheduleTime(10,0,11,30), Week.Monday, false));
		schedule.add(new RegularSchedule("제3 숙면관 403호", new ScheduleTime(10,0,12,30), Week.Wednesday, false));
		Lecture dto = new Lecture("취침학개론","최드르렁","http://homepage.com",schedule);
		dto.setUsername(username);
		dto = dao1.save(username,dto);
		IrregularSchedule dto2 = new IrregularSchedule("시체놀이 하는 날", "공원 쉼터", new ScheduleTime(10,0,11,30), "꼭 물마시고 오기, 시작 전 미리 화장실 다녀오기", "2020-12-05");
		dto2.setUsername(username);
		dto2 = dao2.save(dto2);
		
	}

	@Override
	public void readTest() throws Exception {
		// TODO Auto-generated method stub
		getMockMvc().perform(
				get(targetURI)
				.with(csrf().asHeader())
				.with(user(username).roles("READ","WRITE"))
				.param("date", time)
		)
		.andExpect(status().isOk())
		.andExpect(authenticated().withUsername(username))
		.andDo(print())
		.andExpect(jsonPath("$.errorCode").value(0))
		.andExpect(jsonPath("$.data.lectureList").isNotEmpty())
		.andExpect(jsonPath("$.data.lectureList[0].name").value("취침학개론"))
		.andExpect(jsonPath("$.data.exceptionalScheduleList").isNotEmpty())
		.andExpect(jsonPath("$.data.exceptionalScheduleList[0].location").value("공원 쉼터"))
		.andDo(result->{
			String response = result.getResponse().getContentAsString();
			id = Long.parseLong(JsonPath.parse(response).read("$.data.lectureList[0].id").toString());
			irrId = Long.parseLong(JsonPath.parse(response).read("$.data.exceptionalScheduleList[0].id").toString());
			rId = Long.parseLong(JsonPath.parse(response).read("$.data.lectureList[0].scheduleList[0].id").toString());
		});
		
		
	}

	@Override
	public void updateTest() throws Exception {
		RequestWeeklyTimeTable requestWeeklyTimeTable = new RequestWeeklyTimeTable(rId,false);
		String json = toJson(requestWeeklyTimeTable);
		
		getMockMvc().perform(
				put(targetURI)
				.with(csrf().asHeader())
				.with(user(username).roles("READ","WRITE"))
				.contentType(MediaType.APPLICATION_JSON)
				.content(json)
		)
		.andExpect(status().isOk())
		.andExpect(authenticated().withUsername(username))
		.andExpect(jsonPath("$.errorCode").value(0))
		.andDo(print());
	}

	@Override
	public void deleteTest() throws Exception {
		getMockMvc().perform(delete("/ajax/lecture")
				.with(csrf().asHeader())
				.with(user(username).roles("READ","WRITE"))
				.param("id", String.valueOf(id))
		)
		.andExpect(status().isOk())
		.andExpect(authenticated().withUsername(username))
		.andExpect(jsonPath("$.errorCode").value(0));
		
		getMockMvc().perform(delete("/ajax/calendar")
				.with(csrf().asHeader())
				.with(user(username).roles("READ","WRITE"))
				.param("id", String.valueOf(irrId))
		)
		.andExpect(status().isOk())
		.andExpect(authenticated().withUsername(username))
		.andExpect(jsonPath("$.errorCode").value(0));
		
	}
	
	
	
}
