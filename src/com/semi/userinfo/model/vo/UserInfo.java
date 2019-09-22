package com.semi.userinfo.model.vo;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Set;

import com.semi.bus.model.vo.Business;
import com.semi.emp.model.service.EmpService;
import com.semi.message.model.vo.Message;
import com.semi.noti.model.vo.Notification;
import com.semi.owner.model.service.OwnerService;
import com.semi.user.model.service.UserService;

public class UserInfo {

	private String userId;
	private String selectBusId;
	private HashMap<String, Business> busMap;
	private ArrayList<Notification> notiList;
	private ArrayList<Message> msgList;

	public UserInfo() {
	}

	public UserInfo(String userId, String selectBusId, HashMap<String, Business> busMap, ArrayList<Message> msgList,
			ArrayList<Notification> notiList) {
		super();
		this.userId = userId;
		this.selectBusId = selectBusId;
		this.busMap = busMap;
		this.msgList = msgList;
		this.notiList = notiList;
	}

	public void getParameters(String userType) {
		String userId = this.getUserId();
		String selectBusId = this.getSelectBusId();
		
		HashMap<String, Business> busMap = null;
		if (userType.equals("O"))
			busMap = new EmpService().getBusMap(userId);
		else
			busMap = new OwnerService().getBusMap(userId);
		this.setBusMap(busMap);

		if (!busMap.isEmpty()) {
			if (selectBusId == null) {
				Set<String> set = busMap.keySet();
				Iterator<String> ir = set.iterator();
				this.setSelectBusId(ir.next());
			}
			ArrayList<Notification> notiList = new UserService().getNotiList(this.getSelectBusId());
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

	public ArrayList<Message> getMsgList() {
		return msgList;
	}

	public void setMsgList(ArrayList<Message> msgList) {
		this.msgList = msgList;
	}

	public ArrayList<Notification> getNotiList() {
		return notiList;
	}

	public void setNotiList(ArrayList<Notification> notiList) {
		this.notiList = notiList;
	}

}
