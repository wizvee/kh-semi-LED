package com.semi.atd.model.dao;

import static common.template.JDBCTemplate.close;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

import com.semi.atd.model.vo.Attendance;
import com.semi.emp.model.vo.Employee;
import com.semi.sft.model.dao.ShiftDao;

public class AttendanceDao {

	
	private Properties prop = new Properties();

	public AttendanceDao() {
		String path = ShiftDao.class.getResource("/sql/attendance/atd-query.properties").getPath();
		try {
			prop.load(new FileReader(path));
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public Attendance setAttendance(Connection conn, Employee e, String id) {
//		 SFT_ON, SFT_OFF, SFT_ID, ATD_ON, ATD_OFF 
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		String sql = prop.getProperty("setAttendance");
		Attendance a = new Attendance();
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, id);
			pstmt.setString(2, e.getUserId());
			rs = pstmt.executeQuery();
			while (rs.next()) {
				a.setSftId(rs.getString("SFT_ID"));
				a.setAtdOn(rs.getString("ATD_ON"));
				a.setAtdOff(rs.getString("ATD_OFF"));
				if(rs.getString("ATD_TYPE")!= null) {
					a.setAtdType(rs.getString("ATD_TYPE").split(","));
				}
			}
		} catch (SQLException b) {
			b.printStackTrace();
		} finally {
			close(rs);
			close(pstmt);
		}return a;

	}
	
	
	public List<Attendance> getAttendanceList(Connection conn, String date, String id) {
		List<Attendance> list = new ArrayList<Attendance>();
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		System.out.println(id);
		System.out.println(date);
		String sql = prop.getProperty("setAttendanceList");
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, id);
			pstmt.setString(2, date);
			rs = pstmt.executeQuery();
			
			while(rs.next()) {
				Attendance a = new Attendance();
				
				a.setEmpId(rs.getString("EMP_ID"));
				a.setSftId(rs.getString("SFT_ID"));
				a.setSftName(rs.getString("SFT_NAME"));
				a.setSftOn(rs.getString("SFT_ON"));
				a.setSftOff(rs.getString("SFT_OFF"));
				if(rs.getString("ATD_TYPE")!= null) {
					a.setAtdType(rs.getString("ATD_TYPE").split(","));
				}
				a.setAtdOn(rs.getString("ATD_ON"));
				a.setAtdOff(rs.getString("ATD_OFF"));
				
				list.add(a);
				
			}
			
			
			
		}catch (SQLException e) {
			e.printStackTrace();
		}finally {
			close(rs);
			close(pstmt);
		}return list;
		
		
		
		
	}
	
	
}

