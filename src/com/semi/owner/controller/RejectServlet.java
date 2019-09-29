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
import com.semi.noti.model.vo.Notification;
import com.semi.userinfo.model.vo.UserInfo;

@WebServlet("/owner/rejectEmp.do")
public class RejectServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	public RejectServlet() {
		super();
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		HttpSession session = request.getSession();
		PrintWriter out = response.getWriter();

		UserInfo ui = (UserInfo) session.getAttribute("userInfo");
		String busId = ui.getSelectBusId();
		String empId = request.getParameter("empId");
		
		Notification n = new Notification();
		n.setUserId(ui.getUserId());
		n.setTargetUserId(empId);
		n.setTargetBusId(busId);
		n.setNotiType("rejectEmp");
		n.setNotiMsg(empId + " 입사 거절");
		n.setNotiUrl("");

		int r = new BusinessService().rejectEmp(busId, empId);

		if (r > 0) {
			Gson gs = new GsonBuilder().setDateFormat("yyyy-MM-dd HH:mm:ss").create();
			ui.setFlag("N");

			session.setAttribute("userInfo", ui);
			out.print(gs.toJson(ui));
		}
		else
			out.print("fail");
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doGet(request, response);
	}

}
