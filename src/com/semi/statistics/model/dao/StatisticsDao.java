package com.semi.statistics.model.dao;

import static common.template.JDBCTemplate.close;

import java.io.FileReader;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

import com.semi.statistics.model.vo.Statistics;
import com.semi.statistics.model.vo.StatisticsMonth;

public class StatisticsDao {
	
	private Properties prop=new Properties();
	public StatisticsDao() {
		String path=StatisticsDao.class.getResource("/sql/statistics/statistics-query.properties").getPath();
		try {
			prop.load(new FileReader(path));
		}catch (Exception e) {
			e.printStackTrace();
		}
	}
		
	// 1번째 통계 데이터
	public List<Statistics> forWageLine(Connection conn, String busId){
		PreparedStatement pstmt=null;
		ResultSet rs=null;
		String sql=prop.getProperty("forWageLine");
		List<Statistics>list=new ArrayList<Statistics>();
		Statistics st= new Statistics();
		List<StatisticsMonth>lists=new ArrayList<StatisticsMonth>();
		try {
			pstmt=conn.prepareStatement(sql);
			pstmt.setString(1, busId);
			rs=pstmt.executeQuery();
			while(rs.next()) {
				StatisticsMonth s=new StatisticsMonth();
				s.setYear(rs.getInt(1));
				s.setJan(rs.getInt(2));
				s.setFeb(rs.getInt(3));
				s.setMar(rs.getInt(4));
				s.setApr(rs.getInt(5));
				s.setMay(rs.getInt(6));
				s.setJun(rs.getInt(7));
				s.setJuly(rs.getInt(8));
				s.setAug(rs.getInt(9));
				s.setSep(rs.getInt(10));
				s.setOct(rs.getInt(11));
				s.setNov(rs.getInt(12));
				s.setDec(rs.getInt(13));
				lists.add(s);
			}
			st.setYears(lists);
			list.add(st);
		}catch(SQLException e) {
			e.printStackTrace();
		}finally {
			close(rs);
			close(pstmt);
		}
		return list;
	}
	
	//2번째 통계 데이터 
	public List<Statistics> forWageTable(Connection conn, String busId){
		PreparedStatement pstmt=null;
		ResultSet rs=null;
		String sql=prop.getProperty("forWageTable");
		List<Statistics>list=new ArrayList<Statistics>();
		try {
			pstmt=conn.prepareStatement(sql);
			pstmt.setString(1, busId);
			rs=pstmt.executeQuery();
			while(rs.next()) {
				Statistics s=new Statistics();
				s.setEmpName(rs.getString(1));
				s.setTotalWage(rs.getInt(2));
				s.setWorkingNow(rs.getDate(3)!=null?false:true);
				list.add(s);
			}
		}catch(SQLException e) {
			e.printStackTrace();
		}finally {
			close(rs);
			close(pstmt);
		}
		return list;
	}
	
	//3번째 통계 데이터 
	public List<Statistics> forTimeLine(Connection conn, String busId){
		PreparedStatement pstmt=null;
		ResultSet rs=null;
		String sql=prop.getProperty("forTimeLine");
		List<Statistics>list=new ArrayList<Statistics>();
		try {
			pstmt=conn.prepareStatement(sql);
			pstmt.setString(1, busId);
			rs=pstmt.executeQuery();
			while(rs.next()) {
				Statistics s=new Statistics();
				s.setEmpName(rs.getString(1));
				s.setEmpStart(rs.getString(2));
				s.setEmpEnd(rs.getString(3));
				list.add(s);
			}
		}catch(SQLException e) {
			e.printStackTrace();
		}finally {
			close(rs);
			close(pstmt);
		}
		return list;
	}
	
	//4번째 통계 데이터
	public List<Statistics> forWorkingHour(Connection conn, String busId){
		PreparedStatement pstmt=null;
		ResultSet rs=null;
		String sql=prop.getProperty("forWorkingHour");
		List<Statistics>list=new ArrayList<Statistics>();
		Statistics st= new Statistics();
		List<StatisticsMonth>lists=new ArrayList<StatisticsMonth>();
		try {
			pstmt=conn.prepareStatement(sql);
			pstmt.setString(1, busId);
			rs=pstmt.executeQuery();
			while(rs.next()) {
				StatisticsMonth s=new StatisticsMonth();
				s.setYear(rs.getInt(1));
				s.setJan(rs.getInt(2));
				s.setFeb(rs.getInt(3));
				s.setMar(rs.getInt(4));
				s.setApr(rs.getInt(5));
				s.setMay(rs.getInt(6));
				s.setJun(rs.getInt(7));
				s.setJuly(rs.getInt(8));
				s.setAug(rs.getInt(9));
				s.setSep(rs.getInt(10));
				s.setOct(rs.getInt(11));
				s.setNov(rs.getInt(12));
				s.setDec(rs.getInt(13));
				lists.add(s);
			}
			st.setYears(lists);
			list.add(st);
		}catch(SQLException e) {
			e.printStackTrace();
		}finally {
			close(rs);
			close(pstmt);
		}
		return list;
	}
	
