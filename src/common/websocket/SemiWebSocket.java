package common.websocket;

import javax.websocket.OnOpen;
import javax.websocket.Session;
import javax.websocket.server.ServerEndpoint;

@ServerEndpoint(value = "/ws")
public class SemiWebSocket {

	@OnOpen
	public void onOpen(Session session) {
		
	}
	
	
}
