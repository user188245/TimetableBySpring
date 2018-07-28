package com.user188245.timetable.model.dao;

import java.time.LocalDate;

import org.springframework.data.jpa.repository.Query;

import com.user188245.timetable.model.dto.IrregularSchedule;

public interface IrregularScheduleRepository extends PrivateRepository<IrregularSchedule, Long>{
	
	@Query(value="Select s from #{#entityName} s where s.username = ?1 and s.date BETWEEN ?2 and ?3")
	public Iterable<IrregularSchedule> findAllByUsernameAndDate(String username, LocalDate dateFrom, LocalDate dateTo);

}
