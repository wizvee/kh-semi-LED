package com.semi.owner.controller;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.semi.bus.model.service.BusinessService;
import com.semi.emp.model.vo.Employee;
import com.semi.sft.model.vo.Shift;
import com.semi.userinfo.model.vo.UserInfo;

@WebServlet("/owner/manageEmp.do")
public class manageEmpServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	public manageEmpServlet() {
		super();
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		UserInfo ui = (UserInfo) request.getSession().getAttribute("userInfo");
		ArrayList<Employee> empList = new BusinessService().getEmpList(ui.getSelectBusId());
		ArrayList<Shift> sftList = new BusinessService().getSftList(ui.getSelectBusId());
		request.setAttribute("empList", empList);
		request.setAttribute("sftList", sftList);
		request.getRequestDispatcher("/views/owner/manageEmp.jsp").forward(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doGet(request, response);
	}

}
