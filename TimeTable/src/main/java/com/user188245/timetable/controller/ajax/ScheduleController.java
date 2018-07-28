package com.user188245.timetable.controller.ajax;

import java.security.Principal;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.NoSuchElementException;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;

import com.user188245.timetable.model.core.ajax.service.ScheduleService;
import com.user188245.timetable.model.core.exception.BadAccessException;
import com.user188245.timetable.model.dto.request.RequestSchedule;
import com.user188245.timetable.model.dto.response.Response;

public class ScheduleController extends ExceptionHandledCrudController<RequestSchedule>{
	
	@Autowired
	ScheduleService scheduleService;

	@Override
	public ResponseEntity<? extends Response> read(Principal principal, @RequestParam(value="id",required = false) Long id)
			throws BadAccessException, NoSuchElementException, SQLException {
		// TODO Auto-generated method stub
		if(id == null)
			return readAll(principal);
		else
			return scheduleService.read(principal.getName(), id);
	}

	@Override
	public ResponseEntity<? extends Response> readAll(Principal principal) throws SQLException {
		// TODO Auto-generated method stub
		return scheduleService.readAll(principal.getName());
	}
	

	public ResponseEntity<? extends Response> readAll(
			Principal principal, 
			@RequestParam(value="id",required = false) Long id, 
			@RequestParam(value="date",required = false) String date) throws SQLException, BadAccessException, NoSuchElementException {

		if(date == null) {
			if(id == null) {
				return readAll(principal);
			}else {
				return read(principal,id);
			}
		}else	
			return scheduleService.readAllByDate(principal.getName(), LocalDate.parse(date));
	}

	@Override
	@DeleteMapping
	public ResponseEntity<? extends Response> delete(Principal principal, @RequestParam(value="id",required = false) Long id)
			throws BadAccessException, NoSuchElementException, SQLException {
		// TODO Auto-generated method stub
		scheduleService.delete(principal.getName(), id);
		return Response.buildOK();
	}

	@Override
	@PostMapping
	public ResponseEntity<? extends Response> create(@Valid @RequestBody RequestSchedule data, BindingResult bindingResult,
			Principal principal) throws BadAccessException, SQLException {
		// TODO Auto-generated method stub
		scheduleService.create(principal.getName(), data);
		return Response.buildOK();
	}

	@Override
	@PatchMapping
	public ResponseEntity<? extends Response> update(@Valid @RequestBody RequestSchedule data, BindingResult bindingResult,
			Principal principal) throws BadAccessException, NoSuchElementException, SQLException {
		scheduleService.update(principal.getName(), data);
		return Response.buildOK();
	}
	
	
}
