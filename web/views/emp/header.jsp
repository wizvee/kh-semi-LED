<%@page import="com.semi.user.model.vo.Employee"%>
<%@page import="com.semi.user.model.vo.User"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%
	Employee loginEmp = (Employee) session.getAttribute("loginEmp");
	if (loginEmp == null)
		response.sendRedirect(request.getContextPath());
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
</head>

<body>
	<div id="wrap">
		<input type="checkbox" id="ck_snb" class="ly" /> <label for="ck_snb"></label>
		<input type="checkbox" id="" class="ly" /> <label for=""></label>
		<!-- 사이드 메뉴 -->
		<aside class="snb snb_emp">
			<nav></nav>
		</aside>
		<!-- //사이드 메뉴 -->
		<!-- container -->
		<div id="container" class="container_main">
			<!-- gnb -->
			<nav class="gnb gnb_main gnb_emp">
				<div>
					<label for="ck_snb"> <i class="fa fa-bars"
						aria-hidden="true"></i>
					</label>
				</div>
				<div>
					<h1>사업장</h1>
					<!-- <span><i class="fa fa-caret-down" aria-hidden="true"></i></!-->
				</div>
				<div>
					<i class="fa fa-bell-o" aria-hidden="true"></i>
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