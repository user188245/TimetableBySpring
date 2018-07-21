package com.user188245.timetable.model.dao;

import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.NoRepositoryBean;

@NoRepositoryBean
public interface TimeTableRepository<T> extends CrudRepository<T, Long>{

}
