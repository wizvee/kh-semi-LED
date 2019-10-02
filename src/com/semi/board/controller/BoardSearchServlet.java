package com.semi.board.controller;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.semi.board.model.service.BoardService;
import com.semi.board.model.vo.Board;

/**
 * Servlet implementation class BoardSearchServlet
 */
@WebServlet("/board/boardSearch")
public class BoardSearchServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public BoardSearchServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//게시글 검색 서블릿 
		
		
		request.setCharacterEncoding("UTF-8");//글씨가 깨지지 않도록 인코딩 처리
		
		
		String type=request.getParameter("searchType");
		String keyword=request.getParameter("searchKeyword");
		
        
        
        int cPage; //현재페이지
		try {
			cPage=Integer.parseInt(request.getParameter("cPage"));
		}catch(NumberFormatException e) {
			cPage=1;
		}
		
		int numPerPage=10; //한테이블에출력되는 데이터 수 
		
		
		
		
		int totaldata=new BoardService().selectSearchCount(type,keyword);  //where절에 걸리는 것들만 찾아내는것
		List<Board> list=new BoardService().selectSearch(cPage,numPerPage,type,keyword);
		request.setAttribute("list", list);
		
		
		
		
		//페이징 처리
		
		String pageBar="";
		int totalpage=(int)Math.ceil((double)totaldata/numPerPage);
		int pageBarSize=5;
	    int pageNo=((cPage-1)/pageBarSize)*pageBarSize+1;
	    int pageEnd=pageNo+pageBarSize-1;
	    
	    //pageBar 소스코드 작성
	     //[이전] 만들기
	     if(pageNo==1) {
	    	 pageBar+="<span>[이전]</span>";
	     }else {
	    	 pageBar+="<a href='"+request.getContextPath()+"/views/emp/boardList?cPage="
	     +(pageNo-1)+"&type="+type+"&keyword="
	    			 +keyword+"'>[이전]</a>";
	     }
	     //페이지 링크 구하기
	     while(!(pageNo>pageEnd||pageNo>totalpage)) {
	    	 if(pageNo==cPage) {
	    		 pageBar+="<span>"+pageNo+"</span>";
	    	 }else {
	    		 pageBar+="<a href='"+request.getContextPath()+"/views/emp/boardList?cPage="+pageNo
	    				 +"&type="+type
	    				 +"&keyword="+keyword+"'>"+pageNo+"</a>";
	    	 }
	    	 pageNo++;
	     }
	     //[다음] 만들기
	     if(pageNo>totalpage) {
	    	 pageBar+="<span>[다음]</span>";
	     }else {
	    	 pageBar+="<a href='"+request.getContextPath()+"/views/emp/boardList?cPage="+pageNo
   				 +"&type="+type
   				 +"&keyword="+keyword+"'>[다음]</a>";
	     }
		
		
	    

		request.setAttribute("pageBar",pageBar);
		request.setAttribute("cPage",cPage);
		request.setAttribute("type", type);
		request.setAttribute("keyword", keyword);
		
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
