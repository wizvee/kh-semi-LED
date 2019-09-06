package com.semi.emp.model.vo;

import java.util.ArrayList;

import com.semi.user.model.vo.User;

public class Employee extends User {

	private ArrayList<String> busList;

	public Employee() {
	}

	public Employee(ArrayList<String> busList) {
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
