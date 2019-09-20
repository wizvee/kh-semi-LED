package com.semi.bus.dao;

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

import com.semi.bus.model.vo.Business;
import com.sun.corba.se.spi.orbutil.fsm.Guard.Result;

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

	public Business selectBusiness(Connection conn, String busId) {
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		String sql = prop.getProperty("selectBusiness");
		Business b = null;
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, busId);
			rs = pstmt.executeQuery();
			if (rs.next()) {
				b = new Business();
				b.setBusId(rs.getString("BUS_ID"));
				b.setBusName(rs.getString("BUS_NAME"));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return b;
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

}
