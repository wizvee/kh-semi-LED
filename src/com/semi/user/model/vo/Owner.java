package com.semi.user.model.vo;

import java.util.ArrayList;

public class Owner extends User {

	private ArrayList<String> busList;

	public Owner() {
	}

	public Owner(ArrayList<String> busList) {
		super();
		this.busList = busList;
	}

	public ArrayList<String> getBusList() {
		return busList;
	}

	public void setBusList(ArrayList<String> busList) {
		this.busList = busList;
	}

}
