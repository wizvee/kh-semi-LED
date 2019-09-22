package com.semi.user.model.vo;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Set;

import com.semi.bus.model.vo.Business;
import com.semi.emp.model.service.EmpService;
import com.semi.noti.model.vo.Notification;
import com.semi.owner.model.service.OwnerService;
import com.semi.user.model.service.UserService;
import com.semi.userinfo.model.vo.UserInfo;

public class User {

	private String userId;
	private String email;
	private String password;
	private String userName;
	private String userPhone;
	private String userType;
	private boolean mailCheck;
	private String profilePic;
	private Date joinDate;

	public User() {
	}

	public User(String userId, String email, String password, String userName, String userPhone, String userType,
			boolean mailCheck, String profilePic, Date joinDate) {
		super();
		this.userId = userId;
		this.email = email;
		this.password = password;
		this.userName = userName;
		this.userPhone = userPhone;
		this.userType = userType;
		this.mailCheck = mailCheck;
		this.profilePic = profilePic;
		this.joinDate = joinDate;
	}

	public UserInfo getUserInfo(String userId, String userType) {
		UserInfo ui = new UserInfo();
		ui.setUserId(userId);
		ui.getParameters(userType);
		
		return ui;
	}

	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getUserPhone() {
		return userPhone;
	}

	public void setUserPhone(String userPhone) {
		this.userPhone = userPhone;
	}

	public String getUserType() {
		return userType;
	}

	public void setUserType(String userType) {
		this.userType = userType;
	}

	public boolean isMailCheck() {
		return mailCheck;
	}

	public void setMailCheck(boolean mailCheck) {
		this.mailCheck = mailCheck;
	}

	public String getProfilePic() {
		return profilePic;
	}

	public void setProfilePic(String profilePic) {
		this.profilePic = profilePic;
	}

	public Date getJoinDate() {
		return joinDate;
	}

	public void setJoinDate(Date joinDate) {
		this.joinDate = joinDate;
	}

}
