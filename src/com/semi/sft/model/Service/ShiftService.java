package com.semi.sft.model.Service;

import static common.template.JDBCTemplate.*;

import java.sql.Connection;
import java.util.List;

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
}
