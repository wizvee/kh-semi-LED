package com.semi.chatting.model.service;

import static common.template.JDBCTemplate.close;
import static common.template.JDBCTemplate.commit;
import static common.template.JDBCTemplate.getConnection;
import static common.template.JDBCTemplate.rollback;

import java.sql.Connection;
import java.util.List;

import com.semi.chatting.model.dao.ChattingDao;
import com.semi.chatting.model.vo.Chatting;

public class ChattingService {
	
	private ChattingDao dao=new ChattingDao();
	
	// 채팅 타입,채팅 내역, 채팅 날짜, 유저 이름, 사진 정보 DB 에서 가지고 오기 
	public List <Chatting>getHistory(String busId){
		Connection conn=getConnection();
		List <Chatting>list=dao.getHistory(conn, busId);
		close(conn);
		return list;
	}
	
	// 채팅 내역 DB에 저장하기
	public int insertChat(String busId,String userId,String chatType,String chatMsg) {
		Connection conn=getConnection();
		int result=dao.insertChatting(conn,busId,userId,chatType,chatMsg);
		if(result>0) {
			commit(conn);
		}else {
			rollback(conn);
		}close(conn);
		return result;
	}
	
	//해당 사업장에 등록된 유저들 모두 불러오기 
	public List<String> getAllUsers(String busId){
		Connection conn=getConnection();
		List<String>list=dao.getAllUsers(conn,busId);
		close(conn);
		return list;
	}
	
	// 시간 있는지 체크하기 
	public int checkTime(String busId, String chatType, String chatMsg) {
		Connection conn=getConnection();
		int result=dao.checkTime(conn,busId,chatType,chatMsg);
		close(conn);
		return result;
	}
	
	public int insertTime(String busId, String userId, String chatType, String chatMsg) {
		Connection conn=getConnection();
		int result=dao.insertTime(conn,busId,userId,chatType,chatMsg);
		close(conn);
		return result;
	}

}
