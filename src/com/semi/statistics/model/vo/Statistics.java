package com.semi.statistics.model.vo;

import java.sql.Date;

public class Statistics {
	
	private int hourlyWage;
	private int dailyWage;
	private int monthlyWage;
	private int totalWage;
	private String Date;
	private String empName;
	private boolean workingNow;
	private Date empStart;
	private Date empEnd;
	private int hourlyWorkedHours;
	private int dailyWorkedHours;
	private int monthlyWorkedHours;
	private int totalWorkedHours;
	private int lateDays;
	private int earlyLeaveDays;
	private int empCount;
	
	public Statistics() {
		//TODO Auto-generated constructor stub
	}

	

	public Statistics(int hourlyWage, int dailyWage, int monthlyWage, int totalWage, String date, String empName,
			boolean workingNow, java.sql.Date empStart, java.sql.Date empEnd, int hourlyWorkedHours,
			int dailyWorkedHours, int monthlyWorkedHours, int totalWorkedHours, int lateDays, int earlyLeaveDays, 
			int empCount) {
		super();
		this.hourlyWage = hourlyWage;
		this.dailyWage = dailyWage;
		this.monthlyWage = monthlyWage;
		this.totalWage = totalWage;
		Date = date;
		this.empName = empName;
		this.workingNow = workingNow;
		this.empStart = empStart;
		this.empEnd = empEnd;
		this.hourlyWorkedHours = hourlyWorkedHours;
		this.dailyWorkedHours = dailyWorkedHours;
		this.monthlyWorkedHours = monthlyWorkedHours;
		this.totalWorkedHours = totalWorkedHours;
		this.lateDays = lateDays;
		this.earlyLeaveDays = earlyLeaveDays;
		this.empCount=empCount;
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



	public int getTotalWage() {
		return totalWage;
	}



	public void setTotalWage(int totalWage) {
		this.totalWage = totalWage;
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



	public int getHourlyWorkedHours() {
		return hourlyWorkedHours;
	}



	public void setHourlyWorkedHours(int hourlyWorkedHours) {
		this.hourlyWorkedHours = hourlyWorkedHours;
	}



	public int getDailyWorkedHours() {
		return dailyWorkedHours;
	}



	public void setDailyWorkedHours(int dailyWorkedHours) {
		this.dailyWorkedHours = dailyWorkedHours;
	}



	public int getMonthlyWorkedHours() {
		return monthlyWorkedHours;
	}



	public void setMonthlyWorkedHours(int monthlyWorkedHours) {
		this.monthlyWorkedHours = monthlyWorkedHours;
	}



	public int getTotalWorkedHours() {
		return totalWorkedHours;
	}



	public void setTotalWorkedHours(int totalWorkedHours) {
		this.totalWorkedHours = totalWorkedHours;
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



	public int getEmpCount() {
		return empCount;
	}



	public void setEmpCount(int empCount) {
		this.empCount = empCount;
	}
	
	

	

	
	
	

}
