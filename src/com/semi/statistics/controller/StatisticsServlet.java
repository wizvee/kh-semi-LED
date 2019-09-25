package com.semi.statistics.controller;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

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
		
		String whatsup="hello!";
		System.out.println("값은?:"+whatsup);
		
		UserInfo user = (UserInfo)request.getSession().getAttribute("userInfo");
		String busId = user.getSelectBusId();
		// 해당 owner 의 userId 와 선택한 사업장 busId 값을 불러와서 저장
//		String userId=user.getUserId();
		System.out.println(busId);
		
//		Map<String, Statistics> dataMap = new HashMap<String, Statistics>();
		
	
		
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
