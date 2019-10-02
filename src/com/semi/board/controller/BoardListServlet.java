package com.semi.board.controller;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.semi.board.model.service.BoardService;
import com.semi.board.model.vo.Board;

/**
 * Servlet implementation class BoardViewServlet
 */
@WebServlet("/views/boardList")
public class BoardListServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public BoardListServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		//글 목록 페이지 서블릿
		
		request.setCharacterEncoding("UTF-8");
		

		int cPage;
		try {
			cPage=Integer.parseInt(request.getParameter("cPage"));
		}catch(NumberFormatException e) {
			cPage=1;
		}
		int numPerPage=10;
		
		BoardService service=new BoardService();
		int totalData=service.selectBoardCount();
		List<Board> list=service.selectBoardList(cPage,numPerPage);
		
		//페이지 바 만드는 설정 
		int totalPage=(int)Math.ceil((double)totalData/numPerPage); 
		//Math.ceil 소수점 이하를 올림
		String pageBar="";
		int pageBarSize=5;
		int pageNo=((cPage-1)/pageBarSize)*pageBarSize+1;
		int pageEnd=pageNo+pageBarSize-1;
		
		//페이지 바 만들기 
		//이전 
		
		if(pageNo==1) {
	    	 pageBar+="<span>[이전]</span>";
	     }else {
	    	 pageBar+="<a href='"+request.getContextPath()+"/views/emp/boardList?cPage="+(pageNo-1)+"'>[이전]</a>";
	     }
	     
		//페이지 숫자 출력
	     while(!(pageNo>pageEnd||pageNo>totalPage)) {
	    	 if(pageNo==cPage) {
	    		 pageBar+="<span>"+pageNo+"</span>";
	    	 }else {
	    		 pageBar+="<a href='"+request.getContextPath()+"/views/emp/boardList?cPage="+pageNo+"'>"+pageNo+"</a>";
	    	 }
	    	 pageNo++;
	     }
	     //[다음] 만들기
	     if(pageNo>totalPage) {
	    	 pageBar+="<span>[다음]</span>";
	     }else {
	    	 pageBar+="<a href='"+request.getContextPath()+"/views/emp/boardList?cPage="+pageNo+"'>[다음]</a>";
	     }
		
	    
		request.setAttribute("list",list);
		request.setAttribute("pageBar",pageBar);
		request.setAttribute("cPage",cPage);
		
		
		
		
		
		
		request.getRequestDispatcher("/views/emp/boardList.jsp").forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
