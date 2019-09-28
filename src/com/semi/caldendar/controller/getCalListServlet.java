package com.semi.caldendar.controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.semi.caldendar.model.service.CalendarService;
import com.semi.caldendar.model.vo.Cal;
import com.semi.userinfo.model.vo.UserInfo;

@WebServlet("/getCalList.do")
public class getCalListServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	public getCalListServlet() {
		super();
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		PrintWriter out = response.getWriter();
		UserInfo ui = (UserInfo) request.getSession().getAttribute("userInfo");

		ArrayList<Cal> list = new CalendarService().getCalList(ui.getSelectBusId());

		for (Cal c : list)
			System.out.println(c);

		Gson gs = new GsonBuilder().setDateFormat("yyyy-MM-dd").create();
		out.print(gs.toJson(list));
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doGet(request, response);
	}

}
