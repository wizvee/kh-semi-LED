package com.semi.bus.model.vo;

import java.io.Serializable;

public class Business implements Serializable {

	private String busId;
	private String ownId;
	private String busName;
	private String busNum;
	private String busAddr;
	private String busPhone;

	public Business() {
	}

	public Business(String busId, String ownId, String busName, String busNum, String busAddr, String busPhone) {
		super();
		this.busId = busId;
		this.ownId = ownId;
		this.busName = busName;
		this.busNum = busNum;
		this.busAddr = busAddr;
		this.busPhone = busPhone;
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

}
