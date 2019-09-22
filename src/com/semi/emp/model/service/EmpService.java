package com.semi.emp.model.service;

import static common.template.JDBCTemplate.close;
import static common.template.JDBCTemplate.commit;
import static common.template.JDBCTemplate.getConnection;
import static common.template.JDBCTemplate.rollback;

import java.sql.Connection;
import java.util.ArrayList;
import java.util.HashMap;

import com.semi.bus.model.vo.Business;
import com.semi.emp.model.dao.EmpDao;
import com.semi.emp.model.vo.Employee;
import com.semi.noti.model.dao.NotiDao;
import com.semi.noti.model.vo.Notification;

public class EmpService {

	private EmpDao dao = new EmpDao();
	private NotiDao nDao = new NotiDao();

	public Employee castingTypeE(String userId) {
		Connection conn = getConnection();
		Employee e = dao.castingTypeE(conn, userId);
		close(conn);
		return e;
	}

	public HashMap<String, Business> getBusMap(String userId) {
		Connection conn = getConnection();
		HashMap<String, Business> map = dao.getBusMap(conn, userId);
		close(conn);
		return map;
	}

	public ArrayList<Business> searchBusiness(String key) {
		Connection conn = getConnection();
		ArrayList<Business> list = dao.searchBusiness(conn, key);
		close(conn);
		return list;
	}

	public int submitEnrollBus(Notification n) {
		Connection conn = getConnection();
		int r = dao.submitEnrollBus(conn, n);
		int r2 = nDao.insertNoti(conn, n);
		if (r > 0 && r2 > 0)
			commit(conn);
		else
			rollback(conn);
		close(conn);
		return r + r2;
	}

}
