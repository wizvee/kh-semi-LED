package common.websocket;

import java.util.HashMap;
import java.util.HashSet;

import javax.servlet.http.HttpSession;
import javax.websocket.EndpointConfig;
import javax.websocket.OnMessage;
import javax.websocket.Session;
import javax.websocket.server.ServerEndpoint;

import com.semi.noti.model.service.NotiService;
import com.semi.userinfo.model.vo.UserInfo;

@ServerEndpoint(value = "/ws", configurator = GetHttpSession.class, encoders = { UserInfoEncoder.class }, decoders = {
		UserInfoDecoder.class })
public class SemiWebSocket {

	private static final HashMap<String, Session> USERS = new HashMap<>();
	private static int count = 0;

	@OnMessage
	public void onMessage(Session session, EndpointConfig config, UserInfo userInfo) {
		String flag = userInfo.getFlag();
		if (flag.equals("S")) {
			USERS.put(userInfo.getUserId(), session);
			System.out.println(++count);

		} else if (flag.equals("N")) {
			HashSet<String> userSet = new NotiService().getAlertTarget(userInfo.getSelectBusId());
			for (String id : userSet) {
				if (USERS.get(id) != null) {
					HttpSession hs = (HttpSession) USERS.get(id);
					UserInfo ui = (UserInfo) hs.getAttribute("userInfo");
					System.out.println(ui.getSelectBusId());
				}
			}
		} else if (flag.equals("T")) {
			HashSet<String> userSet = new NotiService().getAlertTarget(userInfo.getSelectBusId());
			for (String id : userSet) {
				if (USERS.get(id) != null) {
					HttpSession hs = (HttpSession) config.getUserProperties().get(HttpSession.class.getName());
					UserInfo ui = (UserInfo) hs.getAttribute("userInfo");
					System.out.println(ui.getSelectBusId());
				}
			}
		}
	}

}
