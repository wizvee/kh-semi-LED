package com.semi.atd.model.vo;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class Attendance {

	private String busId;
	private String empId;

	private String userName;
	private String sftId;
	private String sftName;
	private String sftOn;
	private String sftOff;
	
	private String[] atdType;
	private String atdOn;
	private String atdOff;
	private int empWage;

	
	private long stAtdTime;
	private long enAtdTime;
	private long stSftTime;
	private long enSftTime;

	public Attendance() {
	}

	
	
	public Attendance(String busId, String empId, String userName, String sftId, String sftName, String sftOn,
			String sftOff, String[] atdType, String atdOn, String atdOff, int empWage, long stAtdTime, long enAtdTime,
			long stSftTime, long enSftTime) {
		super();
		this.busId = busId;
		this.empId = empId;
		this.userName = userName;
		this.sftId = sftId;
		this.sftName = sftName;
		this.sftOn = sftOn;
		this.sftOff = sftOff;
		this.atdType = atdType;
		this.atdOn = atdOn;
		this.atdOff = atdOff;
		this.empWage = empWage;
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



	public String getUserName() {
		return userName;
	}



	public void setUserName(String userName) {
		this.userName = userName;
	}



	public String getSftId() {
		return sftId;
	}



	public void setSftId(String sftId) {
		this.sftId = sftId;
	}



	public String getSftName() {
		return sftName;
	}



	public void setSftName(String sftName) {
		this.sftName = sftName;
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



	public String[] getAtdType() {
		return atdType;
	}



	public void setAtdType(String[] atdType) {
		this.atdType = atdType;
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



	public int getEmpWage() {
		return empWage;
	}



	public void setEmpWage(int empWage) {
		this.empWage = empWage;
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



	public void setAtdTimeForLong(String stAtdT, String enAtdT) {
		SimpleDateFormat sdf = new SimpleDateFormat("yyyymmddHHmm");

		try {
			this.setStAtdTime(sdf.parse(stAtdT).getTime() / (1000 * 60));
			this.setEnAtdTime(sdf.parse(enAtdT).getTime() / (1000 * 60));

		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	public void setTimeforLong(String stAtdT, String enAtdT, String stSftT, String enSftT) {

		Date enSftD = new Date();
		SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMddHHmm");
		Calendar cal = Calendar.getInstance();

		String[] stSfts = stSftT.split(":");
		String[] enSfts = enSftT.split(":");

		String stSft = stAtdT.substring(0, 8);
		String enSft = stAtdT.substring(0, 8);

		System.out.println("년 월 일 " + stSft);
		for (int i = 0; i < 2; i++) {
			stSft += stSfts[i];
			enSft += enSfts[i];
		}
		System.out.println("문자열 병합 : " + stSft);
		/// 값이 0으로 세팅됨 수정 해야함
		try {
			if (Integer.parseInt(stSfts[0]) > Integer.parseInt(enSfts[0])) {
				enSftD = sdf.parse(enSft);
				cal.setTime(enSftD);
				System.out.println("캘린더 값으로 parse " + cal);
				cal.add(Calendar.DATE, 1);
				System.out.println("다음 날짜로 변경 " + cal);
				this.setStAtdTime(sdf.parse(stAtdT).getTime() / (1000 * 60));
				this.setEnAtdTime(sdf.parse(enAtdT).getTime() / (1000 * 60));
				this.setStSftTime(sdf.parse(stSft).getTime() / (1000 * 60));
				this.setEnSftTime(cal.getTimeInMillis() / (1000 * 60));

			} else {

				this.setStAtdTime(sdf.parse(stAtdT).getTime() / (1000 * 60));
				this.setEnAtdTime(sdf.parse(enAtdT).getTime() / (1000 * 60));
				this.setStSftTime(sdf.parse(stSft).getTime() / (1000 * 60));
				this.setEnSftTime(sdf.parse(enSft).getTime() / (1000 * 60));

			}

		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		System.out.println("sft get으로 받아온 값 : " + this.getStSftTime());
		System.out.println("ensft : " + this.getEnSftTime());
		System.out.println("atd 계산값 : " + (this.getStAtdTime() - this.getEnAtdTime()));
	}

}
