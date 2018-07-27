package com.user188245.timetable.model.dao;

import java.util.NoSuchElementException;
import java.util.Optional;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.NoRepositoryBean;

import com.user188245.timetable.model.core.exception.BadAccessException;
import com.user188245.timetable.model.dto.BasicDTO;

@NoRepositoryBean
public interface PrivateRepository<T extends BasicDTO, K> extends TimeTableRepository<T, K>{

	@Query("Select s from #{#entityName} s where s.username = ?1")
	public Iterable<T> findAllByUsername(String username);
	
	@Query("Select s from #{#entityName} s where s.username = ?1")
	public Optional<T> findByUsername(String username);
	
	public default T find(String username, K key) throws BadAccessException {
		T dto = findById(key).get();
		if(dto.getUsername().equals(username))
			return dto;
		else
			throw new BadAccessException("Not allowed to manipulate the data with incorrect session");
	}
	
	public default T save(String username, T entity) throws BadAccessException{
		if(entity.getUsername().equals(username))
			return save(entity);
		else
			throw new BadAccessException("Not allowed to manipulate the data with incorrect session");
	}
	
	public default void delete(String username, K key) throws NoSuchElementException, BadAccessException{
		T dto = findById(key).get();
		if(dto.getUsername().equals(username))
			delete(dto);
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
