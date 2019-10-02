package com.semi.board.model.vo;

import java.sql.Date;

public class Board {
	
	private int boardNo; //게시판 게시글 번호
	private String boardTitle; //글 제목
	private String boardContent; //글 내용
	private Date boardDate; //게시글 등록일
	private int boardReadCount; //게시글 조회수
	private String boardOriginalFilename; //첨부파일 이름
	private String boardRenamedFilename; //첨부파일 경로
	
	
	
	
	

	public Board() {}
	
	public Board(int boardNo,String boardTitle,String boardContent,Date boardDate,int boardReadCount,String boardOriginalFilename,
			String boardRenamedFilename) {
		
		this.boardNo=boardNo;
		this.boardTitle=boardTitle;
		this.boardContent=boardContent;
		this.boardDate=boardDate;
		this.boardReadCount=boardReadCount;
		this.boardOriginalFilename=boardOriginalFilename;
		this.boardRenamedFilename=boardRenamedFilename;
		
		
	}

	@Override
	public String toString() {
		return "Board [boardNo=" + boardNo + ", boardTitle=" + boardTitle + ", boardContent=" + boardContent
				+ ", boardDate=" + boardDate + ", boardReadCount=" + boardReadCount + ", boardOriginalFilename="
				+ boardOriginalFilename + ", boardRenamedFilename=" + boardRenamedFilename +"]";
	}

	public String getBoardOriginalFilename() {
		return boardOriginalFilename;
	}

	public void setBoardOriginalFilename(String boardOriginalFilename) {
		this.boardOriginalFilename = boardOriginalFilename;
	}

	public String getBoardRenamedFilename() {
		return boardRenamedFilename;
	}

	public void setBoardRenamedFilename(String boardRenamedFilename) {
		this.boardRenamedFilename = boardRenamedFilename;
	}

	public int getBoardNo() {
		return boardNo;
	}

	public void setBoardNo(int boardNo) {
		this.boardNo = boardNo;
	}

	public String getBoardTitle() {
		return boardTitle;
	}

	public void setBoardTitle(String boardTitle) {
		this.boardTitle = boardTitle;
	}

	public String getBoardContent() {
		return boardContent;
	}

	public void setBoardContent(String boardContent) {
		this.boardContent = boardContent;
	}

	public Date getBoardDate() {
		return boardDate;
	}

	public void setBoardDate(Date boardDate) {
		this.boardDate = boardDate;
	}

	public int getBoardReadCount() {
		return boardReadCount;
	}

	public void setBoardReadCount(int boardReadCount) {
		this.boardReadCount = boardReadCount;
	}

	
	

}
