package com.semi.snslogin.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.semi.user.model.service.UserService;
import com.semi.user.model.vo.User;

import common.util.SHA512;

/**
 * Servlet implementation class SnsLoginEndServlet
 */
@WebServlet("/snsLoginEndServlet")
public class SnsLoginEndServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public SnsLoginEndServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		
		String email=request.getParameter("email");
		String pass=request.getParameter("password");
		String imageUrl=request.getParameter("imageUrl");
		String name=request.getParameter("name");
		String phone=request.getParameter("phone");
		String password = SHA512.getSHA512(pass);
		UserService service=new UserService();
		try {
			int result=service.insertUser(email, name, phone, password);
			if(result>0) {
				User user=service.selectUser(email);
				if(user!=null) {
					HttpSession session = request.getSession();
					session.setAttribute("loginUser", user);
					// cookie 해야함 
				}
			}else {
				// 이메일 중복체크 걸림 나중에 해결하자
			}
		}catch (Exception e) {
			e.printStackTrace();
		}
		
		request.getRequestDispatcher("/views/common/enroll.jsp")
		.forward(request,response);	
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
