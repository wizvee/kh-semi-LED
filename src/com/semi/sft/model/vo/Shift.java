package com.semi.sft.model.vo;

public class Shift {

	private String sftId;
	private String busId;
	private String sftName;
	private String sftDay;
	private String sftOn;
	private String sftOff;
	private int sftTime;

	private String atdOn;
	private String atdOff;

	public Shift() {
	}

	public Shift(String sftId, String busId, String sftName, String sftDay, String sftOn, String sftOff, int sftTime,
			String atdOn, String atdOff) {
		super();
		this.sftId = sftId;
		this.busId = busId;
		this.sftName = sftName;
		this.sftDay = sftDay;
		this.sftOn = sftOn;
		this.sftOff = sftOff;
		this.sftTime = sftTime;
		this.atdOn = atdOn;
		this.atdOff = atdOff;
	}

	public String getSftId() {
		return sftId;
	}

	public void setSftId(String sftId) {
		this.sftId = sftId;
	}

	public String getBusId() {
		return busId;
	}

	public void setBusId(String busId) {
		this.busId = busId;
	}

	public String getSftName() {
		return sftName;
	}

	public void setSftName(String sftName) {
		this.sftName = sftName;
	}

	public String getSftDay() {
		return sftDay;
	}

	public void setSftDay(String sftDay) {
		this.sftDay = sftDay;
	}

	public String getSftOn() {
		return sftOn;
	}

	public void setSftOn(String sftOn) {
		this.sftOn = sftOn;
	}

	public String getSftOff() {
		return sftOff;
	}

	public void setSftOff(String sftOff) {
		this.sftOff = sftOff;
	}

	public int getSftTime() {
		return sftTime;
	}

	public void setSftTime(int sftTime) {
		this.sftTime = sftTime;
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

}
