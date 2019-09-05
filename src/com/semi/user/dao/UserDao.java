package com.semi.user.dao;

import static common.template.JDBCTemplate.close;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Properties;

import com.semi.user.model.vo.User;

import common.util.SHA512;

public class UserDao {

	private Properties prop = new Properties();

	public UserDao() {
		String path = UserDao.class.getResource("/sql/user/user-query.properties").getPath();
		try {
			prop.load(new FileReader(path));
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public int insertUser(Connection conn, String email, String password, String name, String phone) {
		PreparedStatement pstmt = null;
		String sql = prop.getProperty("insertUser");
		int r = -1;
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, email);
			pstmt.setString(2, password);
			pstmt.setString(3, name);
			pstmt.setString(4, phone);
			r = pstmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close(pstmt);
		}
		return r;
	}

	public int checkUser(Connection conn, String email) {
		PreparedStatement pstmt = null;
		String sql = prop.getProperty("checkUser");
		int r = -1;
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, email);
			r = pstmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close(pstmt);
		}
		return r;
	}

	public int createToken(Connection conn, String email) {
		PreparedStatement pstmt = null;
		String sql = prop.getProperty("createToken");
		int r = -1;
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, email);
			pstmt.setString(2, SHA512.getSHA512(email));
			r = pstmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close(pstmt);
		}
		return r;
	}

	public int brokenToken(Connection conn, String token) {
		PreparedStatement pstmt = null;
		String sql = prop.getProperty("brokenToken");
		int r = -1;
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, token);
			r = pstmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close(pstmt);
		}
		return r;
	}

	public User selectUser(Connection conn, String email, String password) {
		PreparedStatement pstmt = null;
		String sql = prop.getProperty("loginUser");
		ResultSet rs = null;
		User u = null;
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, email);
			pstmt.setString(2, password);
			rs = pstmt.executeQuery();
			if (rs.next()) {
				u = new User();
				u.setUserId(rs.getString("USER_ID"));
				u.setEmail(rs.getString("EMAIL"));
				u.setUserType(rs.getString("USER_TYPE"));
				u.setMailCheck(rs.getString("MAIL_CHECK").equals("T") ? true : false);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close(rs);
			close(pstmt);
		}
		return u;
	}

	public User selectUser(Connection conn, String email) {
		PreparedStatement pstmt = null;
		String sql = prop.getProperty("selectUser");
		ResultSet rs = null;
		User u = null;
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, email);
			rs = pstmt.executeQuery();
			if (rs.next()) {
				u = new User();
				u.setUserId(rs.getString("USER_ID"));
				u.setEmail(rs.getString("EMAIL"));
				u.setUserType(rs.getString("USER_TYPE"));
				u.setMailCheck(rs.getString("MAIL_CHECK").equals("T") ? true : false);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close(rs);
			close(pstmt);
		}
		return u;
	}

	public int setUserType(Connection conn, String userId, String type) {
		PreparedStatement pstmt = null;
		String sql = prop.getProperty("setUserType");
		int r = -1;
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, type);
			pstmt.setString(2, userId);
			r = pstmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close(pstmt);
		}
		return r;
	}

}
