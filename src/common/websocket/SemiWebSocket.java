package common.websocket;

import java.io.IOException;
import java.util.HashMap;

import javax.servlet.http.HttpSession;
import javax.websocket.EndpointConfig;
import javax.websocket.OnMessage;
import javax.websocket.OnOpen;
import javax.websocket.Session;
import javax.websocket.server.ServerEndpoint;

import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import com.google.gson.JsonParser;
import com.semi.chatting.model.service.ChattingService;
import com.semi.chatting.model.vo.Chatting;
import com.semi.userinfo.model.vo.UserInfo;

@ServerEndpoint(value = "/ws", configurator = GetHttpSession.class)
public class SemiWebSocket {

	
	JsonParser jsonParser = new JsonParser();
	ChattingService service = new ChattingService();


	@OnMessage
	public void onMessage(Session session, String str) {

		String flag = null;
		JSONParser jp = new JSONParser();
		JSONObject json = null;
		try {
			json = (JSONObject) jp.parse(str);

			flag = (String) json.get("flag");
		} catch (ParseException e) {
			e.printStackTrace();
		}

		if (flag.equals("C")) {
			String busId = (String) json.get("busId");
			String userId = (String) json.get("userId");
			String chatType = (String) json.get("chatType");
			String chatMsg = (String) json.get("chatMsg");
			String userName= (String) json.get("userName");
			String profilePic=(String) json.get("profilePic");
			session.getUserProperties().put("busId", busId);

			int result = service.insertChat(busId, userId, chatType, chatMsg);
			

			for (Session s : session.getOpenSessions()) {
				if (s.getUserProperties().get("busId") != null) {
					try {
						JSONObject jso=new JSONObject();
						jso.put("userName",userName);
						jso.put("profilePic",profilePic);
						jso.put("chatMsg",chatMsg);
						String jsonChat=jso.toJSONString();
						System.out.println(jsonChat);
						s.getBasicRemote().sendText(jsonChat);
					} catch (IOException e) {
						e.printStackTrace();
					}
				}
			}
		}

		if (flag.equals("T")) {
			String busId = (String) json.get("busId");
			String userId = (String) json.get("userId");
			String chatType = (String) json.get("chatType");
			String chatMsg = (String) json.get("chatMsg");

			int exist = service.checkTime(busId, chatType, chatMsg);
			if (exist != 1) {
				System.out.println("exist: " + exist);
				int insert = service.insertTime(busId, userId, chatType, chatMsg);
				System.out.println("insert: " + insert);
			} else {
				return;
			}
		}
		System.out.println(flag);
		if (flag.equals("N")) {
			System.out.println("??");
			for (Session s : session.getOpenSessions()) {
				try {
					s.getBasicRemote().sendText("N");
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}

//		JsonObject jsonObject = (JsonObject) jsonParser.parse(str);
//		String flag=jsonObject.get("flag").toString();

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

	}

}
