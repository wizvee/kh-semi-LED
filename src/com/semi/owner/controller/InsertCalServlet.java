package com.semi.owner.controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.semi.caldendar.model.service.CalendarService;
import com.semi.caldendar.model.vo.Cal;
import com.semi.task.model.vo.Task;
import com.semi.userinfo.model.vo.UserInfo;

@WebServlet("/owner/insertCal.do")
public class InsertCalServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	public InsertCalServlet() {
		super();
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		HttpSession session = request.getSession();
		PrintWriter out = response.getWriter();

		UserInfo ui = (UserInfo) session.getAttribute("userInfo");
		Cal cal = new Cal();

		String from = request.getParameter("calDate");
		SimpleDateFormat sf = new SimpleDateFormat("yyyy-MM-dd");
		Date calDate = null;
		try {
			calDate = sf.parse(from);
		} catch (ParseException e1) {
			e1.printStackTrace();
		}

		cal.setCalDate(calDate);
		cal.setBusId(ui.getSelectBusId());
		cal.setSftId(request.getParameter("sftId"));
		cal.setCalTitle(request.getParameter("calTitle"));
		cal.setCalDetail(request.getParameter("calDetail"));
		
		Gson gs = new GsonBuilder().setDateFormat("yyyy-MM-dd").create();
		
		String task = request.getParameter("taskArr");
		Task[] taskArr = gs.fromJson(task, Task[].class);

//		알림 추가

		int r = new CalendarService().insertCal(cal, taskArr);

		if (r > 0) {
			Gson gs1 = new GsonBuilder().setDateFormat("yyyy-MM-dd HH:mm:ss").create();
			ui.setFlag("N");

			session.setAttribute("userInfo", ui);
			out.print(gs1.toJson(ui));
		} else
			out.print("fail");
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doGet(request, response);
	}

}
