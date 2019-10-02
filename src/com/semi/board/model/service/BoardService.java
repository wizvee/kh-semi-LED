package com.semi.board.model.service;

import static common.template.JDBCTemplate.commit;
import static common.template.JDBCTemplate.getConnection;
import static common.template.JDBCTemplate.rollback;
import static common.template.JDBCTemplate.close;

import java.sql.Connection;
import java.util.List;

import com.semi.board.model.dao.BoardDao;
import com.semi.board.model.vo.Board;


public class BoardService {
	
	private BoardDao dao=new BoardDao();
	
	public int insertBoard(Board b) {
		Connection conn=getConnection();
		int result=dao.insertBoard(conn,b);
		if(result>0) {
			result=dao.selectBoardNo(conn);
			commit(conn);
		}
		else {
			rollback(conn);}
			
		close(conn);
		return result;
	}
	
	public Board selectBoard(int no, boolean hasRead) {
		Connection conn=getConnection();
		Board b=dao.selectBoard(conn,no);
		if(!hasRead && b!=null) {
			int result=dao.updateReadCount(conn,no);
			if(result>0) {commit(conn);}
			else {rollback(conn);}
		}
		
		close(conn);
		return b;
	}
	
	public int selectBoardCount() {
		Connection conn=getConnection();
		int result=dao.selectBoardCount(conn);
		close(conn);
		return result;
	}
	
	public List<Board> selectBoardList(int cPage,int numPerPage){
		Connection conn=getConnection();
		List<Board> result=dao.selectBoardList(conn,cPage,numPerPage);
		close(conn);
		return result;

	}
	
	public List<Board> boardSearchTitle(String title){
		Connection conn=getConnection();
		List<Board> result=dao.boardSearchTitle(conn,title);
		close(conn);
		return result;
	}
	
	public List<Board> boardSearchContent(String content){
		Connection conn=getConnection();
		List<Board> result=dao.boardSearchContent(conn,content);
		close(conn);
		return result;
	}
	
	public List<Board> selectSearch(int cPage,int numPerPage,String type,String keyword){
			Connection conn=getConnection();
			List<Board> list=dao.selectSearch(conn,type,keyword,cPage,numPerPage);
			close(conn);
			return list;
	}
		
	public int selectSearchCount(String type,String keyword) {
			Connection conn=getConnection();
			int result=dao.selectSearchCount(conn,type,keyword);
			close(conn);
			return result;
	}
    public int deleteBoard(Board board) {
    	Connection conn=getConnection();
    	int result=dao.deleteBoard(conn,board);
    	close(conn);
    	return result;
    }
//    public int boardFileDelete(Board board) {
//    	Connection conn=getConnection();
//    	int result=dao.boardFileDelete(conn,board);
//    	close(conn);
//    	return result;
//    }
//	
}


