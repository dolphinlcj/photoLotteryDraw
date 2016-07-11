package com.lcj.lottery.db;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/*
 * ����sqlite����
 */
public class DBConn {
	private Connection conn = null;
	private static DBConn dbc = null;
	String rootDir = System.getProperty("user.dir");
	
	private DBConn() throws SQLException {
		try {
			Class.forName("org.sqlite.JDBC");
			String dbSchemeString = new StringBuffer("jdbc:sqlite:").append(rootDir).
					append("/db/lucky.db").toString();
			conn = DriverManager.getConnection(dbSchemeString);
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
	}
	/*
	 * ��������
	 */
	public static DBConn getInstance() throws SQLException {
		if(dbc == null) {
			dbc = new DBConn();
		}
		
		return dbc;
	}
	
	/*
	 * �������ݿ�����
	 */
	public Connection getConnect() throws Exception {
		if(conn == null) {
			throw new Exception("��ʹ��DBConn.getInstance().connect()");
		}
		else {
			return conn;
		}
	}
}
