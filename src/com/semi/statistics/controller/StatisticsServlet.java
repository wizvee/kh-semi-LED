package com.semi.statistics.controller;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.semi.statistics.model.service.StatisticsService;
import com.semi.statistics.model.vo.Statistics;
import com.semi.userinfo.model.vo.UserInfo;

/**
 * Servlet implementation class StatisticsServlet
 */
@WebServlet("/owner/statistics.do")
public class StatisticsServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public StatisticsServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
				
		// 해당 owner 의 userId 와 선택한 사업장 busId 값을 불러와서 저장
		UserInfo user = (UserInfo)request.getSession().getAttribute("userInfo");
		String busId = user.getSelectBusId();
		System.out.println("사업장아이디: "+busId);
		System.out.println("값은헬로우같은거 없다구!");
		
		Map<String, List<Statistics>> dataMap = new HashMap<String, List<Statistics>>();
		
		StatisticsService ss=new StatisticsService();
		
		dataMap=ss.getAllData(busId);
	
		
	request.getRequestDispatcher("/views/owner/statistics.jsp").forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
