package common.filter;

import java.io.UnsupportedEncodingException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Base64;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletRequestWrapper;

public class EncryptPasswordWrapper extends HttpServletRequestWrapper {

	public EncryptPasswordWrapper(HttpServletRequest request) {
		super(request);
	}
	
	@Override
	public String getParameter(String name) {
		String value = "";
		if(name != null && name.equals("password")) {
			value = getEncryptPw(super.getParameter(name));
		} else {
			value = super.getParameter(name);
		}
		return value;
	}
	
	public static String getEncryptPw(String pw) {
		MessageDigest md = null;
		try {
			md = MessageDigest.getInstance("SHA-512");
			byte[] bytes = pw.getBytes("UTF-8");
			md.update(bytes);
		} catch (NoSuchAlgorithmException e) {
			e.printStackTrace();
		} catch(UnsupportedEncodingException e) {
			e.printStackTrace();
		}
		String encPw = Base64.getEncoder().encodeToString(md.digest());
		return encPw;
	}

}
