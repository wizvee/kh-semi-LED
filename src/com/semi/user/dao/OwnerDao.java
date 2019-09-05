package com.semi.user.dao;

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

import com.semi.user.model.vo.Owner;

public class OwnerDao {

	private Properties prop = new Properties();

	public OwnerDao() {
		String path = OwnerDao.class.getResource("/sql/user/owner-query.properties").getPath();
		try {
			prop.load(new FileReader(path));
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public Owner castingTypeO(Connection conn, String userId) {
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		String sql = prop.getProperty("castingType");
		Owner o = null;
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, userId);
			rs = pstmt.executeQuery();
			if (rs.next()) {
				o = new Owner();
				o.setUserId(rs.getString("USER_ID"));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close(rs);
			close(pstmt);
		}
		return o;
	}

	public ArrayList<String> getBnsList(Connection conn, String userId) {
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		String sql = prop.getProperty("getBnsList");
		ArrayList<String> list = new ArrayList<>();
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, userId);
			rs = pstmt.executeQuery();
			while(rs.next()) {
				list.add(rs.getString("BUS_ID"));
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
