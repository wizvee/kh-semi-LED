package com.semi.noti.model.service;

import static common.template.JDBCTemplate.close;
import static common.template.JDBCTemplate.commit;
import static common.template.JDBCTemplate.getConnection;
import static common.template.JDBCTemplate.rollback;

import java.sql.Connection;
import java.util.ArrayList;
import java.util.HashSet;

import com.semi.noti.model.dao.NotiDao;
import com.semi.noti.model.vo.Notification;

public class NotiService {

	private NotiDao dao = new NotiDao();

	public int insertNoti(Notification n) {
		Connection conn = getConnection();
		int r = dao.insertNoti(conn, n);
		if (r > 0)
			commit(conn);
		else
			rollback(conn);
		close(conn);
		return r;
	}

	public int isReadNoti(String notiId) {
		Connection conn = getConnection();
		int r = dao.isReadNoti(conn, notiId);
		if (r > 0)
			commit(conn);
		else
			rollback(conn);
		close(conn);
		return r;
	}

	public HashSet<String> getAlertTarget(String busId) {
		Connection conn = getConnection();
		HashSet<String> set = dao.getAlertTarget(conn, busId);
		close(conn);
		return set;
	}

}
