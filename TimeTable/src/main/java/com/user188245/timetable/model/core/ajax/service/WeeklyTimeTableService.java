package com.user188245.timetable.model.core.ajax.service;

import java.sql.SQLException;
import java.time.LocalDate;
import java.util.HashSet;
import java.util.NoSuchElementException;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.user188245.timetable.model.core.exception.BadAccessException;
import com.user188245.timetable.model.dao.IrregularScheduleRepository;
import com.user188245.timetable.model.dao.LectureRepository;
import com.user188245.timetable.model.dao.RegularScheduleRepository;
import com.user188245.timetable.model.dto.IrregularSchedule;
import com.user188245.timetable.model.dto.Lecture;
import com.user188245.timetable.model.dto.RegularSchedule;
import com.user188245.timetable.model.dto.request.RequestWeeklyTimeTable;
import com.user188245.timetable.model.dto.response.DataResponse;
import com.user188245.timetable.model.dto.response.WeeklyTimeTable;

@Service
public class WeeklyTimeTableService implements SingleReadableService<WeeklyTimeTable,LocalDate>,
												UpdatableService<RequestWeeklyTimeTable,WeeklyTimeTable,Long>{

	@Autowired
	LectureRepository lectureRepository;
	
	@Autowired
	IrregularScheduleRepository scheduleRepository;
	
	@Autowired
	RegularScheduleRepository regularScheduleRepository;
	
	
	@Override
	public ResponseEntity<DataResponse<WeeklyTimeTable>> read(String username, LocalDate findBy)
			throws BadAccessException, NoSuchElementException, SQLException {
		// TODO Auto-generated method stub
		return DataResponse.build(buildWeeklyTimeTable(username,findBy));
	}

	@Override
	public ResponseEntity<DataResponse<Iterable<WeeklyTimeTable>>> readAll(String username) throws SQLException {
		// TODO Auto-generated method stub
		Set<WeeklyTimeTable> set = new HashSet<WeeklyTimeTable>();
		set.add(buildWeeklyTimeTable(username,null));
		return DataResponse.build(set);
	}

	@Override
	public void update(String username, RequestWeeklyTimeTable request)
			throws BadAccessException, NoSuchElementException, SQLException {
		// TODO Auto-generated method stub
		RegularSchedule regularSchedule = regularScheduleRepository.findById(request.getId()).get();
		if(request.isActivated() ^ regularSchedule.isCanceled() == true) {
			regularSchedule.toggleCanceled();
			regularScheduleRepository.save(regularSchedule);
		}
	}
	
	private WeeklyTimeTable buildWeeklyTimeTable(String username, LocalDate date) {
		Iterable<Lecture> lectures = lectureRepository.findAll();
		Iterable<IrregularSchedule> schedules;
		if(date != null) {
			int dayOfWeek = date.getDayOfWeek().getValue();
			schedules = scheduleRepository.findAllByUsernameAndDate(username, date.minusDays(dayOfWeek-1), date.plusDays(7-dayOfWeek));
		}else {
			schedules = scheduleRepository.findAllByUsername(username);
		}
		return new WeeklyTimeTable(lectures, schedules);
	}

	

}
