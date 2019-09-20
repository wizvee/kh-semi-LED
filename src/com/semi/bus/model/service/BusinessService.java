package com.semi.bus.model.service;

import static common.template.JDBCTemplate.close;
import static common.template.JDBCTemplate.commit;
import static common.template.JDBCTemplate.getConnection;
import static common.template.JDBCTemplate.rollback;

import java.sql.Connection;

import com.semi.bus.dao.BusinessDao;
import com.semi.bus.model.vo.Business;
import com.semi.sft.dao.ShiftDao;
import com.semi.sft.model.vo.Shift;

public class BusinessService {

	private BusinessDao dao = new BusinessDao();
	private ShiftDao sftDao = new ShiftDao();

	public Business selectBusiness(String busId) {
		Connection conn = getConnection();
		Business b = dao.selectBusiness(conn, busId);
		return b;
	}

	public String insertBusiness(String ownId, Business bus, Shift[] sftArr) {
		Connection conn = getConnection();
		String busId = null;
		int r = dao.insertBusiness(conn, bus);
		int r2 = 0;
		for (Shift s : sftArr) {
			r2 += sftDao.insertSft(conn, s);
		}

		if (r > 0 && r2 == sftArr.length) {
			commit(conn);
			busId = dao.getBusId(conn);
		} else
			rollback(conn);
		close(conn);
		return busId;
	}

	public boolean checkBusNum(String bNum) {
		Connection conn = getConnection();
		int r = dao.checkBusNum(conn, bNum);
		boolean check = false;
		if (r > 0)
			check = true;
		return check;
	}

}
