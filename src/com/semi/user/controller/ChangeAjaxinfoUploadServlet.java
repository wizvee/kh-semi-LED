package com.semi.user.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.tomcat.util.http.fileupload.servlet.ServletFileUpload;

import com.oreilly.servlet.MultipartRequest;
import com.oreilly.servlet.multipart.DefaultFileRenamePolicy;
import com.semi.user.model.service.UserService;

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
		
		//이건 왜 써야되지? 모르겠음 -> 파일 형식이 맞게 들어왔는지 확인을 해주려고 쓰는 것.
		if(!ServletFileUpload.isMultipartContent(request)) {
			response.sendRedirect("/");
			return;
		}
		
		//저장경로 설정...
		String path=getServletContext().getRealPath("/upload/profile");
		
		//크기 설정...
		int maxSize=1024*1024*100;
		
		MultipartRequest mr=new MultipartRequest(request, path, maxSize, "UTF-8", new DefaultFileRenamePolicy());

		
		String userId = mr.getParameter("userId");
		String upfile = mr.getFilesystemName("upfile");
		
		int resultPic = new UserService().UpdatePic(userId, upfile);
		
		//파일 삭제 방법.
		/*
		 * if(filename!=null && filename.length()>0) { File deleteFile=new
		 * File(path+"/"+); boolean result=deleteFile.delete(); } else {
		 * fileName=oriFile; }
		 */
		
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
