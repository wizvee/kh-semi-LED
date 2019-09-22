<%@page import="java.util.Map"%>
<%@page import="java.util.HashMap"%>
<%@page import="com.google.gson.Gson"%>
<%@page import="com.semi.userinfo.model.vo.UserInfo"%>
<%@page import="com.semi.bus.model.vo.Business"%>
<%@page import="com.semi.emp.model.vo.Employee"%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="header.jsp"%>
<div id="content">
	<section class="item">
		<div class="item_header">
			<h2>사업장 등록</h2>
		</div>
		<div class="item_body">
			<div class="addBus_area">
				<div class="search_area">
					<input type="text" id="inpt_key" class="inpt-outline" placeholder="근무지 검색 ..." />
					<span><i class="fa fa-search" aria-hidden="true"></i></span>
				</div>
			</div>
		</div>
	</section>
</div>
<script src="<%=request.getContextPath()%>/js/addBus.js"></script>
<%@ include file="footer.jsp"%>