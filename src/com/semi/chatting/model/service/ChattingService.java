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
	public List <Chatting>getHistory(){
		Connection conn=getConnection();
		List <Chatting>list=dao.getHistory(conn);
		close(conn);
		return list;
	}
	
	// 채팅 내역 DB에 저장하기
	public int insertChat(Chatting c) {
		Connection conn=getConnection();
		int result=dao.insertChatting(conn,c);
		if(result>0) {
			commit(conn);
		}else {
			rollback(conn);
		}close(conn);
		return result;
	}

}
