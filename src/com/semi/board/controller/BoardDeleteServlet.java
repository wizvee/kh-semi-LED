package com.semi.board.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.semi.board.model.dao.BoardDao;
import com.semi.board.model.service.BoardService;
import com.semi.board.model.vo.Board;

/**
 * Servlet implementation class BoardDeleteServlet
 */
@WebServlet("/board/delete")
public class BoardDeleteServlet extends HttpServlet {
	
	private BoardDao dao=new BoardDao();
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public BoardDeleteServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//글 삭제 서블릿
		
		
		request.setCharacterEncoding("UTF-8"); //글씨가 깨지지 않도록 인코딩
		
		//글 넘버 받아오기
		String no=request.getParameter("no"); //게시글의 글 번호를 받아옴 board_no
		
		
		
		if(request.getParameter("no")==null) {
			response.sendRedirect(request.getContextPath()+"/views/boardList");
		}else {
			int num=Integer.parseInt(request.getParameter("no")); //형변환
			
			
			Board board = new Board();
            board.setBoardNo(num);
            
            int service=new BoardService().deleteBoard(board); 
            String msg="";
            String loc="";
            String view="/views/emp/msg.jsp";
            if(service==0){
                //response.sendRedirect(request.getContextPath()+"/views/boardList");
            	loc="/views/boardList";
                msg="삭제 실패";
            } else {
                //response.sendRedirect(request.getContextPath()+"/board/delete?num="+num);
            	loc="/board/delete?num="+num;
                msg="삭제 성공";
		}   
            request.setAttribute("msg", msg);
		    request.setAttribute("loc", loc);
		    request.getRequestDispatcher(view)
			.forward(request,response);
            
	}
}


		
		
		//여기까지는 정상적으로 현재 글 번호를 받아오는 중
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		//파일이름 받아오기
//		String fileName=request.getParameter("fileName");
//		System.out.println(fileName);
//		
//		if(fileName != null)
//        {
//            // 파일이 있는 폴더의 절대경로를 가져온다.
//            String folder = request.getServletContext().getRealPath("/upload/board");
//            // 파일의 절대경로를 만든다.
//            String filePath = folder + "/" + fileName;
// 
//            File file = new File(filePath);
//            if(file.exists()) file.delete(); // 파일은 1개만 업로드 되므로 한번만 삭제하면 된다.
//        }
//		
//		request.getRequestDispatcher("/views/boardList.jsp")
//		.forward(request, response);
//      
//
//
//		
//		


	   
	
				
	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
	}
}


