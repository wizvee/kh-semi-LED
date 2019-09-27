package com.semi.prm.model.Service;

import java.sql.Connection;
import java.util.List;

import com.semi.prm.model.dao.PayRollManagementDao;
import com.semi.prm.model.vo.PayRollManagement;

import static common.template.JDBCTemplate.*;


public class PayRollManagementService {

	PayRollManagementDao dao = new PayRollManagementDao();
	
	public List<PayRollManagement> makePayRollList(String id, String type, int length) {
		Connection conn = getConnection();
		List<PayRollManagement> prmList = dao.makePayRollList(conn, id, type, length);
		close(conn);
		return prmList;
	}
	
	
	
	
	
}
