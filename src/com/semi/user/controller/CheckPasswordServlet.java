package com.semi.user.controller;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.semi.user.model.service.UserService;
import com.semi.user.model.vo.User;

import common.util.SHA512;

/**
 * Servlet implementation class CheckPasswordServlet
 */
@WebServlet("/checkPasswordEnd.do")
public class CheckPasswordServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public CheckPasswordServlet() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		String pw = SHA512.getSHA512(request.getParameter("pw"));
		String userid=request.getParameter("userId");
		
//		System.out.println("비빌번호는?---"+pw);
		UserService service = new UserService();
		User u = service.checkpw(pw);

		
		String loc = "";
		String msg = "";
		String view = "";
//		System.out.println("유저 담기냐?"+infoUser);
//		System.out.println(u); 
		if (u != null) {
			//infoUser 객체 만들어서 request에 담아서 디스페쳐 해라! 
//		System.out.println("유저아이디는?---"+userid);
			User infoUser = service.selectOne(userid);
			request.setAttribute("infoUser", infoUser);
			request.getRequestDispatcher("/views/owner/ownerMyPage.jsp").forward(request, response);
		} else {
			msg = "패스워트가 일치하지 않습니다.";
			view = "/views/common/enroll.jsp";
//			request.setAttribute("msg", msg);
//			request.setAttribute("loc", loc);
			RequestDispatcher rd = request.getRequestDispatcher(view);
			rd.forward(request, response);
		}

	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
