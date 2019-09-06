package com.semi.bus.controller;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.semi.bus.model.service.BusinessService;

@WebServlet("/owner/checkBusNum.do")
public class CheckBusNumServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	public CheckBusNumServlet() {
		super();
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		PrintWriter out = response.getWriter();

		String bNum = request.getParameter("bNum");
		boolean unable = new BusinessService().checkBusNum(bNum);
		if (unable)
			out.print("unable");
		else
			out.print("able");
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doGet(request, response);
	}

}
