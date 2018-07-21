package com.user188245.misc;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

import org.junit.Before;
import org.junit.Test;

public class JDBCDriverTest {
	
	//String url = "jdbc:mysql://localhost:3306/timetable?autoReconnect=true&useSSL=false&characterEncoding=UTF-8";
	String url = "jdbc:mysql://localhost:3306/timetable?serverTimezone=UTC";
	String user = "admin";
	String password = "admin";
	

	@Before
	public void setUp() throws Exception {
		Class.forName("com.mysql.cj.jdbc.Driver");
	}

	@Test
	public void test() throws SQLException {
		Connection con = DriverManager.getConnection(url, user, password);
		con.close();
	}

}
