package com.semi.atd.model.dao;

import static common.template.JDBCTemplate.close;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

import com.semi.atd.model.vo.Attendance;
import com.semi.emp.model.vo.Employee;
import com.semi.sft.model.dao.ShiftDao;

public class AttendanceDao {

   
   private Properties prop = new Properties();

   public AttendanceDao() {
      String path = ShiftDao.class.getResource("/sql/attendance/atd-query.properties").getPath();
      try {
         prop.load(new FileReader(path));
      } catch (FileNotFoundException e) {
         e.printStackTrace();
      } catch (IOException e) {
         e.printStackTrace();
      }
   }

   public Attendance setAttendance(Connection conn, Employee e, String id) {
//       SFT_ON, SFT_OFF, SFT_ID, ATD_ON, ATD_OFF 
      PreparedStatement pstmt = null;
      ResultSet rs = null;
      String sql = prop.getProperty("setAttendance");
      Attendance a = new Attendance();
      try {
         pstmt = conn.prepareStatement(sql);
         pstmt.setString(1, id);
         pstmt.setString(2, e.getUserId());
         rs = pstmt.executeQuery();
         while (rs.next()) {
            a.setSftId(rs.getString("SFT_ID"));
            a.setAtdOn(rs.getString("ATD_ON"));
            a.setAtdOff(rs.getString("ATD_OFF"));
            if(rs.getString("ATD_TYPE")!= null) {
               a.setAtdType(rs.getString("ATD_TYPE").split(","));
            }
         }
      } catch (SQLException b) {
         b.printStackTrace();
      } finally {
         close(rs);
         close(pstmt);
      }return a;

   }
   
   
   public List<Attendance> getAttendanceList(Connection conn, String date, String id) {
      List<Attendance> list = new ArrayList<Attendance>();
      PreparedStatement pstmt = null;
      ResultSet rs = null;
      String sql = prop.getProperty("setAttendanceList");
      System.out.println(date);
      System.out.println(id);
      try {
         pstmt = conn.prepareStatement(sql);
         pstmt.setString(1, id);
         pstmt.setString(2, date);
         rs = pstmt.executeQuery();
         
         while(rs.next()) {
            Attendance a = new Attendance();
            
            a.setEmpId(rs.getString("EMP_ID"));
            a.setSftId(rs.getString("SFT_ID"));
            a.setSftName(rs.getString("SFT_NAME"));
            a.setSftOn(rs.getString("SFT_ON"));
            a.setSftOff(rs.getString("SFT_OFF"));
            if(rs.getString("ATD_TYPE")!= null) {
               a.setAtdType((rs.getString("ATD_TYPE").split(",")));
               System.out.println(a.getAtdType()[0]);
            }
            a.setAtdOn(rs.getString("ATD_ON"));
            a.setAtdOff(rs.getString("ATD_OFF"));
            
            list.add(a);
            
         }
         
         
         
      }catch (SQLException e) {
         e.printStackTrace();
      }finally {
         close(rs);
         close(pstmt);
      }return list;
      
   }
   

   public List getDayList (Connection conn, int date, String busId) {
      
      PreparedStatement pstmt = null;
      ResultSet rs = null;
      String sql = prop.getProperty("setCalendar");
      List list = new ArrayList();
      
      try {
         pstmt = conn.prepareStatement(sql);
         pstmt.setInt(1, date);
         pstmt.setInt(2, date);
         pstmt.setString(3, busId);
         rs = pstmt.executeQuery();
         while(rs.next()) {
            String[] days = new String[2];
            days[0] = rs.getString(1);
            days[1] = rs.getString(2);
            
            list.add(days);
         }
         
      }catch(SQLException e) {
         e.printStackTrace();
      }finally {
         close(rs);
         close(pstmt);
      }return list;
      
      
   }
   
   
//public List getNexDayList (Connection conn, int date, String busId) {
//      
//      PreparedStatement pstmt = null;
//      ResultSet rs = null;
//      String sql = prop.getProperty("setNexCalendar");
//      List list = new ArrayList();
//      
//      try {
//         pstmt = conn.prepareStatement(sql);
//         pstmt.setInt(1, date);
//         pstmt.setInt(2, date);
//         pstmt.setString(3, busId);
//         rs = pstmt.executeQuery();
//         while(rs.next()) {
//            String[] days = new String[2];
//            days[0] = rs.getString(1);
//            days[1] = rs.getString(2);
//            list.add(days);
//         }
//         
//      }catch(SQLException e) {
//         e.printStackTrace();
//      }finally {
//         close(rs);
//         close(pstmt);
//      }return list;
//      
//      
//   }
   
