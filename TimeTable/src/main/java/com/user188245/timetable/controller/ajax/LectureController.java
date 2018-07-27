package com.user188245.timetable.controller.ajax;

import java.security.Principal;
import java.sql.SQLException;
import java.util.NoSuchElementException;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.user188245.timetable.model.core.LectureService;
import com.user188245.timetable.model.core.exception.BadAccessException;
import com.user188245.timetable.model.dto.RequestLecture;
import com.user188245.timetable.model.dto.Response;

@RestController
@RequestMapping(value = "/ajax/lecture")
public class LectureController extends ExceptionHandledCrudController<RequestLecture>{
	
	@Autowired
	LectureService lectureService;

	@Override
	@GetMapping
	public ResponseEntity<? extends Response> read(Principal principal, @RequestParam(value="id",required = false) Long id)
			throws BadAccessException, NoSuchElementException, SQLException {
		// TODO Auto-generated method stub
		if(id == null)
			return readAll(principal);
		else
			return lectureService.read(principal.getName(), id);
	}

	@Override
	public ResponseEntity<? extends Response> readAll(Principal principal) throws SQLException {
		// TODO Auto-generated method stub
		return lectureService.readAll(principal.getName());
	}

	@Override
	@DeleteMapping
	public ResponseEntity<? extends Response> delete(Principal principal, @RequestParam(value="id",required = false) Long id)
			throws BadAccessException, NoSuchElementException, SQLException {
		// TODO Auto-generated method stub
		lectureService.delete(principal.getName(), id);
		return Response.buildOK();
	}

	@Override
	@PostMapping
	public ResponseEntity<? extends Response> create(@Valid @RequestBody RequestLecture data, BindingResult bindingResult,
			Principal principal) throws BadAccessException, SQLException {
		// TODO Auto-generated method stub
		lectureService.create(principal.getName(), data);
		return Response.buildOK();
	}

	@Override
	@PatchMapping
	public ResponseEntity<? extends Response> update(@Valid @RequestBody RequestLecture data, BindingResult bindingResult,
			Principal principal) throws BadAccessException, NoSuchElementException, SQLException {
		lectureService.update(principal.getName(), data);
		return Response.buildOK();
	}
	
	

}
