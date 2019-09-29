package com.semi.chatting.model.dao;

import static common.template.JDBCTemplate.close;

import java.io.FileReader;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

import com.semi.chatting.model.vo.Chatting;

public class ChattingDao {
	
	private Properties prop=new Properties();
	public ChattingDao() {
		String path=ChattingDao.class.getResource("/sql/chatting/chatting-query.properties").getPath();
		try {
			prop.load(new FileReader(path));
		}catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	// 채팅 타입,채팅 내역, 채팅 날짜, 유저 이름, 사진 정보 DB 에서 가지고 오기 
	public List<Chatting>getHistory(Connection conn, String busId){
		PreparedStatement pstmt=null;
		ResultSet rs=null;
		String sql=prop.getProperty("getHistory");
		List<Chatting>list=new ArrayList<Chatting>();
		try {
			pstmt=conn.prepareStatement(sql);
			pstmt.setString(1, busId);
			rs = pstmt.executeQuery();
			System.out.println("불러울 사업장 아이디: "+busId);
			while(rs.next()){
				Chatting c=new Chatting();
				c.setChatType(rs.getString(1));
				c.setChatDate(rs.getDate(2));
				c.setChatMsg(rs.getString(3));
				c.setUserName(rs.getString(4));
				c.setProfilePic(rs.getString(5));
				list.add(c);
			}
			}catch(SQLException e) {
				e.printStackTrace();
			}finally {
				close(rs);
				close(pstmt);
			}
			System.out.println(list);
			return list;			
		}
	
	// 채팅 내역 DB에 저장하기
	public int insertChatting(Connection conn,Chatting c) {
		PreparedStatement pstmt=null;
		int result=0;
		String sql=prop.getProperty("insertChatting");
		System.out.println(c.getBusId());
		System.out.println(c.getUserId());
		System.out.println(c.getChatType());
		System.out.println(c.getChatMsg());
		try {
			pstmt=conn.prepareStatement(sql);
			System.out.println(sql);
			pstmt.setString(1, c.getBusId());
			pstmt.setString(2, c.getUserId());
			pstmt.setString(3, c.getChatType());
			pstmt.setString(4, c.getChatMsg());
			result=pstmt.executeUpdate();
		}catch(SQLException e) {
			e.printStackTrace();
		}finally {
			close(pstmt);
		}return result;	
	}

}
