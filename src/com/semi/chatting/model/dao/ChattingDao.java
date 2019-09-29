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
			return list;			
		}
	
	// 채팅 내역 DB에 저장하기
	public int insertChatting(Connection conn,String busId,String userId,String chatType,String chatMsg) {
		PreparedStatement pstmt=null;
		int result=0;
		String sql=prop.getProperty("insertChatting");
		System.out.println(busId);
		System.out.println(userId);
		System.out.println(chatType);
		System.out.println(chatMsg);
		System.out.println(sql);
		try {
			pstmt=conn.prepareStatement(sql);
			pstmt.setString(1, busId);
			pstmt.setString(2, userId);
			pstmt.setString(3, "NULL");
			pstmt.setString(4, chatType);
			pstmt.setString(5, chatMsg);
			result=pstmt.executeUpdate();
		}catch(SQLException e) {
			e.printStackTrace();
		}finally {
			close(pstmt);
		}return result;	
	}

}
