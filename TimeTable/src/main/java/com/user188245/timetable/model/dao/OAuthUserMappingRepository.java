package com.user188245.timetable.model.dao;

import java.util.Optional;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import com.user188245.timetable.model.dto.OAuthUserMapping;

public interface OAuthUserMappingRepository extends TimeTableRepository<OAuthUserMapping, Long>{
	
	@Query("Select s from OAuthUserMapping s where s.identifier = ?1 and s.authServer = ?2")
	public Optional<OAuthUserMapping> findByIdentifierAndAuthServer(String identifier, String authServer);
	
	@Modifying
	@Transactional
	@Query("Update OAuthUserMapping s set s.username = ?2 where s.identifier = ?1")
	public void registUsername(String identifier, String username);

}
