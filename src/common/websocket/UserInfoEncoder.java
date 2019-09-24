package common.websocket;

import javax.websocket.EncodeException;
import javax.websocket.Encoder;
import javax.websocket.EndpointConfig;

import com.google.gson.Gson;
import com.semi.userinfo.model.vo.UserInfo;

public class UserInfoEncoder implements Encoder.Text<UserInfo> {
	@Override
	public void destroy() {

	}

	@Override
	public void init(EndpointConfig arg0) {

	}

	@Override
	public String encode(UserInfo object) throws EncodeException {
		return new Gson().toJson(object);
	}
}
