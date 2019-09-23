package com.semi.atd.model.vo;

public class Attendance {

	private String busId;
	private String empId;
	private String atdOn;
	private String atdOff;
	private String overTime;
	private String attendance;
	private int dayWage;
	private String sftId;
	
	
	public Attendance() {
		// TODO Auto-generated constructor stub
	}


	public Attendance(String busId, String empId, String atdOn, String atdOff, String overTime, String attendance,
			int dayWage, String sftId) {
		super();
		this.busId = busId;
		this.empId = empId;
		this.atdOn = atdOn;
		this.atdOff = atdOff;
		this.overTime = overTime;
		this.attendance = attendance;
		this.dayWage = dayWage;
		this.sftId = sftId;
	}


	public String getBusId() {
		return busId;
	}


	public void setBusId(String busId) {
		this.busId = busId;
	}


	public String getEmpId() {
		return empId;
	}


	public void setEmpId(String empId) {
		this.empId = empId;
	}


	public String getAtdOn() {
		return atdOn;
	}


	public void setAtdOn(String atdOn) {
		this.atdOn = atdOn;
	}


	public String getAtdOff() {
		return atdOff;
	}


	public void setAtdOff(String atdOff) {
		this.atdOff = atdOff;
	}


	public String getOverTime() {
		return overTime;
	}


	public void setOverTime(String overTime) {
		this.overTime = overTime;
	}


	public String getAttendance() {
		return attendance;
	}


	public void setAttendance(String attendance) {
		this.attendance = attendance;
	}


	public int getDayWage() {
		return dayWage;
	}


	public void setDayWage(int dayWage) {
		this.dayWage = dayWage;
	}


	public String getSftId() {
		return sftId;
	}


	public void setSftId(String sftId) {
		this.sftId = sftId;
	}


		
}
