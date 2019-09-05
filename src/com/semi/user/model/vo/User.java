package com.semi.user.model.vo;

public class User {

	private String userId;
	private String email;
	private String password;
	private String userName;
	private String userPhone;
	private String userType;
	private String mailHash;
	private boolean mailCheck;
	private String profilePic;

	public User() {
	}

	public User(String userId, String email, String password, String userName, String userPhone, String userType,
			String mailHash, boolean mailCheck, String profilePic) {
		super();
		this.userId = userId;
		this.email = email;
		this.password = password;
		this.userName = userName;
		this.userPhone = userPhone;
		this.userType = userType;
		this.mailHash = mailHash;
		this.mailCheck = mailCheck;
		this.profilePic = profilePic;
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

	public String getMailHash() {
		return mailHash;
	}

	public void setMailHash(String mailHash) {
		this.mailHash = mailHash;
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

}
