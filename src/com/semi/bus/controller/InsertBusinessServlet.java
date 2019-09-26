package com.semi.bus.controller;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.gson.Gson;
import com.semi.bus.model.service.BusinessService;
import com.semi.bus.model.vo.Business;
import com.semi.noti.model.service.NotiService;
import com.semi.noti.model.vo.Notification;
import com.semi.sft.model.vo.Shift;
import com.semi.userinfo.model.vo.UserInfo;

@WebServlet("/insertBus.do")
public class InsertBusinessServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	public InsertBusinessServlet() {
		super();
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		PrintWriter out = response.getWriter();
		UserInfo ui = (UserInfo) request.getSession().getAttribute("userInfo");

		String business = request.getParameter("business");
		String shift = request.getParameter("shift");

		Business bus = new Gson().fromJson(business, Business.class);
		Shift[] sftArr = new Gson().fromJson(shift, Shift[].class);

		String busId = new BusinessService().insertBusiness(bus.getOwnId(), bus, sftArr);

		Notification n = new Notification();
		n.setUserId(ui.getUserId());
		n.setTargetUserId(ui.getUserId());
		n.setTargetBusId(busId);
		n.setNotiType("insertBus");
		n.setNotiMsg("<b>" + bus.getBusName() + "</b> 사업장 등록");
		n.setNotiUrl("owner/editBus.do");

		if (busId != null) {
			int r = new NotiService().insertNoti(n);
			if (r > 0)
				out.print(busId);
			else
				out.print("fail");
		} else
			out.print("fail");
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doGet(request, response);
	}

}
