package com.yg_ac.dao;

import java.sql.Connection;	
import java.sql.DriverManager;
import java.sql.SQLException;

public class Y_DBmanager {
	public Connection getConnection() {
		String driver = "oracle.jdbc.driver.OracleDriver";
		String url = "jdbc:oracle:thin:@211.45.162.83:1521:xe";
		String dbID = "yg_ac";
		String dbPW = "yg_ac";
		
		Connection conn = null;
		try {
			Class.forName(driver);
		} catch (ClassNotFoundException e) { 
			e.printStackTrace();
		}
		try {
			conn = DriverManager.getConnection(url, dbID, dbPW);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return conn;
	}
}
