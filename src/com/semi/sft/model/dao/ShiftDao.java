package com.semi.sft.model.dao;

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

import com.semi.sft.model.vo.Shift;

public class ShiftDao {
//  ----> test
	private Properties prop = new Properties();

	public ShiftDao() {
		String path = ShiftDao.class.getResource("/sql/shift/sft-query.properties").getPath();
		try {
			prop.load(new FileReader(path));
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public int insertSft(Connection conn, Shift sft) {
		PreparedStatement pstmt = null;
		String sql = prop.getProperty("insertSft");
		int r = -1;
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, sft.getSftName());
			pstmt.setString(2, sft.getSftDay());
			pstmt.setString(3, sft.getSftOn());
			pstmt.setString(4, sft.getSftOff());
			r = pstmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close(pstmt);
		}
		return r;
	}

	public List<Shift> getShiftList(Connection conn, String id) {
		List<Shift> list = new ArrayList<Shift>();
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		String sql = prop.getProperty("getShift");
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, id);
			rs = pstmt.executeQuery();
			while (rs.next()) {

				Shift s = new Shift();
				s.setSftId(rs.getString("SFT_ID"));
				s.setSftOn(rs.getString("SFT_ON"));
				s.setSftOff(rs.getString("SFT_OFF"));
				s.setSftName(rs.getString("SFT_NAME"));

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
}
