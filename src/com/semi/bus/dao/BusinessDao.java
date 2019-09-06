package com.semi.bus.dao;

import static common.template.JDBCTemplate.close;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Properties;

import com.semi.bus.model.vo.Business;

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

	public int insertBusiness(Connection conn, String ownId, String name, String addr, String phone, String bNum) {
		PreparedStatement pstmt = null;
		String sql = prop.getProperty("insertBusiness");
		int r = -1;
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, ownId);
			pstmt.setString(2, name);
			pstmt.setString(3, bNum);
			pstmt.setString(4, addr);
			pstmt.setString(5, phone);
			r = pstmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close(pstmt);
		}
		return r;
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
