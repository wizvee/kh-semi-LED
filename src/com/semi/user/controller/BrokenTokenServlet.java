package com.semi.user.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.semi.user.model.service.UserService;

@WebServlet("/token.do")
public class BrokenTokenServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	public BrokenTokenServlet() {
		super();
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String token = request.getParameter("token");
		String email = request.getParameter("email");
		int r = new UserService().brokenToken(token, email);

		boolean broken = false;
		if (r > 1)
			broken = true;

		request.setAttribute("broken", broken);
		request.getRequestDispatcher("views/common/emailCheck.jsp").forward(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doGet(request, response);
	}

}
