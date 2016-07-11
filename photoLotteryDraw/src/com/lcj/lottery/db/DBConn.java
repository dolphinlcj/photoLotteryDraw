package com.lcj.lottery.db;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/*
 * 创建sqlite连接
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
	 * 单例调用
	 */
	public static DBConn getInstance() throws SQLException {
		if(dbc == null) {
			dbc = new DBConn();
		}
		
		return dbc;
	}
	
	/*
	 * 返回数据库连接
	 */
	public Connection getConnect() throws Exception {
		if(conn == null) {
			throw new Exception("请使用DBConn.getInstance().connect()");
		}
		else {
			return conn;
		}
	}
}
