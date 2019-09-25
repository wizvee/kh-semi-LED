package com.semi.user.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.semi.user.model.service.UserService;
import com.semi.user.model.vo.User;

@WebServlet("/userType.do")
public class UserTypeServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	public UserTypeServlet() {
		super();
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String userId = request.getParameter("userId");
		String type = request.getParameter("type");
		String url = type.equals("O") ? "own_default.png" : "emp_default.png";
		
		int r = new UserService().setUserType(userId, type, url);
		
		if(r > 0) {
			User loginUser = (User) request.getSession().getAttribute("loginUser");
			loginUser = new UserService().selectUser(loginUser.getEmail());
			request.getSession().setAttribute("loginUser", loginUser);
		}
		
		request.getRequestDispatcher("/main.do").forward(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doGet(request, response);
	}

}
