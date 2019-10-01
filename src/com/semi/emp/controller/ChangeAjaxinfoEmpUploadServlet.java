package com.semi.emp.controller;

import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.tomcat.util.http.fileupload.servlet.ServletFileUpload;

import com.oreilly.servlet.MultipartRequest;
import com.oreilly.servlet.multipart.DefaultFileRenamePolicy;
import com.semi.emp.model.vo.Employee;
import com.semi.owner.model.vo.Owner;
import com.semi.user.model.service.UserService;

/**
 * Servlet implementation class ChangeAjaxinfoUploadServlet
 */
@WebServlet("/ajaxempproFile.do")
public class ChangeAjaxinfoEmpUploadServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ChangeAjaxinfoEmpUploadServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		PrintWriter out = response.getWriter();
		
		// 파일 형식이 맞게 들어왔는지 확인을 해주려고 쓰는 것. -> 멀티파트요청인지 확인을 해주려고 쓰는 것.
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
		out.print(upfile);
		
		//로그인 오너에 프로필을 넣고 세션을 업데이트?
		Employee e = (Employee) request.getSession().getAttribute("loginEmp");
		String old = e.getProfilePic();
		System.out.println(old);
		
		//파일 삭제 방법.
		  if(upfile!=null && upfile.length()>0) { 
			  if(!old.equals("emp_default.png")) {
				  File deleteFile=new File(path+"/"+old);
				  boolean result=deleteFile.delete();
				  e.setProfilePic(upfile);
				  request.getSession().setAttribute("loginEmp", e);
			  } else if(old.equals("emp_default.png")){
				  e.setProfilePic(upfile);
				  request.getSession().setAttribute("loginEmp", e);
			  }
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
