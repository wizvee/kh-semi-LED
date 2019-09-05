package com.semi.user.controller;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.semi.user.model.service.EmpService;
import com.semi.user.model.service.OwnerService;
import com.semi.user.model.vo.Employee;
import com.semi.user.model.vo.Owner;
import com.semi.user.model.vo.User;

@WebServlet("/main.do")
public class ForwardServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	public ForwardServlet() {
		super();
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		User loginUser = (User) request.getSession().getAttribute("loginUser");
		String url = "";

		if (loginUser == null)
			url += "/logout.do";
		else {
			if (!loginUser.isMailCheck()) {
				url += "/views/common/emailCheck.jsp";
			} else {
				if (loginUser.getUserType() == null)
					url += "/views/common/enroll.jsp";
				else if (loginUser.getUserType().equals("O")) {
					Owner loginOwner = new OwnerService().castingTypeO(loginUser.getUserId());
					ArrayList<String> bnsList = new OwnerService().getBnsList(loginOwner.getUserId());
					request.getSession().setAttribute("loginOwner", loginOwner);
					request.getSession().setAttribute("bnsList", bnsList);
					if (bnsList.isEmpty())
						url += "/views/owner/addBus.jsp";
					else
						url += "/views/owner/main.jsp";
				} else if (loginUser.getUserType().equals("E")) {
					Employee loginEmp = new EmpService().castingTypeO(loginUser.getUserId());
					request.getSession().setAttribute("loginEmp", loginEmp);
					url += "/views/emp/main.jsp";
				}
			}
		}
		request.getRequestDispatcher(url).forward(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doGet(request, response);
	}

}
