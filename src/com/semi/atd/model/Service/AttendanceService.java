package com.semi.atd.model.Service;
   import static common.template.JDBCTemplate.*;
import static common.template.JDBCTemplate.getConnection;

import java.sql.Connection;
import java.util.List;

import com.semi.atd.model.dao.AttendanceDao;
import com.semi.atd.model.vo.Attendance;
import com.semi.emp.model.vo.Employee;

import oracle.jdbc.OracleConnection.CommitOption;

   public class AttendanceService {
      AttendanceDao dao = new AttendanceDao();
      
      public Attendance setAttendance(Employee e, String id) {
         
         Connection conn = getConnection();
         Attendance atd = dao.setAttendance(conn, e, id);
         close(conn);
         
         return atd;
      }
      

      public List<Attendance> setAttendanceList(String date, String id) {
         Connection conn = getConnection();
         List<Attendance> list = dao.getAttendanceList(conn, date, id);
         close(conn);
         return list;
      }
   
      public List getDayList(int term, String busId) {
         Connection conn = getConnection();
         List dayList = dao.getDayList(conn, term, busId);
         close(conn);
         return dayList;
      }
      
      public String setAtdCheck(String busId, String id) {
         Connection conn = getConnection();
         String sftId = dao.setAtdCheck(conn, busId, id);
         close(conn);
         return sftId;
         
      }
      
      public Attendance findAtdThis(String busId, String id, String sftId) {
         Connection conn = getConnection();
         Attendance a = dao.findAtdThis(conn, busId, id, sftId);
         close(conn);
         return a;
      }
   
      public Attendance findAtdNext(String busId, String id, String sftId) {
         Connection conn = getConnection();
         Attendance a = dao.findAtdNext(conn, busId, id, sftId);
         close(conn);
         return a;
      }
      
      public int updateAtdOff(String id, String nowDate, String type, long atdTime, int atdWage, String atdOn) {
         Connection conn = getConnection();
         int result = dao.updateAtdOff(conn, id, nowDate, type, atdTime, atdWage, atdOn);
         if(result > 0) {
            commit(conn);
         }else {
            rollback(conn);
         }
         close(conn);
         return result;
      }
      
      public String checkSftId(String busId, String id) {
         Connection conn = getConnection();
         String sftId = dao.checkSftId(conn, busId, id);
         close(conn);
         return sftId;
      }
         
}