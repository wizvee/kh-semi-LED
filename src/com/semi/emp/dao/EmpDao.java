package com.semi.emp.dao;

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

import com.semi.bus.model.vo.Business;
import com.semi.emp.model.vo.Employee;

public class EmpDao {
	
	private Properties prop = new Properties();

	public EmpDao() {
		String path = EmpDao.class.getResource("/sql/user/emp-query.properties").getPath();
		try {
			prop.load(new FileReader(path));
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public Employee castingTypeE(Connection conn, String userId) {
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		String sql = prop.getProperty("castingType");
		Employee e = null;
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, userId);
			rs = pstmt.executeQuery();
			if (rs.next()) {
				e = new Employee();
				e.setUserId(rs.getString("USER_ID"));
			}
		} catch (SQLException e1) {
			e1.printStackTrace();
		} finally {
			close(rs);
			close(pstmt);
		}
		return e;
	}
	
	public ArrayList<Business> getBusList(Connection conn, String userId) {
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		String sql = prop.getProperty("getBusList");
		ArrayList<Business> list = new ArrayList<>();
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, userId);
			rs = pstmt.executeQuery();
			while(rs.next()) {
				Business b = new Business();
				b.setBusId(rs.getString("BUS_ID"));
				b.setBusName(rs.getString("BUS_NAME"));
				list.add(b);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return list;
	}

}
