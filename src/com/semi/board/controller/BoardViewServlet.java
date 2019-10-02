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
 * Servlet implementation class BoardListServlet
 */
@WebServlet("/views/boardView")
public class BoardViewServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public BoardViewServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		//글 세부내용 페이지 서블릿
		
		request.setCharacterEncoding("UTF-8");
		
		
		int no=Integer.parseInt(request.getParameter("no"));
		int cPage;
		try {
			cPage=Integer.parseInt(request.getParameter("cPage"));
		}catch(NumberFormatException e) {
			cPage=1;
		}
		
		
		//쿠키값확인하기
		Cookie[] cookies=request.getCookies();//요청 정보로부터 쿠키를 가져옴
		String boardCookieVal="";
		boolean hasRead=false;//읽었는지 안읽었는지 구분하는 기준
		
		if(cookies!=null) {
			for(Cookie c : cookies) {
				String name=c.getName();//key
				String value=c.getValue();//value
				if("boardCookie".equals(name)) {
					boardCookieVal=value;//이전값 보관
					if(value.contains("|"+no+"|")) {
						hasRead=true;
						break;
					}
				}
			}
		}
		//안읽었을때 조회수를 추가하고 cookie에 
		//현재boardNo기록
		if(!hasRead) {
			Cookie c=new Cookie("boardCookie",boardCookieVal+"|"+no+"|");
			c.setMaxAge(-1);//브라우저가 close|로그아웃했을때 삭제
			response.addCookie(c);
		}
				
		
		
		Board b=new BoardService().selectBoard(no,hasRead);
		
		
		request.setAttribute("board",b);
		request.setAttribute("cPage", cPage);
		request.getRequestDispatcher("/views/emp/boardView.jsp")
		.forward(request, response);
		
		
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
