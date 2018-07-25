package com.user188245.timetable.model.dao;

import java.util.Optional;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.NoRepositoryBean;

import com.user188245.timetable.model.core.exception.BadAccessException;
import com.user188245.timetable.model.dto.BasicDTO;

@NoRepositoryBean
public interface PrivateRepository<T extends BasicDTO> extends TimeTableRepository<T>{

	@Query("Select s from #{#entityName} s where s.username = ?1")
	public Iterable<T> findAllByUsername(String username);
	
	@Query("Select s from #{#entityName} s where s.username = ?1")
	public Optional<T> findByUsername(String username);
	
	public default <S extends T> S save(String username, S entity) {
		if(entity.getUsername().equals(username))
			return save(entity);
		else
			throw new BadAccessException("Not allowed to manipulate the data with incorrect session");
	}
	
	public default void delete(String username, T entity) {
		if(entity.getUsername().equals(username))
			delete(entity);
		else
			throw new BadAccessException("Not allowed to manipulate the data with incorrect session");
	}
	
	@Override
	@Deprecated
	<S extends T> S save(S entity);

	@Override
	@Deprecated
	<S extends T> Iterable<S> saveAll(Iterable<S> entities);

	@Override
	@Deprecated
	void delete(T entity);

	@Override
	@Deprecated
	void deleteAll();
	
}
