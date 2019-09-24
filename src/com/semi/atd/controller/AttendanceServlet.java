package com.semi.atd.controller;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.semi.atd.model.Service.AttendanceService;
import com.semi.atd.model.vo.Attendance;
import com.semi.bus.model.service.BusinessService;
import com.semi.emp.model.vo.Employee;
import com.semi.sft.model.Service.ShiftService;
import com.semi.sft.model.vo.Shift;
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
		List<Shift> sftList = new ShiftService().getSftList(busId);
		for(Employee e : empList) {
			for(Shift s : sftList) {
				if(e.getSftId()!=null) {
				if(e.getSftId().equals(s.getSftId())) {
					e.setShift(s);
				}
		}
		}
			Attendance atd = new AttendanceService().setAttendance(e, busId);
			if(e.getShift() != null) {
				System.out.println(atd.getAtdOn()+","+ atd.getAtdOff()+","+ e.getShift().getSftOn()+","+ e.getShift().getSftOff());
				atd.setTimeforLong(atd.getAtdOn(), atd.getAtdOff(), e.getShift().getSftOn(), e.getShift().getSftOff());
				e.setAttendance(atd);
				
				
				/// width값 구하는 로직 완성하기
				long stAtd = e.getAttendance().getStAtdTime();
				long enAtd = e.getAttendance().getEnAtdTime();
				long stSft = e.getAttendance().getStSftTime();
				long enSft = e.getAttendance().getEnSftTime();
				
				long widthLate = 0;
				long widthEarly = 0;
				long widthNomal = 0;
				
				if (stSft < stAtd) {
					widthLate = 100 - (((enSft - stSft) - (stAtd - stSft)) / (enSft - stSft)) * 100;
				}
				if (enSft > enAtd) {
					widthEarly = 100 - (((enSft - stSft) - (enSft - enAtd)) / (enSft - stSft)) * 100
							- widthLate;
				}
				widthNomal = 100 - (((enSft - stSft) - (enAtd - stAtd)) / (enSft - stSft)) * 100
						- (widthLate - widthEarly);
				
				System.out.println(enSft - stSft);
				System.out.println(stAtd - stSft);
				System.out.println(enSft - stSft);
				
				System.out.println(stAtd);
				System.out.println(enAtd);
				System.out.println(stSft);
				System.out.println(enSft);
				
				System.out.println(widthLate);
				System.out.println(widthEarly);
				System.out.println(widthNomal);
		request.setAttribute("empList", empList);
		
			}
			
		}

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
