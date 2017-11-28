package com.iu.util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class DBconnector {

	
	public static Connection getConnect() throws Exception{
		String user = "user03";
		String password = "user03";
		String url = "jdbc:oracle:thin:@192.168.20.52:1521:xe";
		String driver = "oracle.jdbc.driver.OracleDriver";

		//2.드라이버를 메모리에 로딩
		Class.forName(driver);

		//3.로그인 Connection 연결
		Connection con = DriverManager.getConnection(url, user, password);
		
		return con;
	}
	
	public static void disConnect(PreparedStatement st, Connection con) throws Exception{
		st.close();
		con.close();
	}
	
	public static void disConnect(ResultSet rs, PreparedStatement st, Connection con) throws Exception{
		rs.close();
		DBconnector.disConnect(st, con);
	}
}
