package com.semi.user.controller;

import java.io.FileReader;
import java.io.IOException;
import java.util.Properties;

import javax.mail.Address;
import javax.mail.Authenticator;
import javax.mail.Message;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import common.util.Gmail;
import common.util.SHA512;

@WebServlet("/emailSend.do")
public class EmailSendServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	public EmailSendServlet() {
		super();
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String email = request.getParameter("email");

		String from = "albarang1002@gmail.com";
		String subject = "이메일 인증";
		String content = "안녕하세요!<br>";
		content += "저희 홈페이지에 가입해주셔서 감사합니다.<br><br>";
		content += "이메일 인증을 완료하기 위해, 아래 링크를 클릭해 주세요.<br>";
		content += "<a href='http://rclass.iptime.org:9999/" + request.getContextPath() + "/token.do?token="
				+ SHA512.getSHA512(email) + "&email=" + email + "'>인증하기</a>";

		Properties prop = new Properties();
		String path = this.getClass().getClassLoader().getResource("/common/util/email.properties").getPath();
		prop.load(new FileReader(path));

		Authenticator auth = new Gmail();
		Session ses = Session.getInstance(prop, auth);
		MimeMessage msg = new MimeMessage(ses);
		try {
			msg.setSubject(subject);
			Address fromAddr = new InternetAddress(from);
			msg.setFrom(fromAddr);
			Address toAddr = new InternetAddress(email);
			msg.addRecipient(Message.RecipientType.TO, toAddr);
			msg.setContent(content, "text/html; charset=UTF-8");
			Transport.send(msg);

			request.setAttribute("send", true);
		} catch (Exception e) {
			e.printStackTrace();
			request.setAttribute("send", false);
		}
		request.getRequestDispatcher("views/common/emailCheck.jsp").forward(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doGet(request, response);
	}

}
