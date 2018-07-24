package com.user188245.timetable.model.core.util;

import java.util.Set;

public interface BitEncoder<T extends Enum<T>> {
	
	public long encode(Set<T> set);
	
	public Set<T> decode(long flag);

}
