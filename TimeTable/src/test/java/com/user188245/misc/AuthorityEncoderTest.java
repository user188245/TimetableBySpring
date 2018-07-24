package com.user188245.misc;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import java.sql.SQLException;
import java.util.EnumSet;
import java.util.Set;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import com.user188245.timetable.model.core.util.AuthorityBitEncoder;
import com.user188245.timetable.model.dto.Authority;

public class AuthorityEncoderTest {
	
	Set<Authority> set;
	
	@Autowired
	AuthorityBitEncoder bitEncoder = new AuthorityBitEncoder();
	long flag;
	
	@Test
	public void encodeTest1() throws SQLException {
		set = EnumSet.of(Authority.READ,Authority.SUPER);
		flag = bitEncoder.encode(set);
		assertEquals(5, flag);
	}
	
	@Test
	public void encodeTest2() throws SQLException {
		set = EnumSet.of(Authority.READ,Authority.WRITE);
		flag = bitEncoder.encode(set);
		assertEquals(3, flag);
	}
	
	@Test
	public void encodeTest3() throws SQLException {
		set = EnumSet.of(Authority.WRITE);
		flag = bitEncoder.encode(set);
		assertEquals(2, flag);
	}
	
	@Test
	public void decodeTest1() throws SQLException {
		flag = 7;
		set = bitEncoder.decode(flag);
		assertTrue(set.contains(Authority.READ));
		assertTrue(set.contains(Authority.WRITE));
		assertTrue(set.contains(Authority.SUPER));
	}
	
	@Test
	public void decodeTest2() throws SQLException {
		flag = 6;
		set = bitEncoder.decode(flag);
		assertTrue(!set.contains(Authority.READ));
		assertTrue(set.contains(Authority.WRITE));
		assertTrue(set.contains(Authority.SUPER));
	}
	
	@Test
	public void decodeTest3() throws SQLException {
		flag = 4;
		set = bitEncoder.decode(flag);
		assertTrue(!set.contains(Authority.READ));
		assertTrue(!set.contains(Authority.WRITE));
		assertTrue(set.contains(Authority.SUPER));
	}

}
