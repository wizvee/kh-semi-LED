package com.semi.bus.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.semi.bus.model.service.BusinessService;

@WebServlet("/onwer/approvalEmp.do")
public class ApprovalEmpServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	public ApprovalEmpServlet() {
		super();
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String busId = request.getParameter("busId");
		String empId = request.getParameter("empId");
		
		int r = new BusinessService().approvalEmp(busId, empId);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doGet(request, response);
	}

}
