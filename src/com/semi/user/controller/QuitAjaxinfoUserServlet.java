package com.semi.user.controller;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.gson.Gson;
import com.semi.user.model.service.UserService;

/**
 * Servlet implementation class QuitAjaxinfoUserServlet
 */
@WebServlet("/quitAjaxinfoUser.do")
public class QuitAjaxinfoUserServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public QuitAjaxinfoUserServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		String email = request.getParameter("email");
		String pw = request.getParameter("pw");
		String quittype = request.getParameter("quittype");
		
		int resultQuit = new UserService().QuitUser(email,pw,quittype);
		System.out.println("회원탈퇴 가능?");
		System.out.println(resultQuit);
		boolean flag=resultQuit>0?true:false;
		
		response.setContentType("application/json;charset=utf-8");
		new Gson().toJson(flag,response.getWriter());
		
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
