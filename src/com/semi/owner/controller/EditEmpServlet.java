package com.semi.owner.controller;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.semi.bus.model.service.BusinessService;
import com.semi.emp.model.vo.Employee;
import com.semi.noti.model.service.NotiService;
import com.semi.noti.model.vo.Notification;
import com.semi.user.model.service.UserService;
import com.semi.userinfo.model.vo.UserInfo;

@WebServlet("/owner/editEmp.do")
public class EditEmpServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	public EditEmpServlet() {
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
		
		String empName = new UserService().getUserName(e.getUserId());

		Notification n = new Notification();
		n.setUserId(ui.getUserId());
		n.setTargetUserId(e.getUserId());
		n.setTargetBusId(busId);
		n.setNotiType("editEmp");
		n.setNotiMsg(empName + " 정보 변경");
		n.setNotiUrl("emp/viewInfo.do");

		int r = new BusinessService().editEmp(busId, e);
		int r2 = new NotiService().insertNoti(n);

		if (r + r2 > 0)
			out.print("N");
		else
			out.print("fail");

	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doGet(request, response);
	}

}
