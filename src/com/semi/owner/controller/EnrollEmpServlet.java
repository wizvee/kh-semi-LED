package com.semi.owner.controller;

import static common.template.JDBCTemplate.commit;
import static common.template.JDBCTemplate.rollback;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.semi.bus.model.service.BusinessService;
import com.semi.emp.model.vo.Employee;
import com.semi.userinfo.model.vo.UserInfo;

@WebServlet("/owner/enrollEmp.do")
public class EnrollEmpServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	public EnrollEmpServlet() {
		super();
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		PrintWriter out = response.getWriter();
		UserInfo ui = (UserInfo) request.getSession().getAttribute("userInfo");
		
		Employee e = new Employee();
		e.setUserId(request.getParameter("empId"));
		e.setEmpType(request.getParameter("empType"));
		e.setEmpWage(Integer.parseInt(request.getParameter("empWage")));
		e.setSftId(request.getParameter("sftId"));
		
		String busId = ui.getSelectBusId();
		
		int r = new BusinessService().approvalEmp(busId, e);
		if (r > 0)
			out.print("success");
		else
			out.print("fail");
		
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doGet(request, response);
	}

}
