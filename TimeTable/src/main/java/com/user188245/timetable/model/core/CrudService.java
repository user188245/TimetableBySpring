package com.user188245.timetable.model.core;

public interface CrudService<RQ,RS> {
	
	public RS read(String username, RQ request);
	
	public boolean create(String username, RQ request);
	
	public boolean update(String username, RQ request);
	
	public boolean delete(String username, RQ request);

}
