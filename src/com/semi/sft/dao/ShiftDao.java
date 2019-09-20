package com.semi.sft.dao;

import static common.template.JDBCTemplate.close;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.Properties;

import com.semi.sft.model.vo.Shift;

public class ShiftDao {

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

}
