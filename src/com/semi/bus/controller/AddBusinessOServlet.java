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
import com.semi.sft.model.vo.Shift;

@WebServlet("/owner/addBus.do")
public class AddBusinessOServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	public AddBusinessOServlet() {
		super();
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		PrintWriter out = response.getWriter();
		String business = request.getParameter("business");
		String shift = request.getParameter("shift");

		Business bus = new Gson().fromJson(business, Business.class);
		Shift[] sftArr = new Gson().fromJson(shift, Shift[].class);

		String busId = new BusinessService().insertBusiness(bus.getOwnId(), bus, sftArr);

		if (busId != null) {
			request.getSession().setAttribute("selectBusId", busId);
			out.print("success");
		} else
			out.print("fail");
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doGet(request, response);
	}

}
