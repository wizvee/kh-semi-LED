package com.semi.caldendar.model.vo;

import java.util.ArrayList;
import java.util.Date;

import com.semi.task.model.vo.Task;

public class Cal {

	private String calId;
	private Date calDate;
	private String busId;
	private String sftId;
	private String calTitle;
	private String calDetail;
	private boolean done;

	private String sftName;
	private ArrayList<Task> taskList;

	public Cal() {
	}

	public Cal(String calId, Date calDate, String busId, String sftId, String calTitle, String calDetail, boolean done,
			String sftName, ArrayList<Task> taskList) {
		super();
		this.calId = calId;
		this.calDate = calDate;
		this.busId = busId;
		this.sftId = sftId;
		this.calTitle = calTitle;
		this.calDetail = calDetail;
		this.done = done;
		this.sftName = sftName;
		this.taskList = taskList;
	}

	public String getCalId() {
		return calId;
	}

	public void setCalId(String calId) {
		this.calId = calId;
	}

	public Date getCalDate() {
		return calDate;
	}

	public void setCalDate(Date calDate) {
		this.calDate = calDate;
	}

	public String getBusId() {
		return busId;
	}

	public void setBusId(String busId) {
		this.busId = busId;
	}

	public String getSftId() {
		return sftId;
	}

	public void setSftId(String sftId) {
		this.sftId = sftId;
	}

	public String getCalTitle() {
		return calTitle;
	}

	public void setCalTitle(String calTitle) {
		this.calTitle = calTitle;
	}

	public String getCalDetail() {
		return calDetail;
	}

	public void setCalDetail(String calDetail) {
		this.calDetail = calDetail;
	}

	public boolean isDone() {
		return done;
	}

	public void setDone(boolean done) {
		this.done = done;
	}

	public String getSftName() {
		return sftName;
	}

	public void setSftName(String sftName) {
		this.sftName = sftName;
	}

	public ArrayList<Task> getTaskList() {
		return taskList;
	}

	public void setTaskList(ArrayList<Task> taskList) {
		this.taskList = taskList;
	}

}
