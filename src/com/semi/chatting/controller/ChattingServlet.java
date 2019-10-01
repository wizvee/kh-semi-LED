package com.semi.chatting.controller;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

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
		ChattingService service=new ChattingService();
		String userList;
		String chatHistory;
		String data;
		
		//ajax 로 보낸 busId 값 받기 
		String busAnduser =request.getParameter("data");
		String busId=busAnduser.split("/")[0];  
		String userId=busAnduser.split("/")[1];
		
		// 채팅 타입,채팅 내역, 채팅 날짜, 유저 이름, 사진 정보 DB 에서 가지고 오기 
		List<Chatting>chatList=service.getHistory(busId);

		// //해당 사업장에 등록된 유저들 모두 불러오기 
		List<Chatting>uList=service.getAllUsers(busId,userId);		
		
		
		if(!chatList.isEmpty()) {
			chatHistory = new Gson().toJson(chatList);
		}else {
			chatHistory="none";
		}
		
		if(!uList.isEmpty()) {
			userList=new Gson().toJson(uList);
		}else {
			userList="none";
		}
		
		Map<String,String> wholeData=new HashMap<String,String>();
		wholeData.put("chatHistory", chatHistory);
		wholeData.put("userList", userList);
		
		data=new Gson().toJson(wholeData);
		
		
		// chatting.js 로 list 보내기
		response.getWriter().print(data);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
