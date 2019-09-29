package com.semi.task.model.vo;

import java.util.Date;

public class Task {

	private String taskId;
	private Date taskDate;
	private String calId;
	private String userId;
	private String taskMsg;
	private boolean done;

	private String userName;

	public Task() {
	}

	public Task(String taskId, Date taskDate, String calId, String userId, String taskMsg, boolean done,
			String userName) {
		super();
		this.taskId = taskId;
		this.taskDate = taskDate;
		this.calId = calId;
		this.userId = userId;
		this.taskMsg = taskMsg;
		this.done = done;
		this.userName = userName;
	}

	public String getTaskId() {
		return taskId;
	}

	public void setTaskId(String taskId) {
		this.taskId = taskId;
	}

	public Date getTaskDate() {
		return taskDate;
	}

	public void setTaskDate(Date taskDate) {
		this.taskDate = taskDate;
	}

	public String getCalId() {
		return calId;
	}

	public void setCalId(String calId) {
		this.calId = calId;
	}

	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	public String getTaskMsg() {
		return taskMsg;
	}

	public void setTaskMsg(String taskMsg) {
		this.taskMsg = taskMsg;
	}

	public boolean isDone() {
		return done;
	}

	public void setDone(boolean done) {
		this.done = done;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

}
