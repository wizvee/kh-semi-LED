package com.semi.noti.model.service;

import static common.template.JDBCTemplate.close;
import static common.template.JDBCTemplate.commit;
import static common.template.JDBCTemplate.getConnection;
import static common.template.JDBCTemplate.rollback;

import java.sql.Connection;

import com.semi.noti.model.dao.NotiDao;
import com.semi.noti.model.vo.Notification;

public class NotiService {
	
	private NotiDao dao = new NotiDao();
	

	
}
