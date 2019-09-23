package com.semi.sft.model.vo;

public class Shift {

	private String sftId;
	private String busId;
	private String sftName;
	private String sftDay;
	private String sftOn;
	private String sftOff;

	public Shift() {
	}

	public Shift(String sftId, String busId, String sftName, String sftDay, String sftOn, String sftOff) {
		super();
		this.sftId = sftId;
		this.busId = busId;
		this.sftName = sftName;
		this.sftDay = sftDay;
		this.sftOn = sftOn;
		this.sftOff = sftOff;
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

}
