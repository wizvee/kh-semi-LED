package com.semi.atd.model.vo;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class Attendance {

	private String busId;
	private String empId;
	private String atdOn;
	private String atdOff;
	private String overTime;
	private String attendance;
	private int dayWage;
	private String sftId;

	private long stAtdTime;
	private long enAtdTime;
	private long stSftTime;
	private long enSftTime;

	public Attendance() {
	}

	public Attendance(String busId, String empId, String atdOn, String atdOff, String overTime, String attendance,
			int dayWage, String sftId, long stAtdTime, long enAtdTime, long stSftTime, long enSftTime) {
		super();
		this.busId = busId;
		this.empId = empId;
		this.atdOn = atdOn;
		this.atdOff = atdOff;
		this.overTime = overTime;
		this.attendance = attendance;
		this.dayWage = dayWage;
		this.sftId = sftId;
		this.stAtdTime = stAtdTime;
		this.enAtdTime = enAtdTime;
		this.stSftTime = stSftTime;
		this.enSftTime = enSftTime;
	}

	public String getBusId() {
		return busId;
	}

	public void setBusId(String busId) {
		this.busId = busId;
	}

	public String getEmpId() {
		return empId;
	}

	public void setEmpId(String empId) {
		this.empId = empId;
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

	public String getOverTime() {
		return overTime;
	}

	public void setOverTime(String overTime) {
		this.overTime = overTime;
	}

	public String getAttendance() {
		return attendance;
	}

	public void setAttendance(String attendance) {
		this.attendance = attendance;
	}

	public int getDayWage() {
		return dayWage;
	}

	public void setDayWage(int dayWage) {
		this.dayWage = dayWage;
	}

	public String getSftId() {
		return sftId;
	}

	public void setSftId(String sftId) {
		this.sftId = sftId;
	}

	public long getStAtdTime() {
		return stAtdTime;
	}

	public void setStAtdTime(long stAtdTime) {
		this.stAtdTime = stAtdTime;
	}

	public long getEnAtdTime() {
		return enAtdTime;
	}

	public void setEnAtdTime(long enAtdTime) {
		this.enAtdTime = enAtdTime;
	}

	public long getStSftTime() {
		return stSftTime;
	}

	public void setStSftTime(long stSftTime) {
		this.stSftTime = stSftTime;
	}

	public long getEnSftTime() {
		return enSftTime;
	}

	public void setEnSftTime(long enSftTime) {
		this.enSftTime = enSftTime;
	}

	public void setTimeforLong(String stAtdTime, String enAtdTime, String stSftTime, String enSftTime) {

		Date enSftD = new Date();
		SimpleDateFormat sdf = new SimpleDateFormat("yyyymmddHHmmss");
		Calendar cal = Calendar.getInstance();

		String[] stSfts = stSftTime.split(":");
		String[] enSfts = enSftTime.split(":");

		String stSft = stAtdTime.substring(0, 8);
		String enSft = stAtdTime.substring(0, 8);

		for (int i = 0; i < 2; i++) {
			stSft += stSfts[i];
			enSft += stSfts[i];
		}

		try {
			if (Integer.parseInt(stSfts[0]) > Integer.parseInt(enSfts[0])) {
				cal.setTime(enSftD);
				cal.add(Calendar.DATE, 1);

				this.setStAtdTime(sdf.parse(stAtdTime).getTime());
				this.setEnAtdTime(sdf.parse(enAtdTime).getTime());
				this.setStSftTime(sdf.parse(stSftTime).getTime());
				this.setEnSftTime(cal.getTimeInMillis());

			} else {

				this.setStAtdTime(sdf.parse(stAtdTime).getTime());
				this.setEnAtdTime(sdf.parse(enAtdTime).getTime());
				this.setStSftTime(sdf.parse(stSftTime).getTime());
				this.setEnSftTime(sdf.parse(enSftTime).getTime());

			}

		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	
}
