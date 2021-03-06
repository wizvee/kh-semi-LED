package com.semi.statistics.model.service;

import static common.template.JDBCTemplate.close;
import static common.template.JDBCTemplate.getConnection;

import java.sql.Connection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.semi.statistics.model.dao.StatisticsDao;
import com.semi.statistics.model.vo.Statistics;

public class StatisticsService {
	
	private StatisticsDao dao=new StatisticsDao();
	
	Map<String,List<Statistics>>allData=new HashMap();
	
	public Map<String,List<Statistics>> getAllData(String busId){
		Connection conn=getConnection();
		List<Statistics>forWageLine=dao.forWageLine(conn, busId);
		List<Statistics>forWageTable=dao.forWageTable(conn, busId);
		List<Statistics>forTimeLine=dao.forTimeLine(conn,busId);
		List<Statistics>forWorkingHour=dao.forWorkingHour(conn,busId);
		List<Statistics>forTotalEmp=dao.forTotalEmp(conn,busId);
		List<Statistics>forTotalLate=dao.forTotalLate(conn,busId);
		List<Statistics>forTotalEarlyLeave=dao.forTotalEarlyLeave(conn,busId);
		List<Statistics>forTotalOverTime=dao.forTotalOverTime(conn,busId);
		
		allData.put("forWageLine", forWageLine);
		allData.put("forWageTable", forWageTable);
		allData.put("forTimeLine", forTimeLine);
		allData.put("forWorkingHour", forWorkingHour);
		allData.put("forTotalEmp", forTotalEmp);
		allData.put("forTotalLate", forTotalLate);
		allData.put("forTotalEarlyLeave", forTotalEarlyLeave);
		allData.put("forTotalOverTime", forTotalOverTime);
		close(conn);
		return allData;
	}
	
	
}
