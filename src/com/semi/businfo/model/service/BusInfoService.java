package com.semi.businfo.model.service;

import static common.template.JDBCTemplate.close;
import static common.template.JDBCTemplate.getConnection;

import java.sql.Connection;

import com.semi.bus.model.vo.Business;
import com.semi.businfo.model.dao.BusInfoDao;

public class BusInfoService {
	
	private BusInfoDao dao=new BusInfoDao();
	
	public Business getBusInfo(String busId){
		Connection conn=getConnection();
		Business busInfo=dao.getBusInfo(conn,busId);
		close(conn);
		return busInfo;
	}

}
