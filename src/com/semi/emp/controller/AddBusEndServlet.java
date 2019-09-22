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

@WebServlet("/emp/enrollBus.do")
public class AddBusEndServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	public AddBusEndServlet() {
		super();
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		PrintWriter out = response.getWriter();
		
		String userId = request.getParameter("userId");
		String targetUserId = request.getParameter("ownId");
		String targetBusId = request.getParameter("busId");
		String notiType = "enroll_Business";
		String notiMsg = "근무신청(미정)";
		String notiUrl = "/owner/manageEmp.do";
		
		Notification n = new Notification();
		n.setUserId(userId);
		n.setTargetUserId(targetUserId);
		n.setTargetBusId(targetBusId);
		n.setNotiType(notiType);
		n.setNotiMsg(notiMsg);
		n.setNotiUrl(notiUrl);
		
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
