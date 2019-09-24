package com.semi.emp.controller;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.semi.emp.model.service.EmpService;
import com.semi.noti.model.vo.Notification;
import com.semi.userinfo.model.vo.UserInfo;

@WebServlet("/emp/enrollBus.do")
public class AddBusEndServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	public AddBusEndServlet() {
		super();
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		PrintWriter out = response.getWriter();
		
		String userId = ((UserInfo) request.getSession().getAttribute("userInfo")).getUserId();
		
		Notification n = new Notification();
		n.setUserId(userId);
		n.setTargetUserId(request.getParameter("ownId"));
		n.setTargetBusId(request.getParameter("busId"));
		n.setNotiType("enroll_Business");
		n.setNotiMsg("근무신청(미정)");
		n.setNotiUrl("/owner/manageEmp.do");
		
		int r = new EmpService().submitEnrollBus(n);
		
		if (r > 0) 
			out.print("sucess");
		 else
			out.print("fail");
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doGet(request, response);
	}

}
