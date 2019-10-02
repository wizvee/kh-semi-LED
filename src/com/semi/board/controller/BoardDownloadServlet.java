package com.semi.board.controller;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.net.URLEncoder;

import javax.servlet.ServletException;
import javax.servlet.ServletOutputStream;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class BoardDownloadServlet
 */
@WebServlet("/board/download")
public class BoardDownloadServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public BoardDownloadServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//첨부파일 다운로드 서블릿
	
		//파일 받아오기
		String fileName=request.getParameter("fileName");
		
		
		String root=getServletContext().getRealPath("/");
		String saveDir=root+"/upload/board";
		
		//인풋 스트림 생성 
		File f=new File(saveDir+"/"+fileName);
		BufferedInputStream bi=new BufferedInputStream(new FileInputStream(f));
		
		//보낼 아웃풋 스트림 생성
		ServletOutputStream sos=response.getOutputStream();
		BufferedOutputStream bos=new BufferedOutputStream(sos);
		
		//브라우저에 맞춰서 인코딩처리 ,파일명 
		String reNameFile="";
		boolean isMSIE=request.getHeader("user-agent").indexOf("MSIE")!=-1||request.getHeader("user-agent").indexOf("Trident")!=-1;
		if(isMSIE) {
			reNameFile=URLEncoder.encode(fileName,"UTF-8").replaceAll("\\+", "%20");
		}else {
			reNameFile=new String(fileName.getBytes("UTF-8"),"ISO-8859-1");
		}
		
		//response 헤더 설정
		response.setHeader("Content-Disposition", "attachment;filename="+reNameFile); //name 헤더의 값을 value로 지정
		response.setContentType("application/octet-stream"); // 8비트 바이너리 배열을 의미하며 http나 이메일상에서 application 형식이 지정되지 않았거나 형식을 모를때 사용
		
		//파일 전송 
		int read=-1;
		while((read=bi.read())!=-1){
			bos.write(read);
		}bos.close();
		bi.close();
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
