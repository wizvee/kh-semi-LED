package com.semi.emp.model.dao;

import static common.template.JDBCTemplate.close;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Properties;

import com.semi.bus.model.vo.Business;
import com.semi.emp.model.vo.Employee;
import com.semi.noti.model.vo.Notification;

public class EmpDao {

   private Properties prop = new Properties();

   public EmpDao() {
      String path = EmpDao.class.getResource("/sql/user/emp-query.properties").getPath();
      try {
         prop.load(new FileReader(path));
      } catch (FileNotFoundException e) {
         e.printStackTrace();
      } catch (IOException e) {
         e.printStackTrace();
      }
   }

   public Employee castingTypeE(Connection conn, String userId) {
      PreparedStatement pstmt = null;
      ResultSet rs = null;
      String sql = prop.getProperty("castingType");
      Employee e = null;
      try {
         pstmt = conn.prepareStatement(sql);
         pstmt.setString(1, userId);
         rs = pstmt.executeQuery();
         if (rs.next()) {
            e = new Employee();
            e.setUserId(rs.getString("USER_ID"));
            e.setUserName(rs.getString("USER_NAME"));
            e.setProfilePic(rs.getString("PROFILE_PIC"));
         }
      } catch (SQLException e1) {
         e1.printStackTrace();
      } finally {
         close(rs);
         close(pstmt);
      }
      return e;
   }

   public HashMap<String, Business> getBusMap(Connection conn, String userId) {
      PreparedStatement pstmt = null;
      ResultSet rs = null;
      String sql = prop.getProperty("getBusMap");
      HashMap<String, Business> map = new HashMap<>();
      try {
         pstmt = conn.prepareStatement(sql);
         pstmt.setString(1, userId);
         rs = pstmt.executeQuery();
         while (rs.next()) {
            Business b = new Business();
            b.setBusId(rs.getString("BUS_ID"));
            b.setBusName(rs.getString("BUS_NAME"));
            map.put(b.getBusId(), b);
         }
      } catch (SQLException e) {
         e.printStackTrace();
      } finally {
         close(rs);
         close(pstmt);
      }
      return map;
   }

   public ArrayList<Business> searchBusiness(Connection conn, String key) {
      PreparedStatement pstmt = null;
      ResultSet rs = null;
      String sql = prop.getProperty("searchBusiness");
      ArrayList<Business> list = new ArrayList<>();
      try {
         pstmt = conn.prepareStatement(sql);
         pstmt.setString(1, "%" + key + "%");
         rs = pstmt.executeQuery();
         while (rs.next()) {
            Business b = new Business();
            b.setBusId(rs.getString("BUS_ID"));
            b.setOwnId(rs.getString("OWN_ID"));
            b.setBusName(rs.getString("BUS_NAME"));
            b.setBusAddr(rs.getString("BUS_ADDR"));
            b.setBusPhone(rs.getString("BUS_PHONE"));
            b.setOwnName(rs.getString("USER_NAME"));
            list.add(b);
         }
      } catch (SQLException e) {
         e.printStackTrace();
      } finally {
         close(rs);
         close(pstmt);
      }
      return list;
   }
   
   public int submitEnrollBus(Connection conn, Notification n) {
      PreparedStatement pstmt = null;
      String sql = prop.getProperty("submitEnrollBus");
      int r = -1;
      try {
         pstmt = conn.prepareStatement(sql);
         pstmt.setString(1, n.getTargetBusId());
         pstmt.setString(2, n.getUserId());
         r = pstmt.executeUpdate();
      } catch (SQLException e) {
         e.printStackTrace();
      } finally {
         close(pstmt);
      }
      return r;
   }
   
   public int insertAtdOn(Connection conn, String date, String busId, String id, String sftId) {
      PreparedStatement pstmt = null;
      int result = 0;
      String sql = prop.getProperty("insertAtdOn");
      try {
         pstmt = conn.prepareStatement(sql);
         pstmt.setString(1, busId);
         pstmt.setString(2, id);
         pstmt.setString(3, date);
         pstmt.setString(4, sftId);
         result = pstmt.executeUpdate();
         
      }catch(SQLException e) {
         e.printStackTrace();
      }finally {
         close(pstmt);
      }return result;
      
      
   }

}