package common.websocket;

import javax.websocket.DecodeException;
import javax.websocket.Decoder;
import javax.websocket.EndpointConfig;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.semi.userinfo.model.vo.UserInfo;

public class UserInfoDecoder implements Decoder.Text<UserInfo> {

	@Override
	public void destroy() {

	}

	@Override
	public void init(EndpointConfig ec) {

	}

	@Override
	public UserInfo decode(String s) throws DecodeException {
		Gson gs = new GsonBuilder().setDateFormat("yyyy-MM-dd HH:mm:ss").create();
		return gs.fromJson(s, UserInfo.class);
	}

	@Override
	public boolean willDecode(String s) {
		return true;
	}

}
