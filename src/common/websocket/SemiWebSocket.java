package common.websocket;

import java.io.IOException;

import javax.websocket.OnMessage;
import javax.websocket.OnOpen;
import javax.websocket.Session;
import javax.websocket.server.ServerEndpoint;

import com.google.gson.Gson;
import com.semi.userinfo.model.vo.UserInfo;

@ServerEndpoint(value = "/ws", encoders = { UserInfoEncoder.class }, decoders = { UserInfoDecoder.class })
public class SemiWebSocket {

	@OnOpen
	public void onOpen(Session session) {
		try {
			session.getBasicRemote().sendText("zz");
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	@OnMessage
	public void onMessage(Session session, UserInfo userInfo) {
		try {
			session.getBasicRemote().sendText("noti");
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

}
