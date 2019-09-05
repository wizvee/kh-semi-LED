package com.semi.user.model.service;

import static common.template.JDBCTemplate.getConnection;

import java.sql.Connection;

import com.semi.user.dao.EmpDao;
import com.semi.user.model.vo.Employee;

public class EmpService {
	
private EmpDao dao = new EmpDao();
	
	public Employee castingTypeO(String userId) {
		Connection conn = getConnection();
		Employee e = dao.castingTypeO(conn, userId);
		return e;
	}

}
