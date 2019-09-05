package com.semi.user.controller;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
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

@WebServlet("/register.do")
public class RegisterServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	public RegisterServlet() {
		super();
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		PrintWriter out = response.getWriter();

		String email = null;
		String name = null;
		String phone = null;
		String password = null;
		int r = -1;

		String register = request.getParameter("data");
		UserService service = new UserService();
		try {
			JSONParser jp = new JSONParser();
			JSONObject json = (JSONObject) jp.parse(register);
			email = (String) json.get("email");
			password = SHA512.getSHA512((String) json.get("password"));
			name = (String) json.get("name");
			phone = (String) json.get("phone");
			r = service.insertUser(email, password, name, phone);
		} catch (ParseException e) {
			e.printStackTrace();
		}

		if (r > 1)
			out.print("success");
		else
			out.print("fail");
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doGet(request, response);
	}

}
