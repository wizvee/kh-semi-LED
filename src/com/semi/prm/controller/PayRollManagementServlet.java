package com.semi.prm.controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

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
	
		HttpSession session = request.getSession();

		UserInfo ui = (UserInfo) session.getAttribute("userInfo");
		String busId = ui.getSelectBusId();
		String type = "H";
		int length = 0;
			
			List <PayRollManagement> monList1 = new PayRollManagementService().makePayRollList(busId, "H", 0);
			List <PayRollManagement> monList3 = new PayRollManagementService().makePayRollList(busId, "H", 3);
			List <PayRollManagement> monList6 = new PayRollManagementService().makePayRollList(busId, "H", 6);
			List <PayRollManagement> yList = new PayRollManagementService().makePayRollList(busId, "H", 12);

			List <PayRollManagement> monList1D = new PayRollManagementService().makePayRollList(busId, "D", 0);
			List <PayRollManagement> monList3D = new PayRollManagementService().makePayRollList(busId, "D", 3);
			List <PayRollManagement> monList6D = new PayRollManagementService().makePayRollList(busId, "D", 6);
			List <PayRollManagement> yListD = new PayRollManagementService().makePayRollList(busId, "D", 12);

			List <PayRollManagement> monList1M = new PayRollManagementService().makePayRollList(busId, "M", 0);
			List <PayRollManagement> monList3M = new PayRollManagementService().makePayRollList(busId, "M", 3);
			List <PayRollManagement> monList6M = new PayRollManagementService().makePayRollList(busId, "M", 6);
			List <PayRollManagement> yListM = new PayRollManagementService().makePayRollList(busId, "M", 12);

			request.setAttribute("HmonList1", monList1);
			request.setAttribute("HmonList3", monList3);
			request.setAttribute("HmonList6", monList6);
			request.setAttribute("HyList1", yList);

			request.setAttribute("DmonList1", monList1D);
			request.setAttribute("DmonList3", monList3D);
			request.setAttribute("DmonList6", monList6D);
			request.setAttribute("DyList1", yListD);

			request.setAttribute("MmonList1", monList1M);
			request.setAttribute("MmonList3", monList3M);
			request.setAttribute("MmonList6", monList6M);
			request.setAttribute("MyList1", yListM);

			
			
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
