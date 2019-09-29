package com.semi.atd.controller;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.xml.ws.ResponseWrapper;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

import com.semi.atd.model.Service.AttendanceService;
import com.semi.atd.model.vo.Attendance;
import com.semi.bus.model.service.BusinessService;
import com.semi.emp.model.vo.Employee;
import com.semi.userinfo.model.vo.UserInfo;

/**
 * Servlet implementation class AttendanceAjaxServlet
 */
@WebServlet("/ajaxAtd.do")
public class AttendanceAjaxServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public AttendanceAjaxServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession session = request.getSession();
		List <Attendance> atdList = new ArrayList<Attendance>();
		UserInfo ui = (UserInfo) session.getAttribute("userInfo");
		String busId = ui.getSelectBusId();
		List<Employee> empList = new BusinessService().getEmpList(busId);
		String[] day = request.getParameter("date").split("-");
		String date = "";
		for(int i = 0; i < day.length; i ++) {
			date += day[i];
		}
		
		atdList = new AttendanceService().setAttendanceList(date, busId);
		JSONArray jarr = new JSONArray();

		if(!atdList.isEmpty()) {
			for(Employee e : empList) {
			for(Attendance a : atdList) {
				if(e.getUserId().equals(a.getEmpId())) {
					
			
				if(a.getAtdOn() != null) {
				if(a.getAtdOff() == null) {
					a.setTimeforLong(a.getAtdOn(), a.getAtdOn(), a.getSftOn(), a.getSftOff());
				}else {
					a.setTimeforLong(a.getAtdOn(), a.getAtdOff(), a.getSftOn(), a.getSftOff());
				}
			JSONObject json = new JSONObject();
			json.put("name", e.getUserName());
			json.put("level", e.getEmpLevel());
			json.put("pic", e.getProfilePic());
			json.put("sftId", a.getSftId());
			json.put("sftName", a.getSftName());
			json.put("sftOn", a.getStSftTime());
			json.put("sftOff", a.getEnSftTime());
			json.put("sAtd", a.getAtdOn());
			json.put("nAtd", a.getAtdOff());
			json.put("sSft", a.getSftOn());
			json.put("nSft", a.getSftOff());
			json.put("atdOn", a.getStAtdTime());
			json.put("atdOff", a.getEnAtdTime());
			json.put("atdType", a.getAtdType());
//			 data[i]["name"]
					

			jarr.add(json);
				}
		}
		}
		}
		}else {
			// 리스트 값이 없을때의 예외처리 로직
		}
		response.setContentType("application/x-json); charset-UTF-8");
		response.getWriter().print(jarr);

		}
	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
