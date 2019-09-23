package com.semi.user.controller;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.semi.user.model.service.UserService;

import common.util.SHA512;

/**
 * Servlet implementation class CheckAjaxinfoPwServlet
 */
@WebServlet("/checkAjaxinfoPw.do")
public class CheckAjaxinfoPwServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public CheckAjaxinfoPwServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		String userId = request.getParameter("userId");
		String pw = SHA512.getSHA512(request.getParameter("pw"));
		String nPw = SHA512.getSHA512(request.getParameter("nPw"));
		boolean able = new UserService().CheckUser(userId, pw) != null ? true : false;
		
		if (able) {
			//현재 비밀번호들 중에 새 비밀번호가 있는지 확인해야됨.
			if(pw==nPw) {
				
			}
		}
		
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
