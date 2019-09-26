package com.semi.noti.controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.semi.noti.model.service.NotiService;
import com.semi.noti.model.vo.Notification;
import com.semi.userinfo.model.vo.UserInfo;

@WebServlet("/notiRead.do")
public class NotiReadedServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	public NotiReadedServlet() {
		super();
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		PrintWriter out = response.getWriter();
		HttpSession session = request.getSession();

		String notiId = request.getParameter("notiId");
		int r = new NotiService().isReadNoti(notiId);

		if (r > 0) {
			UserInfo userInfo = (UserInfo) session.getAttribute("userInfo");
			userInfo.getParameters("O");
			ArrayList<Notification> list = userInfo.getNotiList();
			Gson gs = new GsonBuilder().setDateFormat("yyyy-MM-dd HH:mm:ss").create();
			out.print(gs.toJson(list));
		} else
			out.print("fail");

	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doGet(request, response);
	}

}
