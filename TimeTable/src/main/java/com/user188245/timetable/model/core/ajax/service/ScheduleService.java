package com.user188245.timetable.model.core.ajax.service;

import java.sql.SQLException;
import java.time.LocalDate;
import java.util.NoSuchElementException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.user188245.timetable.model.core.exception.BadAccessException;
import com.user188245.timetable.model.dao.IrregularScheduleRepository;
import com.user188245.timetable.model.dto.IrregularSchedule;
import com.user188245.timetable.model.dto.Lecture;
import com.user188245.timetable.model.dto.ScheduleTime;
import com.user188245.timetable.model.dto.request.RequestLecture;
import com.user188245.timetable.model.dto.request.RequestSchedule;
import com.user188245.timetable.model.dto.response.DataResponse;

@Service
public class ScheduleService implements CreatableService<RequestSchedule,IrregularSchedule,Long>,
										MultiReadableService<IrregularSchedule,LocalDate>,
										UpdatableService<RequestSchedule,IrregularSchedule,Long>,
										DeletableService<RequestSchedule,IrregularSchedule,Long>{

	@Autowired
	IrregularScheduleRepository irregularScheduleRepository;

	@Override
	public ResponseEntity<DataResponse<Iterable<IrregularSchedule>>> readAll(String username) throws SQLException {
		// TODO Auto-generated method stub
		Iterable<IrregularSchedule> irregularSchedule = irregularScheduleRepository.findAllByUsername(username);
		return DataResponse.build(irregularSchedule);
	}
	
	@Override
	public ResponseEntity<DataResponse<Iterable<IrregularSchedule>>> read(String username, LocalDate date) {
		Iterable<IrregularSchedule> irregularSchedule = irregularScheduleRepository.findAllByUsernameAndDate(username, date.withDayOfMonth(1), date.withDayOfMonth(date.lengthOfMonth()));
		return DataResponse.build(irregularSchedule);
	}

	@Override
	public void create(String username, RequestSchedule request) throws BadAccessException, SQLException {
		// TODO Auto-generated method stub
		request.getSchedule().setUsername(username);
		irregularScheduleRepository.save(username, request.getSchedule());
	}

	@Override
	public void update(String username, RequestSchedule request)
			throws BadAccessException, NoSuchElementException, SQLException {
		IrregularSchedule target = request.getSchedule();
		IrregularSchedule schedule = irregularScheduleRepository.findById(target.getId()).get();
		if(target.getName() != null) {
			schedule.setName(target.getName());
		}
		if(target.getLocation() != null) {
			schedule.setLocation(target.getLocation());
		}
		if(target.getText() != null) {
			schedule.setText(target.getText());
		}
		if(target.getScheduleTime() != null) {
			ScheduleTime sc0 = schedule.getScheduleTime();
			ScheduleTime sc1 = target.getScheduleTime();
			sc0.setTime(sc1.getStartHour(), sc1.getStartMinute(), sc1.getEndHour(), sc1.getEndMinute());
		}
		irregularScheduleRepository.save(username, schedule);
	}

	@Override
	public void delete(String username, Long id) throws BadAccessException, NoSuchElementException, SQLException {
		irregularScheduleRepository.delete(username,id);
	}

}
