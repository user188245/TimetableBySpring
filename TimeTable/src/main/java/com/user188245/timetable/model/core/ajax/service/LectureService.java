package com.user188245.timetable.model.core.ajax.service;

import java.sql.SQLException;
import java.util.NoSuchElementException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestParam;

import com.user188245.timetable.model.core.exception.BadAccessException;
import com.user188245.timetable.model.dao.LectureRepository;
import com.user188245.timetable.model.dao.RegularScheduleRepository;
import com.user188245.timetable.model.dto.Lecture;
import com.user188245.timetable.model.dto.RegularSchedule;
import com.user188245.timetable.model.dto.request.RequestLecture;
import com.user188245.timetable.model.dto.response.DataResponse;

@Service
public class LectureService implements CreatableService<RequestLecture,Lecture, Long>,
										SingleReadableService<Lecture,Long>,
										UpdatableService<RequestLecture,Lecture, Long>,
										DeletableService<Lecture, Long>{
	
	@Autowired
	LectureRepository lectureRepository;
	
	@Autowired
	RegularScheduleRepository regularScheduleRepository;
	
	@Override
	public ResponseEntity<DataResponse<Lecture>> read(String username, @RequestParam(value="id",required = false) Long key) throws BadAccessException, SQLException{
		Lecture lecture = lectureRepository.find(username, key);
		return DataResponse.build(lecture);
	}
	
	@Override
	public ResponseEntity<DataResponse<Iterable<Lecture>>> readAll(String username) throws SQLException{
		Iterable<Lecture> lectures = lectureRepository.findAllByUsername(username);
		return DataResponse.build(lectures);
	}

	@Override
	public Long create(String username, RequestLecture request) throws BadAccessException, SQLException {
		usernameSetting(username, request.getLecture());
		return lectureRepository.save(username, request.getLecture()).getId();
	}

	@Override
	public Long update(String username, RequestLecture request) throws BadAccessException, NoSuchElementException, SQLException {
		Lecture lec = request.getLecture();
		Lecture lecOrigin = lectureRepository.findById(lec.getId()).get();
		if(lec.getName() != null) {
			lecOrigin.setName(lec.getName());
		}
		if(lec.getHomepage() != null) {
			lecOrigin.setHomepage(lec.getHomepage());
		}
		if(lec.getInstructor() != null) {
			lecOrigin.setInstructor(lec.getInstructor());
		}
		if(lec.getScheduleList() != null) {
			lecOrigin.setScheduleList(lec.getScheduleList());
		}
		usernameSetting(username, request.getLecture());
		return lectureRepository.save(username, lecOrigin).getId();
	}

	@Override
	public void delete(String username, Long key) throws BadAccessException, NoSuchElementException, SQLException {
		lectureRepository.delete(username, key);
	}
	
	public void usernameSetting(String username, Lecture lecture) {
		lecture.setUsername(username);
		for(RegularSchedule rs : lecture.getScheduleList())
			rs.setUsername(username);
	}

}
