<%@page import="com.semi.sft.model.vo.Shift"%>
<%@page import="com.semi.emp.model.vo.Employee"%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="header.jsp"%>
<%
	ArrayList<Employee> empList = (ArrayList<Employee>) request.getAttribute("empList");
	ArrayList<Shift> sftList = (ArrayList<Shift>) request.getAttribute("sftList");
%>

<div id="content" class="content_main">

	<section class="item">
		<div class="item_header">
			<h2>근무</h2>
		</div>
		<div class="item_body">

		</div>
	</section>
	
</div>
<%@ include file="footer.jsp"%>