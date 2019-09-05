package common.API;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;

public class APISearch {

	private String clientId = "W41bFDoFkUIW33_706Lv";// 애플리케이션 클라이언트 아이디값";
	private String clientSecret = "KRoY4TweyN";// 애플리케이션 클라이언트 시크릿값";

	public String search(String input) {
		StringBuffer response = null;
		try {
			String text = URLEncoder.encode(input, "UTF-8");
			String apiURL = "https://openapi.naver.com/v1/search/local?query=" + text; // json결과
			URL url = new URL(apiURL);
			HttpURLConnection con = (HttpURLConnection) url.openConnection();
			con.setRequestMethod("GET");
			con.setRequestProperty("X-Naver-Client-Id", clientId);
			con.setRequestProperty("X-Naver-Client-Secret", clientSecret);
			int responseCode = con.getResponseCode();
			BufferedReader br;
			if (responseCode == 200) { // 정상 호출
				br = new BufferedReader(new InputStreamReader(con.getInputStream()));
			} else { // 에러 발생
				br = new BufferedReader(new InputStreamReader(con.getErrorStream()));
			}
			String inputLine;
			response = new StringBuffer();
			while ((inputLine = br.readLine()) != null) {
				response.append(inputLine);
			}
			br.close();
//			System.out.println(response.toString());
		} catch (Exception e) {
			System.out.println(e);
		}
		return response.toString();
	}

}
