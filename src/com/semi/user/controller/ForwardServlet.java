package com.semi.user.controller;

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
import com.semi.owner.model.vo.Owner;
import com.semi.user.model.vo.User;

@WebServlet("/main.do")
public class ForwardServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	public ForwardServlet() {
		super();
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		HttpSession session = request.getSession();
		User loginUser = (User) request.getSession().getAttribute("loginUser");
		String url = "";

		if (loginUser == null)
			url += "/logout.do";
		else {
			if (!loginUser.isMailCheck())
				url += "/views/common/emailCheck.jsp";
			else {
				if (loginUser.getUserType() == null)
					url += "/views/common/enroll.jsp";
				else if (loginUser.getUserType().equals("O"))
					url += "/owner/main.do";
				else if (loginUser.getUserType().equals("E")) 
					url += "/emp/main.do";
			}
		}

		response.sendRedirect(request.getContextPath() + url);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doGet(request, response);
	}

}
