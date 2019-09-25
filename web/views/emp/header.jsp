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
	<script src="<%=request.getContextPath()%>/js/modules.js"></script>
</head>

<body>
	<script>
		const userInfo = <%=parsingInfo %>;
	</script>
	<div id="wrap">
		<input type="checkbox" id="ck_snb" class="ly" /> <label for="ck_snb"></label>
		<!-- 사이드 메뉴 -->
		<aside class="snb snb_emp">
			<nav>
				<ul>
					<li><img class="snb_profile"
							src="<%=request.getContextPath()%>/upload/profile/<%=loginEmp.getProfilePic() %>"
							alt="프로필 사진"></li>
					<li><%=loginEmp.getUserName() %></li>
					<li>
						<p>============</p>
					</li>
				</ul>
			</nav>
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