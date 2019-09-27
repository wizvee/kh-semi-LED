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
import com.semi.userinfo.model.vo.UserInfo;

@WebServlet("/emp/main.do")
public class ForwardEServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	public ForwardEServlet() {
		super();
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		HttpSession session = request.getSession();
		User loginUser = (User) session.getAttribute("loginUser");

		// webSocket용 userInfo생성 및 불러오기
		UserInfo userInfo = null;
		if ((UserInfo) session.getAttribute("userInfo") != null)
			userInfo = (UserInfo) session.getAttribute("userInfo");
		else {
			userInfo = loginUser.getUserInfo(loginUser.getUserId(), loginUser.getUserType());
			session.removeAttribute("loginUser");
		}

		// redirect용 URL
		String url = "";

		// 기존 개발용 Employee instance 생성
		Employee loginEmp = new EmpService().castingTypeE(userInfo.getUserId());
		session.setAttribute("loginEmp", loginEmp);
		String selectBusId = request.getParameter("selectBus");

		if (userInfo.getBusMap().isEmpty() && selectBusId == null)
			url += "/emp/addBus.do";
		else {
			userInfo.setSelectBusId(selectBusId);
			url += "/views/emp/main.jsp";
		}

		userInfo.getParameters("E");
		userInfo.setFlag("S");
		session.setAttribute("userInfo", userInfo);
		response.sendRedirect(request.getContextPath() + url);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doGet(request, response);
	}

}
