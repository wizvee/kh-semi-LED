package com.semi.prm.controller;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.semi.prm.model.Service.PayRollManagementService;
import com.semi.prm.model.vo.PayRollManagement;
import com.semi.userinfo.model.vo.UserInfo;

/**
 * Servlet implementation class PayRollManagementServlet
 */
@WebServlet("/owner/payRollManagement.do")
public class PayRollManagementServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public PayRollManagementServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	
		UserInfo user = (UserInfo)request.getSession().getAttribute("userInfo");
		String busId = user.getSelectBusId();
		String type = "H";
		int length = 0;
			
			List <PayRollManagement> prmList = new PayRollManagementService().makePayRollList(busId, type, length);

			request.setAttribute("list", prmList);
	request.getRequestDispatcher("/views/owner/payRollManagement.jsp").forward(request, response);
	
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
