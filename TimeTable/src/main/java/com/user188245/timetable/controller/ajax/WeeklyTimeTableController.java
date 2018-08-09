package com.user188245.timetable.controller.ajax;

import java.security.Principal;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.NoSuchElementException;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.user188245.timetable.model.core.ajax.service.WeeklyTimeTableService;
import com.user188245.timetable.model.core.exception.BadAccessException;
import com.user188245.timetable.model.dto.request.RequestWeeklyTimeTable;
import com.user188245.timetable.model.dto.response.DataResponse;
import com.user188245.timetable.model.dto.response.Response;

@Controller
@RequestMapping("/ajax/timetable")
public class WeeklyTimeTableController extends ExceptionHandledCrudController<RequestWeeklyTimeTable>{
	
	@Autowired
	WeeklyTimeTableService weeklyTimeTableService;

	@Override
	public ResponseEntity<? extends Response> readAll(Principal principal) throws SQLException {
		return null;
	}
	
	@GetMapping("/get")
	public ResponseEntity<? extends Response> read(Principal principal, @RequestParam String date) throws SQLException, BadAccessException, NoSuchElementException {
		// TODO Auto-generated method stub
		return weeklyTimeTableService.read(principal.getName(), LocalDate.parse(date));
	}

	@Override
	public ResponseEntity<? extends Response> delete(Principal principal, Long key)
			throws BadAccessException, NoSuchElementException, SQLException {
		return null;
	}

	@Override
	public ResponseEntity<? extends Response> create(@Valid @RequestBody RequestWeeklyTimeTable data, BindingResult bindingResult,
			Principal principal) throws BadAccessException, SQLException {
		return null;
	}

	@Override
	@PostMapping("/update")
	public ResponseEntity<? extends Response> update(@Valid @RequestBody RequestWeeklyTimeTable data, BindingResult bindingResult,
			Principal principal) throws BadAccessException, NoSuchElementException, SQLException {
		// TODO Auto-generated method stub
		Long id = weeklyTimeTableService.update(principal.getName(), data);
		return DataResponse.build(id);
	}
	
	

}
