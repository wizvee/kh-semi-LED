<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="header.jsp"%>
<div id="content" class="content_calendar">

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
			<div class="calendar_body">

			</div>
		</div>
	</section>

	<section class="item">
		<div class="item_header">
			<h2>일정 추가</h2>
		</div>
		<div class="item_body subCal_area">
			<div>
				
			</div>
			<input type="text" name="" class="inpt-outline">
			<input type="text" name="title" class="inpt-outline">
			<textarea name="" class="inpt-outline" cols="30" rows="10"></textarea>
			<button class="btn-primary">할 일 추가</button>
		</div>
	</section>

</div>
<script src="<%=request.getContextPath()%>/dist/calendar.js"></script>
<%@ include file="footer.jsp"%>