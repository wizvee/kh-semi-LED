package com.semi.task.model.dao;

import static common.template.JDBCTemplate.close;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.Properties;

import com.semi.task.model.vo.Task;

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

}
