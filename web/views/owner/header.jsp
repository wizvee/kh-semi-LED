<%@page import="java.util.ArrayList"%>
<%@page import="com.semi.owner.model.service.OwnerService"%>
<%@page import="com.semi.owner.model.vo.Owner"%>
<%@page import="com.semi.user.model.vo.User"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%
	Owner loginOwner = (Owner) session.getAttribute("loginOwner");
	ArrayList<String> bnsList = (ArrayList<String>) session.getAttribute("bnsList");
%>
<!DOCTYPE html>
<html lang="ko">
<head>
<meta charset="UTF-8" />
<meta name="viewport" content="width=device-width, initial-scale=1.0" />
<meta http-equiv="X-UA-Compatible" content="ie=edge" />
<title>service : site menu</title>
<!-- CSS -->
<link rel="stylesheet"
	href="<%=request.getContextPath()%>/css/main.min.css" />
<link rel="stylesheet"
	href="<%=request.getContextPath()%>/css/font-awesome.css" />
<!-- JavaScript Libraries -->
<script src="<%=request.getContextPath()%>/js/jquery-3.4.1.min.js"></script>
</head>

<body>
	<div id="wrap">
		<input type="checkbox" id="ck_snb" class="ly" /> <label for="ck_snb"></label>
		<input type="checkbox" id="" class="ly" /> <label for=""></label>
		<!-- 사이드 메뉴 -->
		<aside class="snb snb_own">
			<nav></nav>
		</aside>
		<!-- //사이드 메뉴 -->
		<!-- container -->
		<div id="container" class="container_main">
			<!-- gnb -->
			<nav class="gnb gnb_main gnb_own">
				<div>
					<label for="ck_snb">
						<i class="fa fa-bars" aria-hidden="true"></i>
					</label>
				</div>
				<div class="busList_area">
					<%
						if (!bnsList.isEmpty()) {
					%>
					<h1><%=bnsList.get(0)%><i class="fa fa-caret-square-o-down" aria-hidden="true"></i></h1>
					
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
