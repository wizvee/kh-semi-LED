package com.semi.owner.model.service;

import static common.template.JDBCTemplate.close;
import static common.template.JDBCTemplate.getConnection;

import java.sql.Connection;
import java.util.ArrayList;
import java.util.HashMap;

import com.semi.bus.model.vo.Business;
import com.semi.emp.model.vo.Employee;
import com.semi.owner.model.dao.OwnerDao;
import com.semi.owner.model.vo.Owner;

public class OwnerService {

	private OwnerDao dao = new OwnerDao();

	public Owner castingTypeO(String userId) {
		Connection conn = getConnection();
		Owner o = dao.castingTypeO(conn, userId);
		close(conn);
		return o;
	}

	public HashMap<String, Business> getBusMap(String userId) {
		Connection conn = getConnection();
		HashMap<String, Business> map = dao.getBusMap(conn, userId);
		close(conn);
		return map;
	}

}
