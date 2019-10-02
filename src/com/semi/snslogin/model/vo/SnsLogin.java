package com.semi.snslogin.model.vo;

public class SnsLogin {
	
	private String id;
	private String imageUrl;
	private String email;
	
	public SnsLogin() {
		// TODO Auto-generated constructor stub
	}

	public SnsLogin(String id, String imageUrl, String email) {
		super();
		this.id = id;
		this.imageUrl = imageUrl;
		this.email = email;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getImageUrl() {
		return imageUrl;
	}

	public void setImageUrl(String imageUrl) {
		this.imageUrl = imageUrl;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}
	
	

}
