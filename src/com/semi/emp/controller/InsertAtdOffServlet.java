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
 * Servlet implementation class InsertAtdServlet
 */
@WebServlet("/atdOff.do")
public class InsertAtdOffServlet extends HttpServlet {
   private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public InsertAtdOffServlet() {
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
      String sftId = "";
      Attendance a = new Attendance();
      
      SimpleDateFormat formatter = new SimpleDateFormat("yyyyMMddHHmm", Locale.KOREA); 
      Date date = new Date(); 
      String nowDate = formatter.format(date);
      String sft = new AttendanceService().checkSftId(busId, userId);
      String[] flag = sft.split(",");
      sftId = flag[0];
      int check = Integer.parseInt(flag[1].substring(6, 8));
      int check2 = Integer.parseInt(nowDate.substring(6, 8));
      if(check > check2) {
         a = new AttendanceService().findAtdNext(busId, userId, sftId);
      }else {
         a =  new AttendanceService().findAtdThis(busId, userId, sftId);
      }
      
      a.setTimeforLong(a.getAtdOn(), nowDate, a.getSftOn(), a.getSftOff());
      String type = "";
      
      long atdTime = a.getEnAtdTime() - a.getStAtdTime(); // 근무한 실제 시간
      if(a.getStSftTime() < a.getStAtdTime()) {
      }
      
      
      if(a.getStSftTime() < a.getStAtdTime() && a.getEnSftTime() > a.getEnAtdTime()) {
         type = "L,E";
         atdTime = atdTime - ((a.getStAtdTime() - a.getStSftTime()) + (a.getEnSftTime() - a.getEnAtdTime()));
      }else if(a.getStSftTime() < a.getStAtdTime()) {
         type = "L";
         atdTime -= a.getStAtdTime() - a.getStSftTime();
      }else if(a.getEnSftTime() > a.getEnAtdTime()) {
         type = "E";
         atdTime -= a.getEnSftTime() - a.getEnAtdTime();
      }
      //overTime 체크 되면 로직 추가해야함
      int atdWage = 0;
      if(a.getStSftTime() > a.getStAtdTime() && a.getEnSftTime() < a.getEnAtdTime()) {
         atdWage = (int)(atdTime/60)*a.getEmpWage(); 
      if(atdTime%60 > 45) {
         atdWage += atdWage * 0.75; 
      }else if(atdTime%60 > 30) {
         atdWage += atdWage * 0.5;
      }else if(atdTime%60 > 15) {
         atdWage += atdWage * 0.25;
      }
      }
      //update  atdOff atdtype atdTime  atdWage  where atdon = atdon
      //UPDATE ATTENDANCE_TB SET ATD_OFF = ?, ATD_TYPE = ?, ATDTIME = ? ATDWAGE = ? WHERE ATD_ON = ?
      int result = new AttendanceService().updateAtdOff(userId, nowDate, type, atdTime, atdWage, a.getAtdOn());

      
   }

   /**
    * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
    */
   protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
      // TODO Auto-generated method stub
      doGet(request, response);
   }

}