package com.semi.board.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.tomcat.util.http.fileupload.servlet.ServletFileUpload;

import com.oreilly.servlet.MultipartRequest;
import com.semi.board.model.service.BoardService;
import com.semi.board.model.vo.Board;
import com.semi.policy.MyFileRenamePolicy;

/**
 * Servlet implementation class BoardWriteRealServlet
 */
@WebServlet("/board/writereal")
public class BoardWriteRealServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public BoardWriteRealServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//글쓰기 페이지 서블릿
		//첨부파일 업로드 서블릿
		
		
		
		request.setCharacterEncoding("UTF-8"); //글씨가 깨지지 않도록 인코딩
		
		if(!ServletFileUpload.isMultipartContent(request)) {
			request.setAttribute("msg", "잘못된 요청입니다. -관리자문의-");
			request.setAttribute("loc","/boardCreate");
			request.getRequestDispatcher("/views/emp/msg.jsp")
			.forward(request, response);
		}
		
		String saveDir=getServletContext().getRealPath("/");
		saveDir+="/upload/board";
		
		int maxSize=1024*1024*1024;//1GB
		
		MultipartRequest mr=new MultipartRequest(
					request,saveDir,maxSize,"UTF-8",
					new MyFileRenamePolicy() //파일 이름을 결정해줌
				);
		
		String title=mr.getParameter("title");
		String content=mr.getParameter("content");	
		String oriName=mr.getOriginalFileName("up_file"); //첨부된 파일 확인 
		String reName=mr.getFilesystemName("up_file"); //파일 경로 확인
		
		
		
		Board b=new Board(); 
		b.setBoardTitle(title);
		b.setBoardContent(content);
		b.setBoardOriginalFilename(oriName);
		b.setBoardRenamedFilename(reName);
		
		
		int result=new BoardService().insertBoard(b);
		
		
		
		
		
		String msg="";
		String loc="";
		String view="/views/emp/msg.jsp";
		if(result>0) {
			loc="/views/boardList?no="+result;
			msg="게시글 등록성공!";
		}else {
			loc="/boardCreate";
			msg="게시글등록실패!";
		}
		request.setAttribute("msg", msg);
		request.setAttribute("loc", loc);
		request.getRequestDispatcher(view)
		.forward(request,response);
		
		
		
		//2019 09 18 현재 문제
		//1. 글목록에서 한글 깨짐  ->09 19 해결
		//2. 글내용 보려고 클릭시에 500 에러-> 09 19 해결 게시글 읽기 가능
		//3. 글쓰기 등록하면 404 에러  -> 09 19 해결 글쓰기 등록후 글 목록 페이지로 이동 가능
		
		
		//---------------------------------------------------------
		
		//2019 09 22 현재 기능 구현 목표
		//1. 사진 미리보기 //2019 09 24 해결

		//2. 사진 게시글에 보이게 하기  //크흠

		//3. 첨부파일다운로드 가능하게 하기  //해결 2019 09 25 (혼나야 하는갑다)

		//4. 글 제목이나 내용의 단어로 검색 //2019 09 23 해결(공부 많이 필요함)
		
		//“API(Application Programming Interface, 응용 프로그램 프로그래밍 인터페이스)는 응용 프로그램에서 사용할 수 있도록,
		//운영 체제나 프로그래밍 언어가 제공하는 기능을 제어할 수 있게 만든 인터페이스를 뜻한다.”
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
