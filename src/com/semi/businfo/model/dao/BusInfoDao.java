package com.semi.businfo.model.dao;

import static common.template.JDBCTemplate.close;

import java.io.FileReader;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

import com.semi.bus.model.vo.Business;
import com.semi.statistics.model.dao.StatisticsDao;

public class BusInfoDao {
	
	private Properties prop=new Properties();
	public BusInfoDao() {
		String path=StatisticsDao.class.getResource("/sql/statistics/statistics-query.properties").getPath();
		try {
			prop.load(new FileReader(path));
		}catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public List<Business>getBusInfo(Connection conn, String busId){
		PreparedStatement pstmt=null;
		ResultSet rs=null;
		String sql=prop.getProperty("getBusInfo");
		List<Business>list=new ArrayList<Business>();
		try {
			pstmt=conn.prepareStatement(sql);
			pstmt.setString(1, busId);
			rs=pstmt.executeQuery();
		while(rs.next()) {
			Business b= new Business();
			b.setOwnName(rs.getString(1));
			b.setBusName(rs.getString(2));
			b.setBusNum(rs.getString(3));
			b.setBusAddr(rs.getString(4));
			b.setBusPhone(rs.getString(5));
			b.setBusDate(rs.getInt(6));
			list.add(b);
			}
		}catch(SQLException e) {
			e.printStackTrace();
		}finally{
			close(rs);
			close(pstmt);
		}
		return list;
	}

}