	//5번째 통계 데이터 
	public List<Statistics> forTotalEmp(Connection conn, String busId){
		PreparedStatement pstmt=null;
		ResultSet rs=null;
		String sql=prop.getProperty("forTotalEmp");
		List<Statistics>list=new ArrayList<Statistics>();
		Statistics st= new Statistics();
		List<StatisticsMonth>lists=new ArrayList<StatisticsMonth>();
		try {
			pstmt=conn.prepareStatement(sql);
			pstmt.setString(1, busId);
			rs=pstmt.executeQuery();
			while(rs.next()) {
				StatisticsMonth s=new StatisticsMonth();
				s.setYear(rs.getInt(1));
				s.setJan(rs.getInt(2));
				s.setFeb(rs.getInt(3));
				s.setMar(rs.getInt(4));
				s.setApr(rs.getInt(5));
				s.setMay(rs.getInt(6));
				s.setJun(rs.getInt(7));
				s.setJuly(rs.getInt(8));
				s.setAug(rs.getInt(9));
				s.setSep(rs.getInt(10));
				s.setOct(rs.getInt(11));
				s.setNov(rs.getInt(12));
				s.setDec(rs.getInt(13));
				lists.add(s);
			}
			st.setYears(lists);
			list.add(st);
		}catch(SQLException e) {
			e.printStackTrace();
		}finally {
			close(rs);
			close(pstmt);
		}
		return list;
	}
	
	//6번째 통계 데이터 - 지각
	public List<Statistics> forTotalLate(Connection conn, String busId){
		PreparedStatement pstmt=null;
		ResultSet rs=null;
		String sql=prop.getProperty("forTotalLate");
		List<Statistics>list=new ArrayList<Statistics>();
		Statistics st= new Statistics();
		List<StatisticsMonth>lista=new ArrayList<StatisticsMonth>();
		try {
			pstmt=conn.prepareStatement(sql);
			pstmt.setString(1, busId);
			rs=pstmt.executeQuery();
			while(rs.next()) {
				StatisticsMonth s=new StatisticsMonth();
				s.setYear(rs.getInt(1));
				s.setJan(rs.getInt(2));
				s.setFeb(rs.getInt(3));
				s.setMar(rs.getInt(4));
				s.setApr(rs.getInt(5));
				s.setMay(rs.getInt(6));
				s.setJun(rs.getInt(7));
				s.setJuly(rs.getInt(8));
				s.setAug(rs.getInt(9));
				s.setSep(rs.getInt(10));
				s.setOct(rs.getInt(11));
				s.setNov(rs.getInt(12));
				s.setDec(rs.getInt(13));
				lista.add(s);
			}
			st.setYears(lista);
			list.add(st);
		}catch(SQLException e) {
			e.printStackTrace();
		}finally {
			close(rs);
			close(pstmt);
		}
		return list;
	}
	
	//6번째 통계 데이터 - 조퇴
	public List<Statistics> forTotalEarlyLeave(Connection conn, String busId){
		PreparedStatement pstmt=null;
		ResultSet rs=null;
		String sql=prop.getProperty("forTotalEarlyLeave");
		List<Statistics>list=new ArrayList<Statistics>();
		Statistics st= new Statistics();
		List<StatisticsMonth>liste=new ArrayList<StatisticsMonth>();
		try {
			pstmt=conn.prepareStatement(sql);
			pstmt.setString(1, busId);
			rs=pstmt.executeQuery();
			while(rs.next()) {
				StatisticsMonth s=new StatisticsMonth();
				s.setYear(rs.getInt(1));
				s.setJan(rs.getInt(2));
				s.setFeb(rs.getInt(3));
				s.setMar(rs.getInt(4));
				s.setApr(rs.getInt(5));
				s.setMay(rs.getInt(6));
				s.setJun(rs.getInt(7));
				s.setJuly(rs.getInt(8));
				s.setAug(rs.getInt(9));
				s.setSep(rs.getInt(10));
				s.setOct(rs.getInt(11));
				s.setNov(rs.getInt(12));
				s.setDec(rs.getInt(13));
				liste.add(s);
			}
			st.setYears(liste);
			list.add(st);
		}catch(SQLException e) {
			e.printStackTrace();
		}finally {
			close(rs);
			close(pstmt);
		}
		return list;
	}
	
	//6번째 통계 데이터 - 연장근무
	public List<Statistics> forTotalOverTime(Connection conn, String busId){
		PreparedStatement pstmt=null;
		ResultSet rs=null;
		String sql=prop.getProperty("forTotalOverTime");
		List<Statistics>list=new ArrayList<Statistics>();
		Statistics st= new Statistics();
		List<StatisticsMonth>listp=new ArrayList<StatisticsMonth>();
		try {
			pstmt=conn.prepareStatement(sql);
			pstmt.setString(1, busId);
			rs=pstmt.executeQuery();
			while(rs.next()) {
				StatisticsMonth s=new StatisticsMonth();
				s.setYear(rs.getInt(1));
				s.setJan(rs.getInt(2));
				s.setFeb(rs.getInt(3));
				s.setMar(rs.getInt(4));
				s.setApr(rs.getInt(5));
				s.setMay(rs.getInt(6));
				s.setJun(rs.getInt(7));
				s.setJuly(rs.getInt(8));
				s.setAug(rs.getInt(9));
				s.setSep(rs.getInt(10));
				s.setOct(rs.getInt(11));
				s.setNov(rs.getInt(12));
				s.setDec(rs.getInt(13));
				listp.add(s);
			}
			st.setYears(listp);
			list.add(st);
		}catch(SQLException e) {
			e.printStackTrace();
		}finally {
			close(rs);
			close(pstmt);
		}
		return list;
	}
	
	
	
	
	
}
