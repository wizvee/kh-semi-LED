package com.semi.emp.controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.gson.Gson;
import com.semi.bus.model.vo.Business;
import com.semi.emp.model.service.EmpService;

@WebServlet("/emp/searchBus.do")
public class SearchBusServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	public SearchBusServlet() {
		super();
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		PrintWriter out = response.getWriter();
		
		String key = request.getParameter("data");
		ArrayList<Business> list = new EmpService().searchBusiness(key);
		
		String result = new Gson().toJson(list);
		out.print(result);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doGet(request, response);
	}

}
