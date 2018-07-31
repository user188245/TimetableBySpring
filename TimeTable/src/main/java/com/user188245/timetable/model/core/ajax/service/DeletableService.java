package com.user188245.timetable.model.core.ajax.service;

import java.sql.SQLException;
import java.util.NoSuchElementException;

import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestParam;

import com.user188245.timetable.model.core.exception.BadAccessException;
import com.user188245.timetable.model.dto.request.Request;

@Service
public interface DeletableService<RQ extends Request, T, K> {
	
	public void delete(String username, @RequestParam(value="id",required = true) K id) throws BadAccessException,NoSuchElementException,SQLException;

}
