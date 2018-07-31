package com.user188245.timetable.model.core.ajax.service;

import java.sql.SQLException;

import org.springframework.security.access.annotation.Secured;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;

import com.user188245.timetable.model.core.exception.BadAccessException;
import com.user188245.timetable.model.dto.request.Request;

@Service
public interface CreatableService<RQ extends Request, T, K> {
	
	@Secured("ROLE_WRITE")
	public void create(String username, @RequestBody RQ request) throws BadAccessException,SQLException;

}
