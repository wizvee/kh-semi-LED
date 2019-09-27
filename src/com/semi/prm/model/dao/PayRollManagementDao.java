package com.semi.prm.model.dao;

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

import com.semi.owner.model.dao.OwnerDao;
import com.semi.prm.model.vo.PayRollManagement;


public class PayRollManagementDao {

	Properties prop = new Properties();
	
	public PayRollManagementDao() {
		// TODO Auto-generated constructor stub
		String path = OwnerDao.class.getResource("/sql/user/prm-query.properties").getPath();
		try {
			prop.load(new FileReader(path));
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public List<PayRollManagement> makePayRollList(Connection conn, String id, String type, int length) {
		List<PayRollManagement> list = new ArrayList<PayRollManagement>();
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		String sql = prop.getProperty("makePayRollList");
		
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, length);
			pstmt.setString(2, id);
			pstmt.setString(3,type);
			rs = pstmt.executeQuery();
			
			while(rs.next()) {
				PayRollManagement p = new PayRollManagement();
				p.setEmpName(rs.getString("NAME"));
				p.setPayRoll(rs.getInt("PAY"));
				p.setWorkTime(rs.getInt("WORK"));
				p.setLateCount(rs.getInt("LATE"));
				p.setEarlyCount(rs.getInt("EARLY"));
				
				list.add(p);
			}
		}catch(SQLException e) {
			e.printStackTrace();
		}finally {
			close(rs);
			close(pstmt);
		}return list;
	}
	
	
	
	
}
