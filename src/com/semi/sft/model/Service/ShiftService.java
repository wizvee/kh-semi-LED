package com.semi.sft.model.Service;

import static common.template.JDBCTemplate.*;

import java.sql.Connection;
import java.util.List;

import com.semi.emp.model.vo.Employee;
import com.semi.sft.model.dao.ShiftDao;
import com.semi.sft.model.vo.Shift;

public class ShiftService {

	ShiftDao dao = new ShiftDao();

	public List<Shift> getSftList(String id) {
		Connection conn = getConnection();
		List<Shift> list = dao.getShiftList(conn, id);
		close(conn);
		
		return list;
	}
	
	public List<Employee> addShiftForEmpList(String id, List<Employee> empList) {
		Connection conn = getConnection();
		List<Employee> list = dao.addShiftForEmpList(conn, id, empList);
		close(conn);
		
		return list;
	}
}
