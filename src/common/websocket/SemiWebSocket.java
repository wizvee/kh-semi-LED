package common.websocket;

import java.io.IOException;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Set;

import javax.websocket.OnMessage;
import javax.websocket.Session;
import javax.websocket.server.ServerEndpoint;

import com.semi.noti.model.service.NotiService;
import com.semi.userinfo.model.vo.UserInfo;

@ServerEndpoint(value = "/ws", encoders = { UserInfoEncoder.class }, decoders = { UserInfoDecoder.class })
public class SemiWebSocket {

	private HashMap<String, Session> users = new HashMap<>();

	@OnMessage
	public void onMessage(Session session, UserInfo userInfo) {
		String flag = userInfo.getFlag();
		if (flag.equals("S")) {
			users.put(userInfo.getUserId(), session);
			System.out.println(userInfo.getUserId());
		} else if(flag.equals("N")) {
			System.out.println("??");
			HashSet<String> userSet = new NotiService().getAlertTarget(userInfo.getSelectBusId());
			for(String id : userSet) {
				if(users.get(id) != null)
					try {
						users.get(id).getBasicRemote().sendText("N");
					} catch (IOException e) {
						e.printStackTrace();
					}
			}
		} else if(flag.equals("T")) {
			Set<String> id = users.keySet();
			for(String s : id) {
				try {
					users.get(s).getBasicRemote().sendText("T");
					System.out.println(s);
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}
	}

}