   public String setAtdCheck(Connection conn, String busId, String id) {
      PreparedStatement pstmt = null;
      ResultSet rs = null;
      String sql = prop.getProperty("setAtdCheck");
      String sftId = "";
      try {
         pstmt = conn.prepareStatement(sql);
         pstmt.setString(1, busId);
         pstmt.setString(2, id);
         rs = pstmt.executeQuery();
         
         while(rs.next()) {
            sftId = rs.getString("SFT_ID");
         }
      }catch(SQLException e) {
         e.printStackTrace();
      }finally {
         close(rs);
         close(pstmt);
      }return sftId;
      
   }

   
   public Attendance findAtdThis(Connection conn, String busId, String id, String sftId) {
      Attendance a = new Attendance();
      PreparedStatement pstmt = null;
      ResultSet rs = null;
      String sql = prop.getProperty("findAtdThis");
      try {
         pstmt = conn.prepareStatement(sql);
         pstmt.setString(1, busId);
         pstmt.setString(2, id);
         pstmt.setString(3, sftId);
         rs = pstmt.executeQuery();
         
         if(rs.next()) {
            if(rs.getString(1)!= null) {
               a.setAtdType(rs.getString("ATD_TYPE").split(","));
            }
            a.setSftOn(rs.getString("SFT_ON"));
            a.setSftOff(rs.getString("SFT_OFF"));
            a.setAtdOn(rs.getString("ATD_ON"));
            a.setEmpWage(rs.getInt("EMP_WAGE"));
            a.setEmpType(rs.getString("EMP_TYPE"));
         }
      }catch(SQLException e) {
         e.printStackTrace();
      }finally {
         close(rs);
         close(pstmt);
      }return a;
      
   }
   

   public Attendance findAtdNext(Connection conn, String busId, String id, String sftId) {
      Attendance a = new Attendance();
      PreparedStatement pstmt = null;
      ResultSet rs = null;
      String sql = prop.getProperty("findAtdNext");
      try {
         pstmt = conn.prepareStatement(sql);
         pstmt.setString(1, busId);
         pstmt.setString(2, id);
         pstmt.setString(3, sftId);
         rs = pstmt.executeQuery();
         
         if(rs.next()) {
            if(rs.getString(1)!= null) {
               a.setAtdType(rs.getString(1).split(","));
            }
            a.setSftOn(rs.getString(2));
            a.setSftOff(rs.getString(3));
            a.setAtdOn(rs.getString(4));
            a.setEmpWage(rs.getInt(5));
            a.setEmpType(rs.getString(6));
         }
      }catch(SQLException e) {
         e.printStackTrace();
      }finally {
         close(rs);
         close(pstmt);
      }return a;
      
   }

   
   
   public int updateAtdOff(Connection conn, String id, String nowDate, String type, long atdTime, int atdWage, String atdOn) {
      PreparedStatement pstmt = null;
      int result = 0;
      String sql = prop.getProperty("updateAtdOff");
      try {
         pstmt = conn.prepareStatement(sql);
         pstmt.setString(1, nowDate);
         pstmt.setString(2, type);
         pstmt.setLong(3, atdTime);
         pstmt.setInt(4, atdWage);
         pstmt.setString(5, atdOn);
         pstmt.setString(6, id);
      result = pstmt.executeUpdate();
      
   }catch(SQLException e) {
      e.printStackTrace();
   }finally {
      close(pstmt);
   }return result;
}
   
   public String checkSftId(Connection conn, String busId, String id) {
      
      String sftId = "";
      PreparedStatement pstmt = null;
      ResultSet rs = null;
      String sql = prop.getProperty("checkSftId");
      try {
         pstmt = conn.prepareStatement(sql);
         pstmt.setString(1, busId);
         pstmt.setString(2, id);
         rs = pstmt.executeQuery();
         
         if(rs.next()) {
      
            sftId = rs.getString(1);
            sftId += "," +rs.getString(2);
         }
      }catch(SQLException e) {
         e.printStackTrace();
      }finally {
         close(rs);
         close(pstmt);
      }return sftId;
      
      
      
   }
   
   
}
