package com.semi.noti.model.dao;

import static common.template.JDBCTemplate.close;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.Properties;

import com.semi.noti.model.vo.Notification;

public class NotiDao {
	
	private Properties prop = new Properties();
	
	public NotiDao() {
		String path = NotiDao.class.getResource("/sql/notification/noti-sql.properties").getPath();
		try {
			prop.load(new FileReader(path));
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public int insertNoti(Connection conn, Notification n) {
		PreparedStatement pstmt = null;
		String sql = prop.getProperty("insertNoti");
		int r = -1;
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, n.getUserId());
			pstmt.setString(2, n.getTargetUserId());
			pstmt.setString(3, n.getTargetBusId());
			pstmt.setString(4, n.getNotiType());
			pstmt.setString(5, n.getNotiMsg());
			pstmt.setString(6, n.getNotiUrl());
			r = pstmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close(pstmt);
		}
		return r;
	}

}
