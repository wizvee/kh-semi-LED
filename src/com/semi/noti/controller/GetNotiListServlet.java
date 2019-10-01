package com.semi.noti.controller;

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
import com.semi.noti.model.vo.Notification;
import com.semi.user.model.service.UserService;
import com.semi.userinfo.model.vo.UserInfo;

@WebServlet("/getNotiList.do")
public class GetNotiListServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	public GetNotiListServlet() {
		super();
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		PrintWriter out = response.getWriter();
		UserInfo ui = (UserInfo) request.getSession().getAttribute("userInfo");

		ArrayList<Notification> list = new UserService().getNotiList(ui.getUserId());

		Gson gs = new GsonBuilder().setDateFormat("yyyy-MM-dd").create();
		out.print(gs.toJson(list));
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doGet(request, response);
	}

}
