package com.user188245.timetable.model.core.util;

import java.util.Collections;
import java.util.EnumSet;
import java.util.Set;

import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import com.user188245.timetable.model.dto.Authority;

@Component
public class AuthorityBitEncoder implements BitEncoder<Authority>{
	
	@Override
	public long encode(Set<Authority> set) {
		long result = 0;
		for(Authority a : set)
			result += 1 << a.ordinal();
		return result;
	}
	
	@Override
	public Set<Authority> decode(long flag){
		EnumSet<Authority> set = EnumSet.noneOf(Authority.class);
		Authority[] values = Authority.values();
		for(int d=0; d<Authority.numberOfItem; d++) {
			if((flag >> d) % 2 == 1) {
				set.add(values[d]);
			}
		}
		return Collections.synchronizedSet(set);
	}

}
