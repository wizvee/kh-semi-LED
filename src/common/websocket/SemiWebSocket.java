package common.websocket;

import javax.websocket.OnMessage;
import javax.websocket.OnOpen;
import javax.websocket.Session;
import javax.websocket.server.ServerEndpoint;

import com.semi.chatting.model.vo.Chatting;

@ServerEndpoint(value = "/ws")
public class SemiWebSocket {

	@OnOpen
	public void onOpen(Session session) {
		
	}
	

	
	
}
