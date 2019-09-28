package com.semi.task.model.dao;

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

import com.semi.caldendar.model.vo.Cal;
import com.semi.task.model.vo.Task;
import com.sun.corba.se.spi.orbutil.fsm.Guard.Result;

public class TaskDao {

	private Properties prop = new Properties();

	public TaskDao() {
		String path = TaskDao.class.getResource("/sql/calendar/task-query.properties").getPath();
		try {
			prop.load(new FileReader(path));
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public int addTask(Connection conn, Task t) {
		PreparedStatement pstmt = null;
		String sql = prop.getProperty("addTask");
		int r = -1;
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setDate(1, new java.sql.Date(t.getTaskDate().getTime()));
			pstmt.setString(2, t.getUserId());
			pstmt.setString(3, t.getTaskMsg());
			r = pstmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close(pstmt);
		}
		return r;
	}

	public ArrayList<Task> getTask(Connection conn, String calId) {
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		String sql = prop.getProperty("getTask");
		ArrayList<Task> list = new ArrayList<>();
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, calId);
			rs = pstmt.executeQuery();
			while (rs.next()) {
				Task t = new Task();
				t.setTaskId(rs.getString("TASK_ID"));
				t.setTaskDate(rs.getDate("TASK_DATE"));
				t.setUserId(rs.getString("USER_ID"));
				t.setTaskMsg(rs.getString("TASK_MSG"));
				t.setDone(rs.getString("DONE").equals("T") ? true : false);
				t.setUserName(rs.getString("USER_NAME"));
				list.add(t);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close(rs);
			close(pstmt);
		}
		return list;
	}

}
