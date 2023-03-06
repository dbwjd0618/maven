package com.yj.maven.listener;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class TestServletContextListener {
	private final String DB_DRIVER_CLASS = "org.mariadb.jdbc.Driver";
	private final String DB_URL = "jdbc:mariadb://127.0.0.1:3306/maven";
	
	private static final String DB_USERNAME = "maven";
	private static final String DB_PASSWORD = "maven";
	
	private static Connection conn;
	PreparedStatement pstmt = null;
	ResultSet rs = null;
	
	public void connectDB() {
		try {
			Class.forName(DB_DRIVER_CLASS);
			conn = DriverManager.getConnection(DB_URL, DB_USERNAME, DB_PASSWORD);
			if(conn != null) {
				System.out.println("DB 접속 성공");
			}
		} catch (SQLException e) {
			e.printStackTrace();
			System.out.println("DB 연결 실패");
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
			System.out.println("드라이브 로딩 실패");
		}
		
		try {
			String sql = "select * from user_info";
			pstmt = conn.prepareStatement(sql);
			rs = pstmt.executeQuery();
			
			String userId = null;
			String password = null;
			String name = null;
			String memo = null;
			
			while(rs.next()) {
				userId = rs.getString(1);
				password = rs.getString(2);
				name = rs.getString(3);
				memo = rs.getString(4);
			}

			System.out.println(userId);
			System.out.println(password);
			System.out.println(name);
			System.out.println(memo);
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
				try {
					if(rs != null) {
						rs.close();
					}
					if(pstmt != null) {
						pstmt.close();
					}
					if(conn != null && !conn.isClosed()) {
						conn.close();
					}
				} catch (SQLException e) {
					e.printStackTrace();
				}
		}
	}
	
	public static void main(String[] args) {
		TestServletContextListener test = new TestServletContextListener();
		test.connectDB();
	}
}
