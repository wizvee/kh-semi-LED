package com.semi.statistics.model.vo;

import java.sql.Date;

public class StatisticsDays {
	
	private String year;
	private String month;
	private int sumWage;
	
	public StatisticsDays() {
		// TODO Auto-generated constructor stub
	}



	public StatisticsDays(String year, String month, int sumWage) {
		super();
		this.year = year;
		this.month = month;
		this.sumWage = sumWage;
	}



	public String getYear() {
		return year;
	}



	public void setYear(String year) {
		this.year = year;
	}



	public String getMonth() {
		return month;
	}



	public void setMonth(String month) {
		this.month = month;
	}



	public int getSumWage() {
		return sumWage;
	}



	public void setSumWage(int sumWage) {
		this.sumWage = sumWage;
	}


	
	
}
