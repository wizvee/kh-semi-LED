package com.semi.user.model.dao;

import static common.template.JDBCTemplate.close;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Properties;

import com.semi.noti.model.vo.Notification;
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
		ResultSet rs = null;
		String sql = prop.getProperty("loginUser");
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
		ResultSet rs = null;
		String sql = prop.getProperty("selectUser");
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

	public int setUserType(Connection conn, String userId, String type, String url) {
		PreparedStatement pstmt = null;
		String sql = prop.getProperty("setUserType");
		int r = -1;
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, type);
			pstmt.setString(2, url);
			pstmt.setString(3, userId);
			r = pstmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close(pstmt);
		}
		return r;
	}

	public ArrayList<Notification> getNotiList(Connection conn, String userId) {
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		String sql = prop.getProperty("getNotiList");
		ArrayList<Notification> list = new ArrayList<>();
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, userId);
			rs = pstmt.executeQuery();
			while (rs.next()) {
				Notification n = new Notification();
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
				list.add(n);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close(rs);
			close(pstmt);
		}
		return list;
	}

	public User selectOne(Connection conn, String userId) {
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		User u = null;
		String sql = prop.getProperty("selectOne");
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, userId);
			rs = pstmt.executeQuery();
			if (rs.next()) {
				u = new User();
				u.setUserId(rs.getString("USER_ID"));
				if (rs.getString("USER_TYPE").equals("O")) {
					u.setUserType("사장");
				} else if (rs.getString("USER_TYPE").equals("E")) {
					u.setUserType("종업원");
				}
				u.setEmail(rs.getString("EMAIL"));
				u.setPassword(rs.getString("PASSWORD"));
				u.setUserName(rs.getString("USER_NAME"));
				u.setUserPhone(rs.getString("USER_PHONE"));
				u.setProfilePic(rs.getString("PROFILE_PIC"));
				u.setJoinDate(rs.getDate("JOINDATE"));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close(rs);
			close(pstmt);
		}
		return u;
	}

	public User CheckUser(Connection conn, String userId, String pw) {
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		User u = null;
		String sql = prop.getProperty("CheckUser");
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, userId);
			pstmt.setString(2, pw);
			rs = pstmt.executeQuery();
			if (rs.next()) {
				u = new User();
				u.setUserId(rs.getString("USER_ID"));
				u.setPassword(rs.getString("PASSWORD"));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close(rs);
			close(pstmt);
		}
		return u;
	}

	public int UpdatePw(Connection conn, String userId, String nPw) {
		PreparedStatement pstmt = null;
		int result = 0;
		String sql = prop.getProperty("updatePW");
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, nPw);
			pstmt.setString(2, userId);
			result = pstmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close(pstmt);
		}
		return result;
	}

	public int UpdatePhone(Connection conn, String userId, String phone) {
		PreparedStatement pstmt = null;
		int result = 0;
		String sql = prop.getProperty("updatePhone");
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, phone);
			pstmt.setString(2, userId);
			result = pstmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close(pstmt);
		}
		return result;
	}

	public int UpdateName(Connection conn, String userId, String name) {
		PreparedStatement pstmt = null;
		int result = 0;
		String sql = prop.getProperty("updateName");
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, name);
			pstmt.setString(2, userId);
			result = pstmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close(pstmt);
		}
		return result;
	}
	
	public int UpdatePic(Connection conn, String userId, String upfile) {
		PreparedStatement pstmt = null;
		int result = 0;
		String sql = prop.getProperty("updatePic");
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, upfile);
			pstmt.setString(2, userId);
			result = pstmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close(pstmt);
		}
		return result;
	}

	public int OwneroriginPic(Connection conn, String userId) {
		PreparedStatement pstmt = null;
		int result = 0;
		String sql = prop.getProperty("originPic");
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, "own_default.png");
			pstmt.setString(2, userId);
			result = pstmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close(pstmt);
		}
		return result;
	}
	
	public int EmporiginPic(Connection conn, String userId) {
		PreparedStatement pstmt = null;
		int result = 0;
		String sql = prop.getProperty("originPic");
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, "emp_default.png");
			pstmt.setString(2, userId);
			result = pstmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close(pstmt);
		}
		return result;
	}
	
	public int QuitUser(Connection conn, String email, String pw, String quittype) {
		PreparedStatement pstmt = null;
		int result = 0;
		String sql = prop.getProperty("QuitUser");
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, quittype);
			pstmt.setString(2, email);
			pstmt.setString(3, pw);
			result = pstmt.executeUpdate();
		}catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close(pstmt);
		}
		return result;
	}
	
	public String getUserName(Connection conn, String userId) {
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		String sql = prop.getProperty("getUserName");
		String name = null;
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, userId);
			rs = pstmt.executeQuery();
			if(rs.next())
				name = rs.getString(1);
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close(rs);
			close(pstmt);
		}
		return name;
	}
}
