package com.semi.snslogin.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import com.google.gson.JsonParser;

/**
 * Servlet implementation class SnsLoginServlet
 */
@WebServlet("/snsLogin.do")
public class SnsLoginServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public SnsLoginServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String data=request.getParameter("snsLogin");
		JSONParser jp = new JSONParser();
		JSONObject json = null;
		String id="";
		String imageUrl="";
		String email="";
		try {
			json = (JSONObject) jp.parse(data);
			id = (String) json.get("Eea");
			imageUrl=(String)json.get("Paa");
			email=(String)json.get("U3");
		} catch (ParseException e) {
			e.printStackTrace();
		}

		request.setAttribute("id", id);
		request.setAttribute("imageUrl",imageUrl);
		request.setAttribute("email", email);
		
		
		request.getRequestDispatcher("/views/common/snsEnroll.jsp").forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
