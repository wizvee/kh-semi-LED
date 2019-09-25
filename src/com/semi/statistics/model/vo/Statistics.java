package com.semi.statistics.model.vo;

import java.sql.Date;

public class Statistics {
	
	private int hourlyWage;
	private int dailyWage;
	private int monthlyWage;
	private String Date;
	private String empName;
	private boolean workingNow;
	private Date empStart;
	private Date empEnd;
	private int workedHours;
	private int lateDays;
	private int earlyLeaveDays;
	
	public Statistics() {
		//TODO Auto-generated constructor stub
	}

	public Statistics(int hourlyWage, int dailyWage, int monthlyWage, String date, String empName, boolean workingNow,
			java.sql.Date empStart, java.sql.Date empEnd, int workedHours, int lateDays, int earlyLeaveDays) {
		super();
		this.hourlyWage = hourlyWage;
		this.dailyWage = dailyWage;
		this.monthlyWage = monthlyWage;
		Date = date;
		this.empName = empName;
		this.workingNow = workingNow;
		this.empStart = empStart;
		this.empEnd = empEnd;
		this.workedHours = workedHours;
		this.lateDays = lateDays;
		this.earlyLeaveDays = earlyLeaveDays;
	}

	public int getHourlyWage() {
		return hourlyWage;
	}

	public void setHourlyWage(int hourlyWage) {
		this.hourlyWage = hourlyWage;
	}

	public int getDailyWage() {
		return dailyWage;
	}

	public void setDailyWage(int dailyWage) {
		this.dailyWage = dailyWage;
	}

	public int getMonthlyWage() {
		return monthlyWage;
	}

	public void setMonthlyWage(int monthlyWage) {
		this.monthlyWage = monthlyWage;
	}

	public String getDate() {
		return Date;
	}

	public void setDate(String date) {
		Date = date;
	}

	public String getEmpName() {
		return empName;
	}

	public void setEmpName(String empName) {
		this.empName = empName;
	}

	public boolean isWorkingNow() {
		return workingNow;
	}

	public void setWorkingNow(boolean workingNow) {
		this.workingNow = workingNow;
	}

	public Date getEmpStart() {
		return empStart;
	}

	public void setEmpStart(Date empStart) {
		this.empStart = empStart;
	}

	public Date getEmpEnd() {
		return empEnd;
	}

	public void setEmpEnd(Date empEnd) {
		this.empEnd = empEnd;
	}

	public int getWorkedHours() {
		return workedHours;
	}

	public void setWorkedHours(int workedHours) {
		this.workedHours = workedHours;
	}

	public int getLateDays() {
		return lateDays;
	}

	public void setLateDays(int lateDays) {
		this.lateDays = lateDays;
	}

	public int getEarlyLeaveDays() {
		return earlyLeaveDays;
	}

	public void setEarlyLeaveDays(int earlyLeaveDays) {
		this.earlyLeaveDays = earlyLeaveDays;
	}
	
	

}
