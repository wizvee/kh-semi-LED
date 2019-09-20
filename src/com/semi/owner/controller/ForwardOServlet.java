package com.semi.owner.controller;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.semi.bus.model.service.BusinessService;
import com.semi.bus.model.vo.Business;
import com.semi.owner.model.service.OwnerService;
import com.semi.owner.model.vo.Owner;
import com.semi.user.model.vo.User;

@WebServlet("/owner/main.do")
public class ForwardOServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	public ForwardOServlet() {
		super();
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		HttpSession session = request.getSession();
		User loginUser = (User) request.getSession().getAttribute("loginUser");
		String url = "";

		Owner loginOwner = new OwnerService().castingTypeO(loginUser.getUserId());
		ArrayList<Business> busList = new OwnerService().getBusList(loginOwner.getUserId());
		session.setAttribute("loginOwner", loginOwner);
		session.setAttribute("busList", busList);

		if (busList.isEmpty())
			url += "/owner/insertBus.do";
		else {
			Business selectBus = null;
			String selectBusId = (String) session.getAttribute("selectBusId");
			
			if (selectBusId != null)
				selectBus = new BusinessService().selectBusiness(selectBusId);
			else
				selectBus = busList.get(0);
			
			session.setAttribute("selectBus", selectBus);
			url += "/views/owner/main.jsp";
		}

		response.sendRedirect(request.getContextPath() + url);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doGet(request, response);
	}

}
