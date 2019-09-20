package com.semi.emp.model.vo;

import java.util.ArrayList;

import com.semi.bus.model.vo.Business;
import com.semi.user.model.vo.User;

public class Employee extends User {

	private ArrayList<Business> busList;

	public Employee() {
	}

	public Employee(ArrayList<Business> busList) {
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
