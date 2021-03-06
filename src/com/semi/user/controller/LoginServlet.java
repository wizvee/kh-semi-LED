package com.semi.user.controller;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import com.semi.user.model.service.UserService;
import com.semi.user.model.vo.User;

import common.util.SHA512;

@WebServlet("/login.do")
@MultipartConfig
public class LoginServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	public LoginServlet() {
		super();
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		PrintWriter out = response.getWriter();
		String login = request.getParameter("data");
		System.out.println(login);
		
		UserService service = new UserService();
		User user = null;		
		
		try { 
			JSONParser jp = new JSONParser(); 
			JSONObject json = (JSONObject) jp.parse(login); 
			String password = SHA512.getSHA512((String) json.get("password"));
			user = service.selectUser((String) json.get("email"),password);
			if (user != null) {
				HttpSession session = request.getSession(); 
			    session.setAttribute("loginUser", user); 
			    out.print("success"); 
			    if (json.get("loginKeep") != null && (boolean) json.get("loginKeep") == true) {
			    	Cookie c = new Cookie("loginKeep",(String) json.get("email"));
			    	c.setMaxAge(3*24*60*60); //3일 
			    	response.addCookie(c);
			    } else {
			    	Cookie c = new Cookie("loginKeep",(String) json.get("email"));
			    	c.setMaxAge(0);
		    		response.addCookie(c); 
			    }
			}   	
		} catch (ParseException e){ 
		    	e.printStackTrace(); 
		  }
		 
		 
		
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doGet(request, response);
	}

}
