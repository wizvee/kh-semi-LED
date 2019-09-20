package com.semi.emp.controller;

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
import com.semi.emp.model.service.EmpService;
import com.semi.emp.model.vo.Employee;
import com.semi.owner.model.service.OwnerService;
import com.semi.user.model.vo.User;

@WebServlet("/emp/main.do")
public class ForwardEServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	public ForwardEServlet() {
		super();
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		HttpSession session = request.getSession();
		User loginUser = (User) request.getSession().getAttribute("loginUser");
		String url = "";
		
		Employee loginEmp = new EmpService().castingTypeE(loginUser.getUserId());
		ArrayList<Business> busList = new EmpService().getBnsList(loginEmp.getUserId());
		session.setAttribute("loginEmp", loginEmp);
		session.setAttribute("busList", busList);

		if (busList.isEmpty())
			url += "/views/emp/addBus.jsp";
		else {
			Business selectBus = null;
			String selectBusId = (String) session.getAttribute("selectBusId");
			
			if (selectBusId != null)
				selectBus = new BusinessService().selectBusiness(selectBusId);
			else
				selectBus = busList.get(0);
			
			session.setAttribute("selectBus", selectBus);
			url += "/views/emp/main.jsp";
		}

		response.sendRedirect(request.getContextPath() + url);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doGet(request, response);
	}

}
