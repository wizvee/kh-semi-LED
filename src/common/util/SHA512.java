package common.util;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class SHA512 {

	public static String getSHA512(String input) {
		StringBuffer result = new StringBuffer();
		try {
			MessageDigest digest = MessageDigest.getInstance("SHA-512");
			byte[] salt = "Hello! This is Salt.".getBytes();
			digest.reset();
			digest.update(salt);
			byte[] chars = digest.digest(input.getBytes("utf-8"));
			for (int i = 0; i < chars.length; i++) {
				String hex = Integer.toHexString(0xff & chars[i]);
				if (hex.length() == 1)
					result.append("0");
				result.append(hex);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return result.toString();
	}

}
