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

	String parsingInfo = new Gson().toJson(userInfo);
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
		const userInfo = <%= parsingInfo %>;
	</script>
	<div id="wrap">
		<input type="checkbox" id="ck_snb" class="ly" /><label for="ck_snb"></label>
		<input type="checkbox" id="" class="ly" /> <label for=""></label>
		<!-- 사이드 메뉴 -->
		<aside class="snb snb_own">
			<nav>
				<ul>
					<li><img class="snb_profile"
							src="<%=request.getContextPath()%>/upload/profile/<%=loginOwner.getProfilePic()%>"
							alt="프로필 사진"></li>
					<li><%=loginOwner.getUserName()%></li>
					<li>
						<p>============</p>
					</li>
					<li><a href="<%=request.getContextPath()%>/owner/manageEmp.do">전체 직원 관리</a></li>
					<li><a href="<%=request.getContextPath()%>/views/owner/pwdck.jsp">개인 정보</a></li>
					<li><a href="<%=request.getContextPath()%>/views/attendance.jsp">근태 관리</a></li>
					<li><a href="<%=request.getContextPath()%>/owner/">급여 관리</a></li>
					<li><a href="<%=request.getContextPath()%>/owner/statistics.do">직원 통계</a></li>
				</ul>
			</nav>
		</aside>
		<!-- //사이드 메뉴 -->
		<!-- container -->
		<div id="container" class="container_main">
			<div id="chat_area" class="item" style="display: none;">
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
							<p></p>
						</div>
						<div class="chatMsg_enter">
							<textarea id=content rows="3" cols="20" maxlength="99" style="border:1px solid white"
								autofocus></textarea>
							<button id=btn>Send</button>
						</div>
					</div>
				</div>
			</div>
			<!-- gnb -->
			<nav class="gnb gnb_main gnb_own">
				<div>
					<label for="ck_snb"> <i class="fa fa-bars" aria-hidden="true"></i>
					</label>
				</div>
				<div class="busList_area">
					<%
						if (selectBus != null) {
					%>
					<h1 class="dropdown_toggle">
						<%=selectBus.getBusName()%>
					</h1>
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
				<div>
					<i class="fa fa-bell" aria-hidden="true"></i>
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