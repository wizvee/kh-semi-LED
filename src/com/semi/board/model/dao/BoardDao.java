package com.semi.board.model.dao;

import java.io.FileReader;
import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Properties;

import com.semi.board.model.dao.BoardDao;
import com.semi.board.model.vo.Board;

import static common.template.JDBCTemplate.close;
import static common.template.JDBCTemplate.getConnection;

public class BoardDao {
	
	private Properties prop=new Properties();
	private Connection conn;
	
	
	
	public BoardDao() {
		String path=BoardDao.class.getResource("/sql/board/board-query.properties").getPath(); //쿼리문을 연결하는 경로
		try {
			prop.load(new FileReader(path));//properties 파일을 불러오는 구문
		}catch(IOException e) {
			e.printStackTrace();
		}
}
	
	public int insertBoard(Connection conn,Board b) {
		PreparedStatement pstmt=null; //statement를 상속 받음, sql문을 미리 만들어두고 변수를 따로 입력하는 방식으로 효율성과 유지보수 측면에서 유리
		int result=0; 
		String sql=prop.getProperty("insertBoard"); //insertBoard 라는 쿼리문을 호출
		try {
			pstmt=conn.prepareStatement(sql);
			pstmt.setString(1,b.getBoardTitle());
			pstmt.setString(2, b.getBoardContent());
			pstmt.setString(3, b.getBoardOriginalFilename());
			pstmt.setString(4, b.getBoardRenamedFilename());
			result=pstmt.executeUpdate(); //update나 delete문 수행할때 사용, 반환값은 int로 반환하고 처리된 데이터 수를 반환
		}catch(SQLException e) {
			e.printStackTrace();
		}finally {
			close(pstmt);
		}
		return result;
}
	
	public int selectBoardNo(Connection conn) {
		Statement stmt=null;
		ResultSet rs=null;
		int result=0;
		String sql="select seq_board_no.currval from dual";
		try {
			stmt=conn.createStatement();
			rs=stmt.executeQuery(sql);
			if(rs.next()) {
				result=rs.getInt(1);
				//result=rs.getInt("cnt"); //컬럼인덱스를 사용해서도 가능
				//result=rs.getInt(1);
			}
		}catch(SQLException e) {
			e.printStackTrace();
		}finally {
			close(rs);
			close(stmt);
		}
		return result;
}
	
	public Board selectBoard(Connection conn, int no) {
		PreparedStatement pstmt=null;
		ResultSet rs=null;
		Board b=null;
		String sql=prop.getProperty("selectBoard");
		try {
			pstmt=conn.prepareStatement(sql);
			pstmt.setInt(1, no);
			rs=pstmt.executeQuery();
			if(rs.next()) {
				b=new Board();
				b.setBoardNo(rs.getInt("board_no"));
				b.setBoardTitle(rs.getString("board_title"));
				b.setBoardContent(rs.getString("board_content"));
				b.setBoardDate(rs.getDate("board_date"));
				b.setBoardReadCount(rs.getInt("board_readcount"));
				b.setBoardOriginalFilename(rs.getString("board_original_filename"));
				b.setBoardRenamedFilename(rs.getString("board_renamed_filename"));
			}
			
		}catch(SQLException e) {
			e.printStackTrace();
		}finally {
			close(rs);
			close(pstmt);
		}return b;
		
}
	
	public int updateReadCount(Connection conn, int no) {
		PreparedStatement pstmt=null;
		int result=0;
		String sql=prop.getProperty("updateReadCount");
		try {
			pstmt=conn.prepareStatement(sql);
			pstmt.setInt(1, no);
			result=pstmt.executeUpdate();
		}catch(SQLException e) {
			e.printStackTrace();
		}finally {
			close(pstmt);
		}return result;
}
	
	public List<Board> selectBoardList(Connection conn,int cPage,int numPerPage){
		PreparedStatement pstmt=null;
		ResultSet rs=null; //select문을 사용한 질의 성공시ResultSet를 반환, SQL질의에 의해 생성된 테이블을 담고 있고 커서라는 것을 가지고 특정 행에 대한 참조를 조작
		List<Board> list=new ArrayList();
		String sql=prop.getProperty("selectBoardList");
		try {
			pstmt=conn.prepareStatement(sql);
			pstmt.setInt(1, (cPage-1)*numPerPage+1);
			pstmt.setInt(2, cPage*numPerPage);
			rs=pstmt.executeQuery();
			while(rs.next()) {
				Board b=new Board();
				b.setBoardNo(rs.getInt("board_no"));
				b.setBoardTitle(rs.getString("board_title"));
				b.setBoardDate(rs.getDate("board_date"));
				b.setBoardReadCount(rs.getInt("board_readcount"));
				b.setBoardOriginalFilename(rs.getString("board_original_filename"));
				b.setBoardRenamedFilename(rs.getString("board_renamed_filename"));
				list.add(b); 
			}
	}catch(SQLException e) {
		e.printStackTrace();
	}finally {
		close(rs);
		close(pstmt);
	}return list;
	
}
	public int selectBoardCount(Connection conn) {
		PreparedStatement pstmt=null;
		ResultSet rs=null;
		int result=0;
		String sql=prop.getProperty("selectBoardCount");
		try {
			pstmt=conn.prepareStatement(sql);
			rs=pstmt.executeQuery();
			if(rs.next()) {
				result=rs.getInt(1);
			}
		}catch(SQLException e) {
			e.printStackTrace();
		}finally {
			close(rs);
			close(pstmt);
		}return result;
	}
	
