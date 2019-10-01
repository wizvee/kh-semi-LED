package com.semi.chatting.model.vo;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class Chatting {

	private String busId;
	private String userId;
	private String targetUserId;
	private String chatType;
	private String chatMsg;
	private Date chatDate;
	private String readed;

	private String userName;
	private String profilePic;

	public Chatting() {
	}

	public Chatting(String busId, String userId, String targetUserId, String chatType, String chatMsg, Date chatDate,
			String readed, String userName, String profilePic) {
		super();
		this.busId = busId;
		this.userId = userId;
		this.targetUserId = targetUserId;
		this.chatType = chatType;
		this.chatMsg = chatMsg;
		this.chatDate = chatDate;
		this.readed = readed;
		this.userName = userName;
		this.profilePic = profilePic;
		
	}

	public String getReaded() {
		return readed;
	}

	public void setReaded(String readed) {
		this.readed = readed;
	}

	public String getBusId() {
		return busId;
	}

	public void setBusId(String busId) {
		this.busId = busId;
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

	public String getChatType() {
		return chatType;
	}

	public void setChatType(String chatType) {
		this.chatType = chatType;
	}

	public String getChatMsg() {
		return chatMsg;
	}

	public void setChatMsg(String chatMsg) {
		this.chatMsg = chatMsg;
	}

	public Date getChatDate() {
		return chatDate;
	}

	public void setChatDate(String chatDate) {
		SimpleDateFormat simple=new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
		try {
			this.chatDate = simple.parse(chatDate);
		} catch (ParseException e) {
			e.printStackTrace();
		}
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
