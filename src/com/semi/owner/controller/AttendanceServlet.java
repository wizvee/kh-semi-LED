package com.semi.owner.controller;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.semi.bus.model.service.BusinessService;
import com.semi.emp.model.vo.Employee;
import com.semi.sft.model.Service.ShiftService;
import com.semi.sft.model.vo.Shift;
import com.semi.user.model.vo.User;
import com.semi.userinfo.model.vo.UserInfo;

/**
 * Servlet implementation class AttendanceServlet
 */
@WebServlet("/views/attendance.jsp")
public class AttendanceServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public AttendanceServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub

		UserInfo user = (UserInfo)request.getSession().getAttribute("userInfo");
		String busId = user.getSelectBusId();
		
		List<Employee> empList = new BusinessService().getEmpList(busId);
		List<Employee> list = new ShiftService().addShiftForEmpList(busId, empList);
//		for(Employee e : empList) {
//			for(Shift s : sftList) {
//				if(e.getSftId()!=null) {
//				if(e.getSftId().equals(s.getSftId())) {
//					e.setShift(s);
//				}
//		}
//		}
//		}
//		List<String> atdList = new ShiftService().getAtdList(busId);
//		String atdOn
//		String atdOff
		
		request.setAttribute("empList", list);

		request.getRequestDispatcher("/views/owner/attendance.jsp").forward(request, response);

	
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}