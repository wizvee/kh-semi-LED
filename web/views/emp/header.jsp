<%@page import="com.google.gson.GsonBuilder"%>
<%@page import="com.semi.noti.model.vo.Notification"%>
<%@page import="java.util.ArrayList"%>
<%@page import="java.util.Map"%>
<%@page import="java.util.HashMap"%>
<%@page import="com.google.gson.Gson"%>
<%@page import="com.semi.userinfo.model.vo.UserInfo"%>
<%@page import="com.semi.bus.model.vo.Business"%>
<%@page import="com.semi.emp.model.vo.Employee"%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%
	Employee loginEmp = (Employee) session.getAttribute("loginEmp");
	UserInfo userInfo = (UserInfo) session.getAttribute("userInfo");
	HashMap<String, Business> busMap = userInfo.getBusMap();
	Business selectBus = null;
	ArrayList<Notification> notiList = null;
	
	if(!busMap.isEmpty()) {
		selectBus = busMap.get(userInfo.getSelectBusId());
		notiList = userInfo.getNotiList();
	}
	
	Gson gs = new GsonBuilder().setDateFormat("yyyy-MM-dd HH:mm:ss").create();
	String parsingInfo = gs.toJson(userInfo);
%>
<!DOCTYPE html>
<html lang="ko">

<head>
	<meta charset="UTF-8" />
	<meta name="viewport" content="width=device-width, initial-scale=1.0" />
	<meta http-equiv="X-UA-Compatible" content="ie=edge" />
	<title>service : site menu</title>
	<!-- CSS -->
	<link rel="stylesheet" href="<%=request.getContextPath()%>/css/main.min.css" />
	<link rel="stylesheet" href="<%=request.getContextPath()%>/css/font-awesome.css" />
	<!-- JavaScript Libraries -->
	<script src="<%=request.getContextPath()%>/js/jquery-3.4.1.min.js"></script>
</head>

<body>
	<script>
		var userInfo = <%=parsingInfo %>;
	</script>
	<script src="<%=request.getContextPath()%>/dist/modules.js"></script>
	<div id="wrap">
		<input type="checkbox" id="ck_snb" class="ly" /> <label for="ck_snb"></label>
		<input type="checkbox" id="ck_chat" class="ly"><label for="ck_chat"></label>
		<!-- 사이드 메뉴 -->
		<aside class="snb snb_emp">
			<nav>
				<img class="snb_profile"
					src="<%=request.getContextPath()%>/upload/profile/<%=loginEmp.getProfilePic()%>" alt="프로필 사진">
				<span><%=loginEmp.getUserName()%></span>
				<ul id="snb_btnList">
					<li>
						<a href="<%=request.getContextPath()%>/views/emp/pwdck.jsp">
							<i class="fa fa-user-circle-o" aria-hidden="true"></i>개인 정보</a>
					</li>
					<li id="btnSnbCalendar">
						<a href="<%=request.getContextPath()%>/emp/calendar.do">
							<i class="fa fa-calendar-check-o" aria-hidden="true"></i>캘린더</a>
					</li>
					<li id="btnBoard">
						<a href="<%=request.getContextPath()%>/views/boardList">
							<i class="fa fa-window-close" aria-hidden="true"></i></i>익명 게시판</a>
					</li>
				</ul>
			</nav>
		</aside>
		<aside class="chat">
			<div class="chat_header">
				<span class="focus">리스트</span>
				<span>대화방</span>
			</div>
			<div class="chat_body">
				<div class="chatList_area focus">
					<%
						for (Map.Entry<String, Business> e : busMap.entrySet()) {
							Business b = e.getValue();
					%>
					<div class="chatListItem_area">
						<%=b.getBusName()%>
						<input type="hidden" class="hidden_busId" value='<%=b.getBusId()%>'>
						<%-- <input type="hidden" id="hidden_userId" value='<%=userInfo.getUserId()%>'> --%>
					</div>
					<%
						}
					%>
				</div>
				<div class="chatRoom_area">
					<div class="chatEmp_area">
						
					</div>
					<div class="chat2">
						<div class="chatMsg_area">
						</div>
						<div class="chatMsg_enter">
							<textarea id=content rows="3" cols="20" maxlength="99" style="border:1px solid #F8F8F8"
								autofocus></textarea>
							<button id=btn>Send</button>
						</div>
					</div>
				</div>
			</div>
		</aside>
		<!-- //사이드 메뉴 -->
		<!-- container -->
		<div id="container" class="container_main">
			<!-- gnb -->
			<nav class="gnb gnb_main gnb_emp">
				<div>
					<label for="ck_snb"> <i class="fa fa-bars" aria-hidden="true"></i>
					</label>
				</div>
				<div class="busList_area">
					<%
						if (selectBus != null) {
					%>
					<h1 class="dropdown_toggle"><%=selectBus.getBusName()%></h1>
					<ul class="dropdown_menu">
						<%
							for (Map.Entry<String, Business> e : busMap.entrySet()) {
								Business b = e.getValue();
						%>
						<li><a
								href="<%=request.getContextPath()%>/emp/main.do?selectBus=<%=b.getBusId()%>"><%=b.getBusName()%></a>
						</li>
						<%
							}
						%>
						<li><a href="<%=request.getContextPath()%>/emp/addBus.do">추가</a></li>
					</ul>
					<%
						}
					%>
				</div>
				
				<div id="gnb_alert">
					<div id="btn_alert">
						<div id="gnb_alertBadge"></div>
						<i class="fa fa-bell" aria-hidden="true"></i>
					</div>
					<!-- 알람창 -->
					<div id="alert">
						<div id="alert_header">알림 센터</div>
						<div id="alert_body"></div>
					</div>
					<!-- //알람창 -->
				</div>
				<div>
					<i class="fa fa-cog" aria-hidden="true"></i>
				</div>
				<div>
					<i class="fa fa-sign-out" aria-hidden="true"></i>
				</div>
			</nav>
			<!-- //gnb -->
			<!-- conent -->