package com.yg_ac.dao;

import java.sql.Connection;	
import java.sql.DriverManager;
import java.sql.SQLException;

public class Y_DBmanager {
	private static Connection conn = null;
	public static Connection getConnection() {
		if(conn!=null) {	// 이미 접속한경우
			return conn;
		} else {	// 새롭게 접속을 해야하는 경우
			String driver = "oracle.jdbc.driver.OracleDriver";
			String url = "jdbc:oracle:thin:@211.45.162.83:1521:xe";
			String dbID = "yg_ac";
			String dbPW = "yg_ac";
			
			try {
				Class.forName(driver);
				conn = DriverManager.getConnection(url, dbID, dbPW);
			} catch(Exception e) {
				e.printStackTrace();
			}
			return conn;
		}
	}
}
