package com.semi.bus.model.vo;

import java.io.Serializable;
import java.util.ArrayList;

import com.semi.emp.model.vo.Employee;

public class Business implements Serializable {

	private static final long serialVersionUID = 1L;
	private String busId;
	private String ownId;
	private String busName;
	private String busNum;
	private String busAddr;
	private String busPhone;
	private int busDate;

	private String ownName;
	private ArrayList<Employee> list;

	public Business() {
	}

	public Business(String busId, String ownId, String busName, String busNum, String busAddr, String busPhone,
			int busDate, String ownName, ArrayList<Employee> list) {
		super();
		this.busId = busId;
		this.ownId = ownId;
		this.busName = busName;
		this.busNum = busNum;
		this.busAddr = busAddr;
		this.busPhone = busPhone;
		this.busDate = busDate;
		this.ownName = ownName;
		this.list = list;
	}

	public String getBusId() {
		return busId;
	}

	public void setBusId(String busId) {
		this.busId = busId;
	}

	public String getOwnId() {
		return ownId;
	}

	public void setOwnId(String ownId) {
		this.ownId = ownId;
	}

	public String getBusName() {
		return busName;
	}

	public void setBusName(String busName) {
		this.busName = busName;
	}

	public String getBusNum() {
		return busNum;
	}

	public void setBusNum(String busNum) {
		this.busNum = busNum;
	}

	public String getBusAddr() {
		return busAddr;
	}

	public void setBusAddr(String busAddr) {
		this.busAddr = busAddr;
	}

	public String getBusPhone() {
		return busPhone;
	}

	public void setBusPhone(String busPhone) {
		this.busPhone = busPhone;
	}

	public int getBusDate() {
		return busDate;
	}

	public void setBusDate(int busDate) {
		this.busDate = busDate;
	}

	public String getOwnName() {
		return ownName;
	}

	public void setOwnName(String ownName) {
		this.ownName = ownName;
	}

	public ArrayList<Employee> getList() {
		return list;
	}

	public void setList(ArrayList<Employee> list) {
		this.list = list;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

}
