package com.semi.emp.controller;

import java.io.File;
import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.semi.emp.model.vo.Employee;
import com.semi.owner.model.vo.Owner;
import com.semi.user.model.service.UserService;

/**
 * Servlet implementation class ChangeAjaxinfoOriginloadServlet
 */
@WebServlet("/ajaxemporiproFile.do")
public class ChangeAjaxinfoEmpOriginloadServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ChangeAjaxinfoEmpOriginloadServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		String userId = request.getParameter("userId");
		Employee e = (Employee) request.getSession().getAttribute("loginEmp");
		
		String path=getServletContext().getRealPath("/upload/profile");
		String old = e.getProfilePic();
		
		if(!e.getProfilePic().equals("emp_default.png")) {
			File deleteFile=new File(path+"/"+old);
			boolean result=deleteFile.delete();
		}
		
		
		int result = new UserService().EmporiginPic(userId);
		
		if(result > 0) {
			e.setProfilePic("emp_default.png");
			request.getSession().setAttribute("loginEmp", e);
		} else {
			return;
		}
		
		
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
