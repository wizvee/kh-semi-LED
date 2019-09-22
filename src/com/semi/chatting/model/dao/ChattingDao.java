package com.semi.chatting.model.dao;

import static common.template.JDBCTemplate.close;

import java.io.FileReader;
import java.sql.Connection;
import java.sql.Date;
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
	public List<Chatting>getHistory(Connection conn){
		PreparedStatement pstmt=null;
		ResultSet rs=null;
		String sql=prop.getProperty("getHistory");
		List<Chatting>list=new ArrayList<Chatting>();
		try {
			pstmt=conn.prepareStatement(sql);
			rs=pstmt.executeQuery();
			while(rs.next()){
				Chatting c=new Chatting();
				c.setBusId(rs.getString("BUS_ID"));
				c.setChatType(rs.getString("CHAT_TYPE"));
				c.setUserName(rs.getString("CHAT_MSG"));
				c.setChatDate(rs.getDate("CHAT_DATE"));
				c.setUserName(rs.getString("USER_NAME"));
				c.setProfilePic(rs.getString("PROFILE_PIC"));
				list.add(c);
			}
			}catch(SQLException e) {
				e.printStackTrace();
			}finally {
				close(rs);
				close(pstmt);
			}return list;
		}
	
	// 채팅 내역 DB에 저장하기
	public int insertChatting(Connection conn,Chatting c) {
		PreparedStatement pstmt=null;
		int result=0;
		String sql=prop.getProperty("insertChatting");
		try {
			pstmt=conn.prepareStatement(sql);
			pstmt.setString(1, c.getBusId());
			pstmt.setString(2, c.getUserId());
			pstmt.setString(3, c.getTargetUserId());
			pstmt.setString(4, c.getChatType());
			pstmt.setString(5, c.getChatMsg());
			pstmt.setDate(6, (Date) c.getChatDate());
			result=pstmt.executeUpdate();
		}catch(SQLException e) {
			e.printStackTrace();
		}finally {
			close(pstmt);
		}return result;	
	}

}
