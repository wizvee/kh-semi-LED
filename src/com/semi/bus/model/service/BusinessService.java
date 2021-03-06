package com.semi.bus.model.service;

import static common.template.JDBCTemplate.close;
import static common.template.JDBCTemplate.commit;
import static common.template.JDBCTemplate.getConnection;
import static common.template.JDBCTemplate.rollback;

import java.sql.Connection;
import java.util.ArrayList;

import com.semi.bus.model.dao.BusinessDao;
import com.semi.bus.model.vo.Business;
import com.semi.emp.model.vo.Employee;
import com.semi.noti.model.dao.NotiDao;
import com.semi.sft.model.dao.ShiftDao;
import com.semi.sft.model.vo.Shift;

public class BusinessService {

	private BusinessDao dao = new BusinessDao();
	private ShiftDao sftDao = new ShiftDao();
	private NotiDao nDao = new NotiDao();

	public String insertBusiness(String ownId, Business bus, Shift[] sftArr) {
		Connection conn = getConnection();
		String busId = null;
		int r = dao.insertBusiness(conn, bus);
		int r2 = 0;
		for (Shift s : sftArr)
			r2 += sftDao.insertSft(conn, s);

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

	public ArrayList<Employee> getEmpList(String selectBusId) {
		Connection conn = getConnection();
		ArrayList<Employee> list = dao.getEmpList(conn, selectBusId);
		close(conn);
		return list;
	}

	public ArrayList<Shift> getSftList(String selectBusId) {
		Connection conn = getConnection();
		ArrayList<Shift> list = dao.getSftList(conn, selectBusId);
		close(conn);
		return list;
	}

	public int approvalEmp(String busId, Employee e) {
		Connection conn = getConnection();
		int r = dao.approvalEmp(conn, busId, e);
		if (r > 0)
			commit(conn);
		else
			rollback(conn);
		close(conn);
		return r;
	}

	public int rejectEmp(String busId, String empId) {
		Connection conn = getConnection();
		int r = dao.rejectEmp(conn, busId, empId);
		if (r > 0)
			commit(conn);
		else
			rollback(conn);
		close(conn);
		return r;
	}

	public int editEmp(String busId, Employee e) {
		Connection conn = getConnection();
		int r = dao.editEmp(conn, busId, e);
		if (r > 0)
			commit(conn);
		else
			rollback(conn);
		close(conn);
		return r;
	}
	
	public ArrayList<String> getSftEmpList(String sftId) {
		Connection conn = getConnection();
		ArrayList<String> list = dao.getSftEmpList(conn, sftId);
		close(conn);
		return list;
	}
}
