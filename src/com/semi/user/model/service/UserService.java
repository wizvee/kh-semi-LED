package com.semi.user.model.service;

import static common.template.JDBCTemplate.close;
import static common.template.JDBCTemplate.commit;
import static common.template.JDBCTemplate.getConnection;
import static common.template.JDBCTemplate.rollback;

import java.sql.Connection;
import java.util.ArrayList;

import com.semi.noti.model.vo.Notification;
import com.semi.user.model.dao.UserDao;
import com.semi.user.model.vo.User;

public class UserService {

	private UserDao dao = new UserDao();

	public int insertUser(String email, String name, String phone, String password) {
		Connection conn = getConnection();
		int r = dao.insertUser(conn, email, name, phone, password);
		int r2 = dao.createToken(conn, email);
		if (r > 0 && r2 > 0)
			commit(conn);
		else
			rollback(conn);
		close(conn);
		return r + r2;
	}

	public int brokenToken(String token, String email) {
		Connection conn = getConnection();
		int r = dao.brokenToken(conn, token);
		int r2 = dao.checkUser(conn, email);
		if (r > 0 && r2 > 0)
			commit(conn);
		else
			rollback(conn);
		close(conn);
		return r + r2;
	}

	public User selectUser(String email, String pw) {
		Connection conn = getConnection();
		User u = dao.selectUser(conn, email, pw);
		close(conn);
		return u;
	}

	public User selectUser(String email) {
		Connection conn = getConnection();
		User u = dao.selectUser(conn, email);
		close(conn);
		return u;
	}

	public int setUserType(String userId, String type) {
		Connection conn = getConnection();
		int r = dao.setUserType(conn, userId, type);
		if (r > 0)
			commit(conn);
		else
			rollback(conn);
		close(conn);
		return r;
	}
	
	public ArrayList<Notification> getNotiList(String busId) {
		Connection conn = getConnection();
		ArrayList<Notification> list = dao.getNotiList(conn, busId);
		close(conn);
		return list;
	}
	
	public User selectOne(String userId) {
		Connection conn = getConnection();
		User u = dao.selectOne(conn,userId);
		close(conn);
		return u;
	}
	
	public User checkpw(String pw) {
		Connection conn=getConnection();
		User u=dao.checkpw(conn, pw);
		close(conn);
		return u;
	}

}
