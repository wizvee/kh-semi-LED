package com.semi.bus.model.vo;

public class Business {

	private String busId;
	private String ownId;
	private String busName;
	private String busAddr;
	private String busPhone;

	public Business() {
	}

	public Business(String busId, String ownId, String busName, String busAddr, String busPhone) {
		super();
		this.busId = busId;
		this.ownId = ownId;
		this.busName = busName;
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
