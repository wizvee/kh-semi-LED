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
import com.semi.chatting.model.service.ChattingService;
import com.semi.chatting.model.vo.Chatting;
import com.semi.userinfo.model.vo.UserInfo;

@ServerEndpoint(value = "/ws", configurator = GetHttpSession.class)
public class SemiWebSocket {

	private static final HashMap<Session, HttpSession> SESSIONS = new HashMap<>();
	private static final HashMap<String, Session> USERS = new HashMap<>();
	JsonParser jsonParser = new JsonParser();
	ChattingService service=new ChattingService();
	
	

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
		JsonObject jsonObject = (JsonObject) jsonParser.parse(str);
		String flag=jsonObject.get("flag").toString();
		
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
			System.out.println(flag);
			
			System.out.println("여기까진옴?");
			chat.setBusId(jsonObject.get("busId").toString());
			chat.setUserId(jsonObject.get("userId").toString());
			chat.setChatType(jsonObject.get("chatType").toString());
			chat.setChatMsg(jsonObject.get("chatMsg").toString());
			chat.setReaded(jsonObject.get("readed").toString());
			session.getUserProperties().put("chatting", chat);
			
			for(Session s:session.getOpenSessions()) {
				if(s.getUserProperties().get("chatting")!=null) {
					Chatting c=(Chatting)s.getUserProperties().get("chatting");
					if(c.getBusId().equals(chat.getBusId())) {
						try {
							System.out.println("여기까지오나?");
							s.getBasicRemote().sendObject(chat);
						}catch(Exception e) {
							e.printStackTrace();
						}
					}
				}
			int result=service.insertChat(chat);
			System.out.println("저장결과:"+result);
		}
		
		
		
		
	}

}
