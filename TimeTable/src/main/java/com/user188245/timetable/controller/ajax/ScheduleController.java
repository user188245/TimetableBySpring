package com.user188245.timetable.controller.ajax;

import java.security.Principal;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.NoSuchElementException;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.user188245.timetable.model.core.ajax.service.ScheduleService;
import com.user188245.timetable.model.core.exception.BadAccessException;
import com.user188245.timetable.model.dto.request.RequestSchedule;
import com.user188245.timetable.model.dto.response.DataResponse;
import com.user188245.timetable.model.dto.response.Response;

@RestController
@RequestMapping("/ajax/calendar")
public class ScheduleController extends ExceptionHandledCrudController<RequestSchedule>{
	
	@Autowired
	ScheduleService scheduleService;

	@Override
	public ResponseEntity<? extends Response> readAll(Principal principal) throws SQLException {
		// TODO Auto-generated method stub
		return scheduleService.readAll(principal.getName());
	}
	

	@GetMapping("/get")
	public ResponseEntity<? extends Response> readAll(
			Principal principal, 
			@RequestParam(value="id",required = false) Long id, 
			@RequestParam(value="date",required = false) String date) throws SQLException, BadAccessException, NoSuchElementException {

		if(date == null) {
			return readAll(principal);
		}else	
			return scheduleService.read(principal.getName(), LocalDate.parse(date));
	}

	@Override
	@PostMapping("/remove")
	public ResponseEntity<? extends Response> delete(Principal principal, @RequestParam(value="id",required = false) Long id)
			throws BadAccessException, NoSuchElementException, SQLException {
		// TODO Auto-generated method stub
		scheduleService.delete(principal.getName(), id);
		return Response.buildOK();
	}

	@Override
	@PostMapping("/add")
	public ResponseEntity<? extends Response> create(@Valid @RequestBody RequestSchedule data, BindingResult bindingResult,
			Principal principal) throws BadAccessException, SQLException {
		// TODO Auto-generated method stub
		Long id = scheduleService.create(principal.getName(), data);
		return DataResponse.build(id);
	}

	@Override
	@PostMapping("/update")
	public ResponseEntity<? extends Response> update(@Valid @RequestBody RequestSchedule data, BindingResult bindingResult,
			Principal principal) throws BadAccessException, NoSuchElementException, SQLException {
		Long id = scheduleService.update(principal.getName(), data);
		return DataResponse.build(id);
	}
	
	
}
