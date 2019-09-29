package com.semi.user.controller;

import java.io.IOException;

import javax.mail.Session;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.google.gson.Gson;
import com.semi.user.model.service.UserService;

import common.util.SHA512;

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
		String pw = SHA512.getSHA512(request.getParameter("pw"));
		String quittype = request.getParameter("quittype");
		
		int resultQuit = new UserService().QuitUser(email,pw,quittype);
		
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
