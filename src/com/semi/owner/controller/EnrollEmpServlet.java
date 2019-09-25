package com.semi.owner.controller;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.semi.bus.model.service.BusinessService;
import com.semi.emp.model.vo.Employee;
import com.semi.noti.model.vo.Notification;
import com.semi.userinfo.model.vo.UserInfo;

@WebServlet("/owner/enrollEmp.do")
public class EnrollEmpServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	public EnrollEmpServlet() {
		super();
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		HttpSession session = request.getSession();
		PrintWriter out = response.getWriter();

		UserInfo ui = (UserInfo) session.getAttribute("userInfo");
		String busId = ui.getSelectBusId();

		Employee e = new Employee();
		e.setUserId(request.getParameter("empId"));
		e.setEmpType(request.getParameter("empType"));
		e.setEmpWage(Integer.parseInt(request.getParameter("empWage")));
		e.setSftId(request.getParameter("sftId"));

		Notification n = new Notification();
		n.setUserId(e.getUserId());
		n.setTargetUserId(ui.getUserId());
		n.setTargetBusId(busId);
		n.setNotiType("approval_Emp");
		n.setNotiMsg("종업원 " + e.getUserId() + " 승인");
		n.setNotiUrl("/owner/manageEmp.do");

		Notification nNoti = new BusinessService().approvalEmp(busId, e, n);

		if (nNoti != null) {
			Gson gs = new GsonBuilder().setDateFormat("yyyy-MM-dd HH:mm:ss").create();
			ui.getNotiList().add(nNoti);
			ui.setFlag("noti");
			
			session.setAttribute("userInfo", ui);
			out.print(gs.toJson(ui));
		} else
			out.print("fail");
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doGet(request, response);
	}

}
