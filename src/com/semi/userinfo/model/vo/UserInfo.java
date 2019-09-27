package com.semi.userinfo.model.vo;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Set;

import com.semi.bus.model.vo.Business;
import com.semi.chatting.model.vo.Chatting;
import com.semi.emp.model.service.EmpService;
import com.semi.noti.model.vo.Notification;
import com.semi.owner.model.service.OwnerService;
import com.semi.user.model.service.UserService;

public class UserInfo {

	private String userId;
	private String selectBusId;
	private HashMap<String, Business> busMap;
	private ArrayList<Notification> notiList;
	private ArrayList<Chatting> chatList;

	private String flag;

	public UserInfo() {
	}

	public UserInfo(String userId, String selectBusId, HashMap<String, Business> busMap,
			ArrayList<Notification> notiList, ArrayList<Chatting> chatList, String flag) {
		super();
		this.userId = userId;
		this.selectBusId = selectBusId;
		this.busMap = busMap;
		this.notiList = notiList;
		this.chatList = chatList;
		this.flag = flag;
	}

	public void getParameters(String userType) {
		String userId = this.getUserId();
		String selectBusId = this.getSelectBusId();

		HashMap<String, Business> busMap = null;
		if (userType.equals("O"))
			busMap = new OwnerService().getBusMap(userId);
		else
			busMap = new EmpService().getBusMap(userId);
		this.setBusMap(busMap);

		if (!busMap.isEmpty()) {
			if (selectBusId == null) {
				Set<String> set = busMap.keySet();
				Iterator<String> ir = set.iterator();
				this.setSelectBusId(ir.next());
			}
			ArrayList<Notification> notiList = new UserService().getNotiList(this.getSelectBusId(), this.getUserId());
			this.setNotiList(notiList);
		}
	}

	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	public String getSelectBusId() {
		return selectBusId;
	}

	public void setSelectBusId(String selectBusId) {
		this.selectBusId = selectBusId;
	}

	public HashMap<String, Business> getBusMap() {
		return busMap;
	}

	public void setBusMap(HashMap<String, Business> busMap) {
		this.busMap = busMap;
	}

	public ArrayList<Notification> getNotiList() {
		return notiList;
	}

	public void setNotiList(ArrayList<Notification> notiList) {
		this.notiList = notiList;
	}

	public ArrayList<Chatting> getChatList() {
		return chatList;
	}

	public void setChatList(ArrayList<Chatting> chatList) {
		this.chatList = chatList;
	}

	public String getFlag() {
		return flag;
	}

	public void setFlag(String flag) {
		this.flag = flag;
	}

}
