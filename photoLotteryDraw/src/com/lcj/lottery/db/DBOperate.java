package com.lcj.lottery.db;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

public class DBOperate {

	public static final int TIME_OUT = 5; 
	// δ�����е�ͼƬ���ΪNORMAL_MARK
	public static final int NORMAL_MARK = 0;
	// �����е�ͼƬ���ΪWIN_MARK
	public static final int WIN_MARK = 1;
	
	/*
	 * ��ʼ�����ݿ�
	 */
	public static void init(Connection conn) {
		Statement stmt;
		try {
			stmt = conn.createStatement();
			stmt.setQueryTimeout(TIME_OUT);
			stmt.executeUpdate("drop table if exists images");
			stmt.executeUpdate("create table images(id integer, name string, mark integer, uptime string)");
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}		
	}
	
	/*
	 * ��ͼƬ�б���Ϣ�������ݿ�
	 */
	public static int save(Connection conn, List<String> images) {
		int count = 0;
		try {
			PreparedStatement pstmt = conn.prepareStatement("insert into images values(?,?,?,?)");
			Iterator<String> iterator = images.iterator();
			int i = 1;
			while (iterator.hasNext()) {
				pstmt.setInt(1, i);
				pstmt.setString(2, iterator.next());
				pstmt.setInt(3, NORMAL_MARK);
				pstmt.setString(4, getTimeString());
			}
			
			int result[] = pstmt.executeBatch();
			//����insert�ɹ����ݣ�excute()�ɹ�ʱ����1
			for(int j = 0; j < result.length; j++) {
				if(result[j] == 1) {
					count++;
				}
			}	
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return count;
		
	}
	
	/*
	 * ɾ�����ݿ�����ؼ�¼
	 */
	public static int remove(Connection conn, String imageName) {
		int count = 0;
		PreparedStatement pstmt;
		try {
			pstmt = conn.prepareStatement("delete from images where name=?");
			
			pstmt.setString(1, imageName);
			
			count = pstmt.executeUpdate();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return count;
	}
	
	/*
	 * �����ļ���������ݿ�����Ӧ�ֶ�
	 */
	public static int mark(Connection conn, String imageName) {
		int count = 0;
		try {
			PreparedStatement pstmt = conn.prepareStatement("update images set mark = ?, uptime = ? where name = ?");
			
			pstmt.setInt(1, WIN_MARK);
			pstmt.setString(2, getTimeString());
			pstmt.setString(3, imageName);
			
			count = pstmt.executeUpdate();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return count;
	}
	
	/*
	 * �������л�����������Ǹ�Map<String,String>���ͣ�key��ͼƬ�ļ�����value�ǻ�ʱ��
	 */
	public static Map<String, String> allWinners(Connection conn) {
		Map<String, String> wins = new HashMap<String, String>();
		try {
			PreparedStatement pstmt = conn.prepareStatement("select name, uptime from images where mark = ? order by uptime");
			pstmt.setInt(1, WIN_MARK);
			ResultSet rs = pstmt.executeQuery();
			while(rs.next()) {
				wins.put(rs.getString("name"), rs.getString("uptime"));
				
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return wins;
	}
	
	/*
	 *  �����ݿ��а������˳��ȡ��δ�񽱵�ͼƬ�б�
	 */
	public static List<String> allByRandom(Connection conn) {
		List<String> list = new ArrayList<String>();
		try {
			PreparedStatement pstmt = conn.prepareStatement("select name from images where mark = ? order by rand()");
			pstmt.setInt(1, NORMAL_MARK);
			ResultSet rs = pstmt.executeQuery();
			while(rs.next()) {
				list.add(rs.getString("name"));
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return list;
	} 
	
	/*
	 * ��ȡ��ǰʱ��
	 */
	public static String getTimeString() {
		return new SimpleDateFormat("YYYY-MM-dd HH:mm:ss").format(new Date());
	}
}
