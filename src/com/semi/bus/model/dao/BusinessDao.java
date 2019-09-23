package com.semi.bus.model.dao;

import static common.template.JDBCTemplate.close;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Properties;

import com.semi.bus.model.vo.Business;
import com.semi.emp.model.vo.Employee;
import com.semi.sft.model.vo.Shift;

public class BusinessDao {

	private Properties prop = new Properties();

	public BusinessDao() {
		String path = BusinessDao.class.getResource("/sql/business/bus-query.properties").getPath();
		try {
			prop.load(new FileReader(path));
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public int insertBusiness(Connection conn, Business bus) {
		PreparedStatement pstmt = null;
		String sql = prop.getProperty("insertBusiness");
		int r = -1;
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, bus.getOwnId());
			pstmt.setString(2, bus.getBusName());
			pstmt.setString(3, bus.getBusNum());
			pstmt.setString(4, bus.getBusAddr());
			pstmt.setString(5, bus.getBusPhone() != null ? bus.getBusPhone() : "NULL");
			r = pstmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close(pstmt);
		}
		return r;
	}

	public String getBusId(Connection conn) {
		Statement stmt = null;
		ResultSet rs = null;
		String sql = prop.getProperty("getBusId");
		String busId = null;
		try {
			stmt = conn.createStatement();
			rs = stmt.executeQuery(sql);
			if (rs.next()) {
				busId = rs.getString(1);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close(rs);
			close(stmt);
		}
		return busId;
	}

	public int checkBusNum(Connection conn, String bNum) {
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		String sql = prop.getProperty("checkBusNum");
		int count = 0;
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, bNum);
			rs = pstmt.executeQuery();
			if (rs.next())
				count = rs.getInt(1);
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close(rs);
			close(pstmt);
		}
		return count;
	}

	public ArrayList<Employee> getEmpList(Connection conn, String busId) {
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		String sql = prop.getProperty("getEmpList");
		ArrayList<Employee> list = new ArrayList<>();
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, busId);
			rs = pstmt.executeQuery();
			while (rs.next()) {
				Employee e = new Employee();
				e.setUserId(rs.getString("EMP_ID"));
				e.setUserType(rs.getString("EMP_TYPE"));
				e.setEmpWage(rs.getInt("EMP_WAGE"));
				e.setBankAccount(rs.getString("BANK_ACCOUNT"));
				e.setSftId(rs.getString("SFT_ID"));
				e.setUserName(rs.getString("USER_NAME"));
				e.setProfilePic(rs.getString("PROFILE_PIC"));
				list.add(e);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close(rs);
			close(pstmt);
		}
		return list;
	}
	
	public ArrayList<Shift> getSftList(Connection conn, String busId) {
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		String sql = prop.getProperty("getSftList");
		ArrayList<Shift> list = new ArrayList<>();
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, busId);
			rs = pstmt.executeQuery();
			while (rs.next()) {
				Shift s = new Shift();
				s.setSftId(rs.getString("SFT_ID"));
				s.setSftName(rs.getString("SFT_NAME"));
				s.setSftDay(rs.getString("SFT_DAY"));
				s.setSftOn(rs.getString("SFT_ON"));
				s.setSftOff(rs.getString("SFT_OFF"));
				list.add(s);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close(rs);
			close(pstmt);
		}
		return list;
	}
	
//	public int approvalEmp(Connection conn, String busId, String empId) {
//		PreparedStatement pstmt = null;
//		String sql = prop.getProperty("");
//		int r = -1;
//		try {
//			pstmt = conn.prepareStatement(sql);
//		} catch (SQLException e) {
//			e.printStackTrace();
//		}
//		
//	}

}
