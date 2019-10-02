package com.semi.emp.controller;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.semi.atd.model.Service.AttendanceService;
import com.semi.atd.model.vo.Attendance;
import com.semi.emp.model.service.EmpService;
import com.semi.userinfo.model.vo.UserInfo;

/**
 * Servlet implementation class UpdateAtdOnServlet
 */
@WebServlet("/atdOn.do")
public class InsertAtdOnServlet extends HttpServlet {
   private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public InsertAtdOnServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

   /**
    * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
    */
   protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

      HttpSession session = request.getSession();
      UserInfo ui = (UserInfo) session.getAttribute("userInfo");
      String busId = ui.getSelectBusId();
      String userId = ui.getUserId();
      
      
      SimpleDateFormat formatter = new SimpleDateFormat("yyyyMMddHHmm", Locale.KOREA); 
      Date date = new Date(); 
      String nowDate = formatter.format(date);
      System.out.println(nowDate);
      String sftId = new AttendanceService().setAtdCheck(busId, userId);
      int result = new EmpService().insertAtdOn(nowDate, busId, userId, sftId);

      
       
   }

   /**
    * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
    */
   protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
      // TODO Auto-generated method stub
      doGet(request, response);
   }

}