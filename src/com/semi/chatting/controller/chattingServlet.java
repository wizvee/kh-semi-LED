package com.semi.chatting.controller;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.gson.Gson;
import com.semi.chatting.model.service.ChattingService;
import com.semi.chatting.model.vo.Chatting;

/**
 * Servlet implementation class ChattingServlet
 */
@WebServlet("/chat.do")
public class ChattingServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ChattingServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		//ajax 로 보낸 busId 값 받기 
		String busId =request.getParameter("data");
		System.out.println(busId);
		
		// 채팅 타입,채팅 내역, 채팅 날짜, 유저 이름, 사진 정보 DB 에서 가지고 오기 
	
		ChattingService service=new ChattingService();
		
		List<Chatting>list=service.getHistory(busId);
		String chatHistory;
		
		if(!list.isEmpty()) {
			chatHistory = new Gson().toJson(list);
		}else {
			chatHistory="none";
		}
		
		// chatting.js 로 list 보내기
		response.getWriter().print(chatHistory);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
