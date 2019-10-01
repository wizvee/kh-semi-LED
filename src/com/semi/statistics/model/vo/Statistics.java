package com.semi.statistics.model.vo;

import java.util.List;

public class Statistics {
	
	private int totalWage;
	private String Date;
	private String empName;
	private boolean workingNow;
	private String empStart;
	private String empStartStr;
	private String empEnd;
	private String empEndStr;
	private int hourlyWorkedHours;
	private int dailyWorkedHours;
	private int monthlyWorkedHours;
	private int totalWorkedHours;
	private int lateDays;
	private int earlyLeaveDays;
	private int empCount;
	
	private List<StatisticsMonth> years;
	
	
	public Statistics() {
		//TODO Auto-generated constructor stub
	}

	
	public Statistics(int totalWage, String date, String empName, boolean workingNow, String empStart,
			String empEnd, int hourlyWorkedHours, int dailyWorkedHours, int monthlyWorkedHours,
			int totalWorkedHours, int lateDays, int earlyLeaveDays, int empCount, List<StatisticsMonth> years) {
		super();
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
		this.empCount = empCount;
		this.years = years;
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


	public String getEmpStart() {
		return empStart;
	}


	public void setEmpStart(String empStart) {
		this.empStart = empStart;
//		if(empStart!=null) {
//			SimpleDateFormat s=new SimpleDateFormat("yyyy-MM-dd ");
//			this.empStartStr=s.format(empStart);
//		}
	}


	public String getEmpEnd() {
		return empEnd;
	}


	public void setEmpEnd(String empEnd) {
		this.empEnd = empEnd;
//		if(empEnd!=null) {
//			SimpleDateFormat s=new SimpleDateFormat("yyyy-MM-dd");
//			this.empEndStr=s.format(empEnd);
//		}
	}

	
	
	public String getEmpStartStr() {
		return empStartStr;
	}


	public String getEmpEndStr() {
		return empEndStr;
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


	public List<StatisticsMonth> getYears() {
		return years;
	}


	public void setYears(List<StatisticsMonth> years) {
		this.years = years;
	}







	

	
	
	

}
