package com.semi.businfo.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.semi.bus.model.vo.Business;
import com.semi.businfo.model.service.BusInfoService;
import com.semi.userinfo.model.vo.UserInfo;

/**
 * Servlet implementation class businfoServlet
 */
@WebServlet("/businfo.do")
public class businfoServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public businfoServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		BusInfoService service=new BusInfoService();
		
		UserInfo user = (UserInfo)request.getSession().getAttribute("userInfo");
		String busId = user.getSelectBusId();
		System.out.println("사업장 이름은 :"+busId);
	
		Business busInfo=service.getBusInfo(busId);
		
		request.setAttribute("busInfo", busInfo);
		request.getRequestDispatcher("/views/common/editBus.jsp").forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
