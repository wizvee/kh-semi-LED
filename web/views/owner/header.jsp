<%@page import="com.google.gson.GsonBuilder"%>
<%@page import="com.semi.noti.model.vo.Notification"%>
<%@page import="java.util.ArrayList"%>
<%@page import="java.util.Map"%>
<%@page import="java.util.HashMap"%>
<%@page import="com.google.gson.Gson"%>
<%@page import="com.semi.userinfo.model.vo.UserInfo"%>
<%@page import="com.semi.bus.model.vo.Business"%>
<%@page import="com.semi.owner.model.vo.Owner"%>
<%@page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%
	Owner loginOwner = (Owner) session.getAttribute("loginOwner");
	UserInfo userInfo = (UserInfo) session.getAttribute("userInfo");
	HashMap<String, Business> busMap = userInfo.getBusMap();
	Business selectBus = null;
	ArrayList<Notification> notiList = null;

	if (!busMap.isEmpty()) {
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
	<!-- favicon erro -->
	<link rel="icon" href="data:;base64,iVBORw0KGgo=">
</head>

<body>
	<script>
		var userInfo = <%=parsingInfo %>;
	</script>
	<script src="<%=request.getContextPath()%>/dist/modules.js"></script>
	<div id="wrap">
		<input type="checkbox" id="ck_snb" class="ly"><label for="ck_snb"></label>
		<input type="checkbox" id="ck_chat" class="ly"><label for="ck_chat"></label>
		<!-- 사이드 메뉴 -->
		<aside class="snb snb_own">
			<nav>
				<img class="snb_profile"
					src="<%=request.getContextPath()%>/upload/profile/<%=loginOwner.getProfilePic()%>" alt="프로필 사진">
				<span><%=loginOwner.getUserName()%></span>
				<ul id="snb_btnList">
					<li id="btnSnbMngEmp">
						<a href="<%=request.getContextPath()%>/owner/manageEmp.do">
							<i class="fa fa-address-card-o" aria-hidden="true"></i>직원 관리</a>
					</li>
					<li>
						<a href="<%=request.getContextPath()%>/views/owner/pwdck.jsp">
							<i class="fa fa-user-circle-o" aria-hidden="true"></i>개인 정보</a>
					</li>
					<li>
						<a href="<%=request.getContextPath()%>/views/attendance.do">
							<i class="fa fa-file-text-o" aria-hidden="true"></i>근태 관리</a>
					</li>
					<li>
						<a href="<%=request.getContextPath()%>/owner/payRollManagement.do">
							<i class="fa fa-krw" aria-hidden="true"></i>급여 관리</a>
					</li>
					<li>
						<a href="<%=request.getContextPath()%>/owner/statistics.do">
							<i class="fa fa-bar-chart" aria-hidden="true"></i>직원 통계</a>
					</li>
					<li id="btnSnbCalendar">
						<a href="<%=request.getContextPath()%>/owner/calendar.do">
							<i class="fa fa-calendar-check-o" aria-hidden="true"></i>캘린더</a>
					</li>
				</ul>
			</nav>
		</aside>
		<!-- //사이드 메뉴 -->
		<!-- 채팅창 -->
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
						<input type="hidden" id="hidden_busId" value='<%=b.getBusId()%>'>
						<input type="hidden" id="hidden_userId" value='<%=userInfo.getUserId()%>'>
					</div>
					<%
						}
					%>
				</div>
				<div class="chatRoom_area">
					<div class="chatEmp_area">
						zz
					</div>
					<div>
						<div class="chatMsg_area">
						</div>
						<div class="chatMsg_enter">
							<textarea id=content rows="3" cols="20" maxlength="99" style="border:1px solid white"
								autofocus></textarea>
							<button id=btn>Send</button>
						</div>
					</div>
				</div>
			</div>
		</aside>
		<!-- //채팅창 -->
		<!-- container -->
		<div id="container" class="container_main">
			<!-- 채팅창 -->
			<!-- <div id="chat_area" class="item" style="display: none;">
				<div class="item_header">채팅창</div>
				<div class="item_body">
					<div class="chatList_area" style="display: block;">
						<%
							for (Map.Entry<String, Business> e : busMap.entrySet()) {
								Business b = e.getValue();
						%>
						<div class="chatListItem_area">
							<%=b.getBusName()%>
							<input type="hidden" id="hidden_busId" value='<%=b.getBusId()%>'>
							<input type="hidden" id="hidden_userId" value='<%=userInfo.getUserId()%>'>
						</div>
						<%
							}
						%>
					</div>
					<div class="chatRoom_area" style="display: none;">
						<button class="btn_chatBack">돌아가기</button>
						<div class="chatMsg_area">
						</div>
						<div class="chatMsg_enter">
							<textarea id=content rows="3" cols="20" maxlength="99" style="border:1px solid white"
								autofocus></textarea>
							<button id=btn>Send</button>
						</div>
					</div>
				</div>
			</div> -->
			<!-- //채팅창 -->
			<!-- gnb -->
			<nav class="gnb gnb_main gnb_own">
				<div>
					<label for="ck_snb">
						<i class="fa fa-bars" aria-hidden="true"></i>
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
								href="<%=request.getContextPath()%>/owner/main.do?selectBus=<%=b.getBusId()%>"><%=b.getBusName()%></a>
						</li>
						<%
							}
						%>
						<li><a href="<%=request.getContextPath()%>/owner/insertBus.do">추가</a></li>
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