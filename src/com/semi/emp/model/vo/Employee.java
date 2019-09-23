package com.semi.emp.model.vo;

import java.util.ArrayList;

import com.semi.atd.model.vo.Attendance;
import com.semi.bus.model.vo.Business;
import com.semi.sft.model.vo.Shift;
import com.semi.user.model.vo.User;

public class Employee extends User {

	private String empType;
	private int empWage;
	private String bankAccount;
	private String empLevel;
	private String sftId;

	private Shift shift;
	private Attendance attendance;
	private ArrayList<Business> busList;
	private ArrayList<Integer> wageList;

	public Employee() {
	}

	public Employee(String empType, int empWage, String bankAccount, String empLevel, String sftId, Shift shift, Attendance attendance,
			ArrayList<Business> busList, ArrayList<Integer> wageList) {
		super();
		this.empType = empType;
		this.empWage = empWage;
		this.bankAccount = bankAccount;
		this.empLevel = empLevel;
		this.sftId = sftId;
		this.shift = shift;
		this.attendance = attendance;
		this.busList = busList;
		this.wageList = wageList;
	}

	public String getEmpType() {
		return empType;
	}

	public void setEmpType(String empType) {
		this.empType = empType;
	}

	public int getEmpWage() {
		return empWage;
	}

	public void setEmpWage(int empWage) {
		this.empWage = empWage;
	}

	public String getBankAccount() {
		return bankAccount;
	}

	public void setBankAccount(String bankAccount) {
		this.bankAccount = bankAccount;
	}

	public String getEmpLevel() {
		return empLevel;
	}

	public void setEmpLevel(String empLevel) {
		this.empLevel = empLevel;
	}

	public String getSftId() {
		return sftId;
	}

	public void setSftId(String sftId) {
		this.sftId = sftId;
	}

	public Shift getShift() {
		return shift;
	}

	public void setShift(Shift shift) {
		this.shift = shift;
	}
	

	public Attendance getAttendance() {
		return attendance;
	}

	public void setAttendance(Attendance attendance) {
		this.attendance = attendance;
	}

	public ArrayList<Business> getBusList() {
		return busList;
	}

	public void setBusList(ArrayList<Business> busList) {
		this.busList = busList;
	}

	public ArrayList<Integer> getWageList() {
		return wageList;
	}

	public void setWageList(ArrayList<Integer> wageList) {
		this.wageList = wageList;
	}

}
