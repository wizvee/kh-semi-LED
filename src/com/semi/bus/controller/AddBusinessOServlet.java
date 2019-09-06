package com.semi.bus.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.semi.bus.model.service.BusinessService;
import com.semi.bus.model.vo.Business;

@WebServlet("/owner/addBus.do")
public class AddBusinessOServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	public AddBusinessOServlet() {
		super();
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String ownId = request.getParameter("userId");
		String name = request.getParameter("name");
		String addr = request.getParameter("addr");
		String phone = request.getParameter("phone");
		String bNum = request.getParameter("bNum");

		int r = new BusinessService().insertBusiness(ownId, name, addr, phone, bNum);

		if (r > 0) {

		} else {

		}
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doGet(request, response);
	}

}
