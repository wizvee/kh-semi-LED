package com.semi.caldendar.model.dao;

import static common.template.JDBCTemplate.close;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.Properties;

import com.semi.caldendar.model.vo.Cal;

public class CalendarDao {

	private Properties prop = new Properties();

	public CalendarDao() {
		String path = CalendarDao.class.getResource("/sql/calendar/cal-query.properties").getPath();
		try {
			prop.load(new FileReader(path));
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public int insertCal(Connection conn, Cal cal) {
		PreparedStatement pstmt = null;
		String sql = prop.getProperty("insertCal");
		int r = -1;
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setDate(1, new java.sql.Date(cal.getCalDate().getTime()));
			pstmt.setString(2, cal.getBusId());
			pstmt.setString(3, cal.getSftId().equals("all") ? null : cal.getSftId());
			pstmt.setString(4, cal.getCalTitle());
			pstmt.setString(5, cal.getCalDetail());
			r = pstmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close(pstmt);
		}
		return r;
	}

}
