package com.semi.emp.model.service;

import static common.template.JDBCTemplate.getConnection;

import java.sql.Connection;

import com.semi.emp.dao.EmpDao;
import com.semi.emp.model.vo.Employee;

public class EmpService {
	
private EmpDao dao = new EmpDao();
	
	public Employee castingTypeO(String userId) {
		Connection conn = getConnection();
		Employee e = dao.castingTypeO(conn, userId);
		return e;
	}

}
