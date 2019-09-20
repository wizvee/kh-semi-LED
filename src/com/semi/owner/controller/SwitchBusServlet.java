package com.semi.owner.controller;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/owner/switch.do")
public class SwitchBusServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	public SwitchBusServlet() {
		super();
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String busId = request.getParameter("busId");
		request.getSession().setAttribute("selectBusId", busId);
		response.sendRedirect(request.getContextPath() + "/owner/main.do");
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doGet(request, response);
	}

}
