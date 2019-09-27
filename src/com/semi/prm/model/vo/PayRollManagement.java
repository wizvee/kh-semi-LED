package com.semi.prm.model.vo;

public class PayRollManagement {

	private String empName;
	private int payRoll;
	private int workTime;
	private int lateCount;
	private int earlyCount;

	
	public PayRollManagement() {
		// TODO Auto-generated constructor stub
	}


	public PayRollManagement(String empName, int payRoll, int workTime, int lateCount, int earlyCount) {
		super();
		this.empName = empName;
		this.payRoll = payRoll;
		this.workTime = workTime;
		this.lateCount = lateCount;
		this.earlyCount = earlyCount;
	}


	public String getEmpName() {
		return empName;
	}


	public void setEmpName(String empName) {
		this.empName = empName;
	}


	public int getPayRoll() {
		return payRoll;
	}


	public void setPayRoll(int payRoll) {
		this.payRoll = payRoll;
	}


	public int getWorkTime() {
		return workTime;
	}


	public void setWorkTime(int workTime) {
		this.workTime = workTime;
	}


	public int getLateCount() {
		return lateCount;
	}


	public void setLateCount(int lateCount) {
		this.lateCount = lateCount;
	}


	public int getEarlyCount() {
		return earlyCount;
	}


	public void setEarlyCount(int earlyCount) {
		this.earlyCount = earlyCount;
	}

	
	
}
