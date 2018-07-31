package com.user188245.timetable.ajax;

import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.csrf;
import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.user;
import static org.springframework.security.test.web.servlet.response.SecurityMockMvcResultMatchers.authenticated;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.patch;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.user188245.timetable.base.AbstractCrudTest;
import com.user188245.timetable.model.dto.IrregularSchedule;
import com.user188245.timetable.model.dto.ScheduleTime;
import com.user188245.timetable.model.dto.request.RequestSchedule;

@RunWith(SpringRunner.class)
@SpringBootTest
public class RequestScheduleTest extends AbstractCrudTest{
	
	protected String targetURI = "/ajax/calendar";
	
	private long id = 1;
	
	private static final String time0 = "2020-12-01";
	private static final String time1 = "2020-11-28";
	private static final String time2 = "2020-12-15";

	@Override
	public void createTest() throws Exception {
		IrregularSchedule schedule = createScheduleExample();
		RequestSchedule request = new RequestSchedule(schedule);
		String json = toJson(request);
		
		this.getMockMvc().perform(
				post(targetURI)
				.with(csrf().asHeader())
				.with(user(username).roles("READ","WRITE"))
				.contentType("application/json")
				.content(json)
		)
		.andExpect(authenticated().withUsername(username))
		.andExpect(jsonPath("$.errorCode").value(0))
		.andExpect(status().isOk())
		.andDo(print());
	}

	@Override
	public void readTest() throws Exception {
		this.getMockMvc().perform(
				get(targetURI)
				.with(csrf().asHeader())
				.with(user(username).roles("READ","WRITE"))
				.param("time", time2)
		)
		.andExpect(authenticated().withUsername(username))
		.andExpect(jsonPath("$.errorCode").value(0))
		.andExpect(status().isOk())
		.andExpect(jsonPath("$.data").isNotEmpty())
		.andExpect(jsonPath("$.data[0].id").exists())
		.andExpect(jsonPath("$.data[0].id").isNumber())
		.andExpect(jsonPath("$.data[0].location").value("장소"))
		.andDo(print());
	}

	@Override
	public void updateTest() throws Exception {
		// TODO Auto-generated method stub
		IrregularSchedule schedule = createScheduleExample();
		schedule.setId(id);
		schedule.setDate(time1);
		schedule.setLocation("변경된 장소");
		schedule.setName(null);
		RequestSchedule request = new RequestSchedule(schedule);
		String json = toJson(request);
		
		this.getMockMvc().perform(
				put(targetURI)
				.with(csrf().asHeader())
				.with(user(username).roles("READ","WRITE"))
				.contentType("application/json")
				.content(json)
		)
		.andExpect(authenticated().withUsername(username))
		.andExpect(jsonPath("$.errorCode").value(0))
		.andExpect(status().isOk())
		.andDo(print());
	}

	@Override
	public void deleteTest() throws Exception {
		// TODO Auto-generated method stub
		getMockMvc().perform(
				delete(targetURI)
				.with(csrf().asHeader())
				.with(user(username).roles("READ","WRITE"))
				.param("id", String.valueOf(id))
		)
		.andExpect(authenticated().withUsername(username))
		.andExpect(jsonPath("$.errorCode").value(0))
		.andExpect(status().isOk())
		.andDo(print());
	}
	
	private IrregularSchedule createScheduleExample() {
		return new IrregularSchedule("이름","장소",new ScheduleTime(12,0,14,0),"메모",time0);
	}
	
	

}
