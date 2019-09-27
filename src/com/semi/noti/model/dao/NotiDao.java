package com.semi.noti.model.dao;

import static common.template.JDBCTemplate.close;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
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

	public String getNotiId(Connection conn) {
		Statement stmt = null;
		ResultSet rs = null;
		String sql = prop.getProperty("getNotiId");
		String id = null;
		try {
			stmt = conn.createStatement();
			rs = stmt.executeQuery(sql);
			if (rs.next())
				id = rs.getString(1);
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close(rs);
			close(stmt);
		}
		return id;
	}

	public Notification selectNoti(Connection conn, String id) {
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		String sql = prop.getProperty("selectNoti");
		Notification n = null;
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, id);
			rs = pstmt.executeQuery();
			if (rs.next()) {
				n = new Notification();
				n.setNotiId(rs.getString("NOTI_ID"));
				n.setUserId(rs.getString("USER_ID"));
				n.setTargetUserId(rs.getString("TARGET_USER_ID"));
				n.setTargetBusId(rs.getString("TARGET_BUS_ID"));
				n.setNotiType(rs.getString("NOTI_TYPE"));
				n.setNotiMsg(rs.getString("NOTI_MSG"));
				n.setNotiUrl(rs.getString("NOTI_URL"));
				n.setNotiDate(rs.getTimestamp("NOTI_DATE"));
				n.setUserName(rs.getString("USER_NAME"));
				n.setProfilePic(rs.getString("PROFILE_PIC"));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close(rs);
			close(pstmt);
		}
		return n;
	}
	
	public int isReadNoti(Connection conn, String notiId) {
		PreparedStatement pstmt = null;
		String sql = prop.getProperty("isReadNoti");
		int r = -1;
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, notiId);
			r = pstmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close(pstmt);
		}
		return r;
	}

}
