<%@page import="com.semi.caldendar.model.vo.Cal"%>
<%@page import="java.util.Arrays"%>
<%@page import="com.semi.sft.model.vo.Shift"%>
<%@page import="com.semi.emp.model.vo.Employee"%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="header.jsp"%>
<%
	ArrayList<Employee> empList = (ArrayList<Employee>) request.getAttribute("empList");
	ArrayList<Shift> sftList = (ArrayList<Shift>) request.getAttribute("sftList");

	String parsingEmpList = gs.toJson(empList);
%>
<script>
	var empList = <%=parsingEmpList %>;
	window.onload = function () {
		document.querySelector("#btnSnbCalendar").classList.add("focus");
	}
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

	<section class="item cal_item">
		<div class="viewCalendar_area focus">
			<div class="item_header">
				<h2>일정 보기</h2>
			</div>
			<div class="item_body viewCal_area">
				<div><span class="calSft"></span></div>
				<div class="calTitle">
				</div>
				<div class="calDetail"></div>
				<div class="calTask"></div>
			</div>
		</div>
		<div class="addCal_area">
				<div class="item_header">
					<h2>일정 없음</h2>
				</div>
				<div class="item_body">
					일정이 없습니다.
				</div>
			</div>
	</section>

</div>
<script src="<%=request.getContextPath()%>/dist/calendar.js"></script>
<%@ include file="footer.jsp"%>