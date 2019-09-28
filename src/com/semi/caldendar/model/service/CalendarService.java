package com.semi.caldendar.model.service;

import static common.template.JDBCTemplate.close;
import static common.template.JDBCTemplate.commit;
import static common.template.JDBCTemplate.getConnection;
import static common.template.JDBCTemplate.rollback;

import java.sql.Connection;
import java.util.ArrayList;

import com.semi.caldendar.model.dao.CalendarDao;
import com.semi.caldendar.model.vo.Cal;
import com.semi.task.model.vo.Task;

public class CalendarService {
	
	private CalendarDao dao = new CalendarDao();
	
	public int insertCal(Cal cal, Task[] taskArr) {
		Connection conn = getConnection();
		int r = dao.insertCal(conn, cal);
		if(r > 0)
			commit(conn);
		else
			rollback(conn);
		close(conn);
		return r;
	}
	
	public ArrayList<Cal> getCalList(String busId) {
		Connection conn = getConnection();
		ArrayList<Cal> list = dao.getCalList(conn, busId);
		close(conn);
		return list;
	}

}
