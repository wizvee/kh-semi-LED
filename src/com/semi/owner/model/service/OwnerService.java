package com.semi.owner.model.service;

import static common.template.JDBCTemplate.getConnection;

import java.sql.Connection;
import java.util.ArrayList;

import com.semi.owner.dao.OwnerDao;
import com.semi.owner.model.vo.Owner;

public class OwnerService {
	
	private OwnerDao dao = new OwnerDao();
	
	public Owner castingTypeO(String userId) {
		Connection conn = getConnection();
		Owner o = dao.castingTypeO(conn, userId);
		return o;
	}
	
	public ArrayList<String> getBnsList(String userId) {
		Connection conn = getConnection();
		ArrayList<String> list = dao.getBnsList(conn, userId);
		return list;
	}

}
