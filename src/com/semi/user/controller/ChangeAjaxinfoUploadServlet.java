package com.semi.user.controller;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Enumeration;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class ChangeAjaxinfoUploadServlet
 */
@WebServlet("/ajaxproFile.do")
public class ChangeAjaxinfoUploadServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ChangeAjaxinfoUploadServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		//이건 왜 써야되지? 모르겠음
		if(!ServletFileUpload.isMultipartContent(request)) {
			response.sendRedirect("/");
			return;
		}
		
		//저장경로 설정...
		String path=getServletContext().getRealPath("/upload/profile");
		
		//크기 설정...
		int maxSize=1024*1024*100;
		
		MultipartRequest mr=new MultipartRequest(request, path, maxSize, "UTF-8", new DefaultFileRenamePolicy());
		
		List<String> fileNames=new ArrayList();
		Enumeration<String> e=mr.getFileNames();
		while(e.hasMoreElements()) {
			fileNames.add(mr.getFilesystemName(e.nextElement()));
		}
		System.out.println(fileNames);
		
		
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
