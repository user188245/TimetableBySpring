package com.user188245.timetable.model.core;

import java.util.List;

import com.user188245.timetable.model.dto.DataResponse;
import com.user188245.timetable.model.dto.Lecture;
import com.user188245.timetable.model.dto.RequestLecture;

public class LectureService implements CrudService<RequestLecture,DataResponse<List<Lecture>>>{

	@Override
	public DataResponse<List<Lecture>> read(String username, RequestLecture request) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public boolean create(String username, RequestLecture request) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean update(String username, RequestLecture request) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean delete(String username, RequestLecture request) {
		// TODO Auto-generated method stub
		return false;
	}

}
