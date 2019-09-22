package common.websocket;

import java.io.IOException;
import java.util.ArrayList;

import javax.websocket.EncodeException;
import javax.websocket.OnOpen;
import javax.websocket.Session;
import javax.websocket.server.ServerEndpoint;

import com.google.gson.Gson;

@ServerEndpoint(value = "/ws")
public class SemiWebSocket {

	@OnOpen
	public void onOpen(Session session) {
		
	}
	
	
}
