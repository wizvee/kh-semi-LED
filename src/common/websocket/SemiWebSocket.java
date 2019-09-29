package common.websocket;

import java.io.IOException;
import java.util.HashMap;

import javax.servlet.http.HttpSession;
import javax.websocket.EndpointConfig;
import javax.websocket.OnMessage;
import javax.websocket.OnOpen;
import javax.websocket.Session;
import javax.websocket.server.ServerEndpoint;

import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import com.semi.chatting.model.vo.Chatting;
import com.semi.userinfo.model.vo.UserInfo;

@ServerEndpoint(value = "/ws", configurator = GetHttpSession.class)
public class SemiWebSocket {

	private static final HashMap<Session, HttpSession> SESSIONS = new HashMap<>();
	private static final HashMap<String, Session> USERS = new HashMap<>();
	JsonParser jsonParser = new JsonParser();
	Chatting chat=new Chatting();
	

	@OnOpen
	public void onOpen(Session session, EndpointConfig config) {
		HttpSession hs = (HttpSession) config.getUserProperties().get(HttpSession.class.getName());
		UserInfo ui = (UserInfo) hs.getAttribute("userInfo");
		SESSIONS.put(session, hs);
		USERS.put(ui.getUserId(), session);
		try {
			session.getBasicRemote().sendText("연결");
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	@OnMessage
	public void onMessage(Session session, String str) {
//		String flag = websocket.getFlag();
//		if (flag.equals("T")) {
//			HashSet<String> to = new NotiService().getAlertTarget(userInfo.getSelectBusId());
//			for (String id : to) {
//				if (USERS.get(id) != null) {
//					HttpSession hs = SESSIONS.get(USERS.get(id));
//					UserInfo ui = (UserInfo) hs.getAttribute("userInfo");
//					ArrayList<Notification> newList = new UserService().getNotiList(id);
//					ui.setNotiList(newList);
//					hs.setAttribute("userInfo", ui);
//					try {
//						session.getBasicRemote().sendText(String.valueOf(newList.size()));
//					} catch (IOException e) {
//						e.printStackTrace();
//					}
//				}
//			}
//		}
		JsonObject jsonObject = (JsonObject) jsonParser.parse(str);
		String flag=jsonObject.get("flag").toString();
		if (flag=="C") {
			chat.setBusId(jsonObject.get("busId").toString());
			chat.setUserId(jsonObject.get("userId").toString());
			chat.setChatType(jsonObject.get("chatType").toString());
			chat.setChatMsg(jsonObject.get("chatType").toString());
			chat.setReaded(jsonObject.get("chatType").toString()=="F"?false:true);
		}
		
		
		
		
	}

}