	public List<Board> boardSearchTitle(Connection conn,String title){
		PreparedStatement pstmt=null;
		ResultSet rs=null;
		List<Board> list=new ArrayList();
		String sql=prop.getProperty("boardSearchTitle");
		try {
			pstmt=conn.prepareStatement(sql);
			pstmt.setString(1, title);
			rs=pstmt.executeQuery();
			while(rs.next()) {
				Board b=new Board();
				b.setBoardNo(rs.getInt("board_no"));
				b.setBoardTitle(rs.getString("board_title"));
				b.setBoardDate(rs.getDate("board_date"));
				b.setBoardReadCount(rs.getInt("board_readcount"));
				b.setBoardOriginalFilename(rs.getString("board_original_filename"));
				b.setBoardRenamedFilename(rs.getString("board_renamed_filename"));
				list.add(b);
			}
		}catch(SQLException e) {
			e.printStackTrace();
		}finally {
			close(rs);
			close(pstmt);
		}return list;
	}
	public List<Board> boardSearchContent(Connection conn,String content){
		PreparedStatement pstmt=null;
		ResultSet rs=null;
		List<Board> list=new ArrayList();
		String sql=prop.getProperty("boardSearchContent");
		try {
			pstmt=conn.prepareStatement(sql);
			pstmt.setString(1, content);
			rs=pstmt.executeQuery();
			while(rs.next()) {
				Board b=new Board();
				b.setBoardNo(rs.getInt("board_no"));
				b.setBoardTitle(rs.getString("board_title"));
				b.setBoardDate(rs.getDate("board_date"));
				b.setBoardReadCount(rs.getInt("board_readcount"));
				b.setBoardOriginalFilename(rs.getString("board_original_filename"));
				b.setBoardRenamedFilename(rs.getString("board_renamed_filename"));
				list.add(b);
			}
		}catch(SQLException e) {
			e.printStackTrace();
		}finally {
			close(rs);
			close(pstmt);
		}return list;
	}

	public List<Board> selectSearch(Connection conn,String type, String keyword,int cPage,int numPerPage){
		
		Statement stmt=null;
		ResultSet rs=null;
		List<Board> list=new ArrayList();
		
		int start=(cPage-1)*numPerPage+1;
		int end=cPage*numPerPage;
		
		String sql="select * from (select rownum as rnum, a.* "
				+"from (select * "
				+"from board "
				+" where "+type+" like '%"+keyword+"%')a)"
				+" where rnum between "+start+" and "+end ;
                          try {
			stmt=conn.createStatement();
			rs=stmt.executeQuery(sql);
			while(rs.next()) {
				Board b=new Board();
				b.setBoardNo(rs.getInt("board_no"));
				b.setBoardTitle(rs.getString("board_title"));
				b.setBoardDate(rs.getDate("board_date"));
				b.setBoardReadCount(rs.getInt("board_readCount"));
				b.setBoardOriginalFilename(rs.getString("board_original_filename"));
				b.setBoardRenamedFilename(rs.getString("board_renamed_filename"));
				list.add(b);
			}
		}catch(SQLException e) {
			e.printStackTrace();
		}finally {
			close(rs);
			close(stmt);
		}return list;
	}
	
	public int selectSearchCount(Connection conn,String type,String keyword) {
		
		Statement stmt=null;
		ResultSet rs=null;
		int result=0;
		String sql="";
                          sql="select count(*) from board "+ " where "+type+" like '%"+keyword+"%'";
	try {
		stmt=conn.createStatement();
		rs=stmt.executeQuery(sql);
		if(rs.next()) {
			result=rs.getInt(1); //컬럼인덱스
		}
	}catch(SQLException e) {
		e.printStackTrace();
	}finally {
		close(rs);
		close(stmt);
	}return result;
 }
	
	public int deleteBoard(Connection conn,Board board) {
		int result=0;
		PreparedStatement pstmt=null;
		String sql=prop.getProperty("deleteBoard");
		try {
			pstmt=conn.prepareStatement(sql);
			pstmt.setInt(1, board.getBoardNo());
			result=pstmt.executeUpdate();
		}catch(Exception e) {
			e.printStackTrace();
		}finally {
			close(pstmt);
		}return result;
	}
	
//	public int boardFileDelete(Connection conn,Board board) {
//		int result=0;
//		PreparedStatement pstmt=null;
//		String sql=prop.getProperty("boardFileDelete");
//		try {
//			pstmt=conn.prepareStatement(sql);
//			pstmt.setString(1, board.getBoardRenamedFilename());
//			result=pstmt.executeUpdate();
//		}catch(Exception e) {
//			e.printStackTrace();
//		}finally {
//			close(pstmt);
//		}return result;
//	}
}

