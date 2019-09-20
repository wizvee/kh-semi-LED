package com.semi.emp.model.service;

import static common.template.JDBCTemplate.getConnection;

import java.sql.Connection;
import java.util.ArrayList;

import com.semi.bus.model.vo.Business;
import com.semi.emp.dao.EmpDao;
import com.semi.emp.model.vo.Employee;

public class EmpService {
	
private EmpDao dao = new EmpDao();
	
	public Employee castingTypeE(String userId) {
		Connection conn = getConnection();
		Employee e = dao.castingTypeE(conn, userId);
		return e;
	}
	
	public ArrayList<Business> getBnsList(String userId) {
		Connection conn = getConnection();
		ArrayList<Business> list = dao.getBusList(conn, userId);
		return list;
	}

}
