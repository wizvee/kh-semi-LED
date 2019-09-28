<%@page import="com.semi.caldendar.model.vo.Cal"%>
<%@page import="java.util.Arrays"%>
<%@page import="com.semi.sft.model.vo.Shift"%>
<%@page import="com.semi.emp.model.vo.Employee"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ include file="header.jsp"%>
<%
	ArrayList<Employee> empList = (ArrayList<Employee>) request.getAttribute("empList");
	ArrayList<Shift> sftList = (ArrayList<Shift>) request.getAttribute("sftList");

	String parsingEmpList = gs.toJson(empList);
%>
<script>
	var empList = <%=parsingEmpList %>;
</script>
<div id="content" class="content_calendar">

	<section class="item">
		<div class="item_header">
			<h2>캘린더</h2>
		</div>
		<div class="item_body calendar_area">
			<div class="calendar_header">
				<i id="btn_calPrv" class="fa fa-chevron-left" aria-hidden="true"></i>
				<span>월</span> <i id="btn_calNxt" class="fa fa-chevron-right" aria-hidden="true"></i>
			</div>
			<div class="calendar_body"></div>
		</div>
	</section>

	<section class="item">
		<div class="item_header">
			<h2>일정 보기</h2>
		</div>
		<div class="item_body viewCal_area">
			
		</div>
	</section>

	<section class="item">
		<div class="item_header">
			<h2>일정 추가</h2>
		</div>
		<div class="item_body subCal_area">
			<input type="text" name="date" class="inpt-outline" readonly>
			<div class="sftList">
				<input type="hidden" name="sftId" value="<%=sftList.get(0).getSftId() %>">
				<span class="selectSft"><%=sftList.get(0).getSftName() %></span>
				<ul class="dropMenu">
					<%
						for (Shift s : sftList) {
					%>
					<li id="<%=s.getSftId()%>" class="sft"><%=s.getSftName()%></li>
					<%
						}
					%>
				</ul>
			</div>
			<input type="text" name="title" class="inpt-outline">
			<textarea name="content" class="inpt-outline" cols="30" rows="10"></textarea>
			<button id="btn_addTask" class="btn-primary">할일 추가</button>
			<div class="taskList"></div>
			<button id="btn_insertCal" class="btn-primary">일정 추가</button>
		</div>
	</section>

</div>
<script src="<%=request.getContextPath()%>/dist/calendar.js"></script>
<%@ include file="footer.jsp"%>