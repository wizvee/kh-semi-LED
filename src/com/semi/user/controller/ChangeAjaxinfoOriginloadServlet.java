package com.semi.user.controller;

import java.io.File;
import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.semi.owner.model.vo.Owner;
import com.semi.user.model.service.UserService;

/**
 * Servlet implementation class ChangeAjaxinfoOriginloadServlet
 */
@WebServlet("/ajaxoriproFile.do")
public class ChangeAjaxinfoOriginloadServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ChangeAjaxinfoOriginloadServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		String userId = request.getParameter("userId");
		Owner o = (Owner) request.getSession().getAttribute("loginOwner");
		
		String path=getServletContext().getRealPath("/upload/profile");
		String old = o.getProfilePic();
		
		if(!o.getProfilePic().equals("own_default.png")) {
			File deleteFile=new File(path+"/"+old);
			boolean result=deleteFile.delete();
		}
		
		
		int result = new UserService().originPic(userId);
		
		if(result > 0) {
			o.setProfilePic("own_default.png");
			request.getSession().setAttribute("loginOwner", o);
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
