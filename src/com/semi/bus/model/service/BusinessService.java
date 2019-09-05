package com.semi.bus.model.service;

import static common.template.JDBCTemplate.close;
import static common.template.JDBCTemplate.commit;
import static common.template.JDBCTemplate.getConnection;
import static common.template.JDBCTemplate.rollback;

import java.sql.Connection;

import com.semi.bus.dao.BusinessDao;
import com.semi.bus.model.vo.Business;

public class BusinessService {
	
	private BusinessDao dao = new BusinessDao();
	
	public int insertBusiness(String ownId, String name, String addr, String phone) {
		Connection conn = getConnection();
		int r = dao.insertBusiness(conn, ownId, name, addr, phone);
		if(r > 0)
			commit(conn);
		else
			rollback(conn);
		close(conn);
		return r;
	}

}
