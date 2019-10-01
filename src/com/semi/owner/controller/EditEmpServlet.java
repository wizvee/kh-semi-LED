package com.semi.owner.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.semi.bus.model.service.BusinessService;

@WebServlet("/owner/editEmp.do")
public class EditEmpServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	public EditEmpServlet() {
		super();
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String empId = request.getParameter("editEmpId");
		int empWage = Integer.parseInt(request.getParameter("editEmpWage"));
		String empType = request.getParameter("editEmpType");
		String sftId = request.getParameter("editSftId");
		
		int r = new BusinessService().editEmp(empId, empWage, empType, sftId);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doGet(request, response);
	}

}
