package com.semi.owner.model.vo;

import java.util.ArrayList;

import com.semi.bus.model.vo.Business;
import com.semi.user.model.vo.User;

public class Owner extends User {

	private ArrayList<Business> busList;

	public Owner() {
	}

	public Owner(ArrayList<Business> busList) {
		super();
		this.busList = busList;
	}

	public ArrayList<Business> getBusList() {
		return busList;
	}

	public void setBusList(ArrayList<Business> busList) {
		this.busList = busList;
	}

}
