<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="header.jsp"%>
<div id="content">

	<section class="item">
		<div class="item_header">
			<h2>캘린더</h2>
		</div>
		<div class="item_body calendar_area">
			<div class="calendar_header">
				<i id="btn_calPrv" class="fa fa-chevron-left" aria-hidden="true"></i>
				<span>월</span>
				<i id="btn_calNxt" class="fa fa-chevron-right" aria-hidden="true"></i>
			</div>
			<div class="calendar_body"></div>
		</div>
	</section>

</div>
<script src="<%=request.getContextPath()%>/js/calendar.js"></script>
<%@ include file="footer.jsp"%>