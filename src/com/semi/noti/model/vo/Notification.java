package com.semi.noti.model.vo;

import java.util.Date;

public class Notification {

	private String notiId;
	private String userId;
	private String targetUserId;
	private String targetBusId;
	private String notiType;
	private String notiMsg;
	private String notiUrl;
	private Date notiDate;
	private boolean readed;

	private String userName;
	private String profilePic;

	public Notification() {
	}

	public Notification(String notiId, String userId, String targetUserId, String targetBusId, String notiType,
			String notiMsg, String notiUrl, Date notiDate, boolean readed, String userName, String profilePic) {
		super();
		this.notiId = notiId;
		this.userId = userId;
		this.targetUserId = targetUserId;
		this.targetBusId = targetBusId;
		this.notiType = notiType;
		this.notiMsg = notiMsg;
		this.notiUrl = notiUrl;
		this.notiDate = notiDate;
		this.readed = readed;
		this.userName = userName;
		this.profilePic = profilePic;
	}

	public String getNotiId() {
		return notiId;
	}

	public void setNotiId(String notiId) {
		this.notiId = notiId;
	}

	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	public String getTargetUserId() {
		return targetUserId;
	}

	public void setTargetUserId(String targetUserId) {
		this.targetUserId = targetUserId;
	}

	public String getTargetBusId() {
		return targetBusId;
	}

	public void setTargetBusId(String targetBusId) {
		this.targetBusId = targetBusId;
	}

	public String getNotiType() {
		return notiType;
	}

	public void setNotiType(String notiType) {
		this.notiType = notiType;
	}

	public String getNotiMsg() {
		return notiMsg;
	}

	public void setNotiMsg(String notiMsg) {
		this.notiMsg = notiMsg;
	}

	public String getNotiUrl() {
		return notiUrl;
	}

	public void setNotiUrl(String notiUrl) {
		this.notiUrl = notiUrl;
	}

	public Date getNotiDate() {
		return notiDate;
	}

	public void setNotiDate(Date notiDate) {
		this.notiDate = notiDate;
	}

	public boolean isReaded() {
		return readed;
	}

	public void setReaded(boolean readed) {
		this.readed = readed;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getProfilePic() {
		return profilePic;
	}

	public void setProfilePic(String profilePic) {
		this.profilePic = profilePic;
	}

}
