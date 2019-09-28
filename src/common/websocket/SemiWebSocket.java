package common.websocket;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;

import javax.servlet.http.HttpSession;
import javax.websocket.EndpointConfig;
import javax.websocket.OnMessage;
import javax.websocket.OnOpen;
import javax.websocket.Session;
import javax.websocket.server.ServerEndpoint;

import com.semi.noti.model.service.NotiService;
import com.semi.noti.model.vo.Notification;
import com.semi.user.model.service.UserService;
import com.semi.userinfo.model.vo.UserInfo;

@ServerEndpoint(value = "/ws", configurator = GetHttpSession.class, encoders = { UserInfoEncoder.class }, decoders = {
		UserInfoDecoder.class })
public class SemiWebSocket {

	private static final HashMap<Session, HttpSession> SESSIONS = new HashMap<>();
	private static final HashMap<String, Session> USERS = new HashMap<>();

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
	public void onMessage(Session session, UserInfo userInfo) {
		String flag = userInfo.getFlag();
		if (flag.equals("T")) {
			HashSet<String> to = new NotiService().getAlertTarget(userInfo.getSelectBusId());
			for (String id : to) {
				if (USERS.get(id) != null) {
					HttpSession hs = SESSIONS.get(USERS.get(id));
					UserInfo ui = (UserInfo) hs.getAttribute("userInfo");
					ArrayList<Notification> newList = new UserService().getNotiList(id);
					ui.setNotiList(newList);
					hs.setAttribute("userInfo", ui);
					try {
						session.getBasicRemote().sendText(String.valueOf(newList.size()));
					} catch (IOException e) {
						e.printStackTrace();
					}
				}
			}
		}
	}

}
