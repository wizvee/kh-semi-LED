package com.semi.atd.model.Service;
	import static common.template.JDBCTemplate.close;
import static common.template.JDBCTemplate.getConnection;

import java.sql.Connection;

import com.semi.atd.model.dao.AttendanceDao;
import com.semi.atd.model.vo.Attendance;
import com.semi.emp.model.vo.Employee;

	public class AttendanceService {
		AttendanceDao dao = new AttendanceDao();
		
		public Attendance setAttendance(Employee e, String id) {
			
			Connection conn = getConnection();
			Attendance atd = dao.setAttendance(conn, e, id);
			close(conn);
			
			return atd;
		}
		

	
	
	
	
}
