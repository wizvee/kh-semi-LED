package com.semi.caldendar.model.service;

import static common.template.JDBCTemplate.close;
import static common.template.JDBCTemplate.commit;
import static common.template.JDBCTemplate.getConnection;
import static common.template.JDBCTemplate.rollback;

import java.sql.Connection;
import java.util.ArrayList;

import com.semi.caldendar.model.dao.CalendarDao;
import com.semi.caldendar.model.vo.Cal;
import com.semi.task.model.dao.TaskDao;
import com.semi.task.model.vo.Task;

public class CalendarService {

	private CalendarDao dao = new CalendarDao();
	private TaskDao taskDao = new TaskDao();

	public int insertCal(Cal cal, Task[] taskArr) {
		Connection conn = getConnection();
		int r = dao.insertCal(conn, cal);
		int r2 = 0;
		for (Task t : taskArr)
			r2 += taskDao.addTask(conn, t);

		if (r > 0 && r2 == taskArr.length)
			commit(conn);
		else
			rollback(conn);
		close(conn);
		return r;
	}

	public ArrayList<Cal> getCalList(String busId) {
		Connection conn = getConnection();
		ArrayList<Cal> list = dao.getCalList(conn, busId);
		for (Cal c : list)
			c.setTaskList(taskDao.getTask(conn, c.getCalId()));
		close(conn);
		return list;
	}

}
