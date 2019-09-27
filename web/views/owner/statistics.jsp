<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page
	import="com.semi.statistics.model.vo.Statistics,
	java.util.List"%>
<%@ include file="header.jsp"%>
<%
Map<String, List<Statistics>> dataMap = (Map<String, List<Statistics>>) request.getAttribute("dataMap");
%>
<!-- 구글 AJAX API 불러오기 -->
<script type="text/javascript" src="https://www.gstatic.com/charts/loader.js"></script>


<!------------------------------------- script THE END ------------------------------------>

<div id="content">
	<section class="item">
		<div class="item_header">
			<h2>통계</h2>
		</div>
		<hr>
		<div class="item_body">
			<div class ="statistics_menu">
				<!-- <div class="statistics_list">월별 근무자 급여 비율 파이 차트</div> -->
				<div class="statistics_list">인권비 급여/일급/월급/전체 커브 차트</div>
				<div class="statistics_list">알바생별 받은 총 인권비 테이블차트</div>
				<div class="statistics_list">근무 기간 타임라인 차트</div>
				<div class="statistics_list">평균 근무 시간 콤보 차트</div>
				<div class="statistics_list">월별 근무자 수 라인 차트</div>
				<div class="statistics_list">월별 총 지각 조퇴 바 차트</div>
			</div>
			<br>
			<% if (dataMap!=null){ %>
			<div><%=dataMap.get("forWageTable").get(0).getEmpName()%></div>
			<% } %>
			<div class="statistics_view">
					<div class="columns">
						<!-- <div id="worker_wage_div" style= "width: 400px; height: 300px; border: 1px solid red"></div> -->
						<div id="firstChart">
							<div class="area_chart"  style="border: 1px solid blue">2017</div>
							<div class="area_chart"  style="border: 1px solid blue">2018</div>
							<div class="area_chart"  style="border: 1px solid blue">2019</div>
							<div id="area_chart" style="width: 700px; height: 500px; border: 1px solid red"></div>	
						</div>	
						<br>
						<div id="table_div" style="width:700px ; height: 500px; border: 1px solid red"></div>
						<br>
						<div id="time_line" style="width:700px ; height: 500px; border: 1px solid red"></div>
						<br>
						<div id="combo_chart_div" style="width: 700px; height: 500px;border: 1px solid red"></div>
						<br>
						<div id="dailyWorkerCount_div" style="width:700px; height:500px; border: 1px solid red"></div>
						<br>
						<div id="barchart_div" style="width: 700px; height:500px; border: 1px solid red"></div>
					</div>
			</div>
		</div>
	</section>
</div>
<script type="text/javascript" src="<%=request.getContextPath()%>/js/owner/statistics.js"></script>

<%@ include file="footer.jsp"%>