package common.websocket;

import java.io.IOException;

import javax.websocket.OnMessage;
import javax.websocket.OnOpen;
import javax.websocket.Session;
import javax.websocket.server.ServerEndpoint;

@ServerEndpoint(value = "/ws", encoders = {UserInfoEncoder.class}, decoders = {UserInfoDecoder.class})
public class SemiWebSocket {

	@OnOpen
	public void onOpen(Session session) {
		try {
			session.getBasicRemote().sendText("zz");
		} catch (IOException e) {
			e.printStackTrace();
		}
	}	

}
