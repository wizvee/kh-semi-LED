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
<style>
	.statistics_view {
		overflow: hidden;
   		 height: 500px;
	}

	.statistics_view>.chart {
		position: relative;
        width: 100%;
        height: 500px;
        transition: all 0.7s;
	}

	.statistics_view>.chart .yearSet {
		z-index: 30;
            position: absolute;
            right: 0;
            background: red;
	}
</style>

<!------------------------------------- script THE END ------------------------------------>

<div id="content">
	<section class="item">
		<div class="item_header">
			<h2>통계</h2>
		</div>
		<div class="item_body statis_body">
			<div class="statistics_menu statis_header">
				<!-- <div class="statistics_list">월별 근무자 급여 비율 파이 차트</div> -->
				<!-- <div class="statistics_list">인권비 급여/일급/월급/전체 커브 차트</div>
				<div class="statistics_list">알바생별 받은 총 인권비 테이블차트</div>
				<div class="statistics_list">근무 기간 타임라인 차트</div>
				<div class="statistics_list">평균 근무 시간 콤보 차트</div>
				<div class="statistics_list">월별 근무자 수 라인 차트</div>
				<div class="statistics_list">월별 총 지각 조퇴 바 차트</div> -->
				<br>
				<br>
				<br>
				<span class="focus">급여 라인 차트</span>
				<br>
				<span>급여 테이블</span>
				<br>
				<span>직원 타임 라인</span>
				<br>
				<span>근무시간 차트</span>
				<br>
				<span>근무자 수 차트</span>
				<br>
				<span>근태관리 차트</span>
				<br>
				<div></div>
			</div>
			<div class="statistics_view">
				<!-- <div id="worker_wage_div" style= "width: 400px; height: 300px; border: 1px solid red"></div> -->
				<div id="firstChart" class="chart focus">
					<div class="yearSet">
						년도
						<ul id="firstChartList"></ul>
					</div>
					<div id="arealine_chart" style="height: 500px;"></div>
				</div>
				<div id="table_div" class="chart" style="height: 500px;"></div>
				<div id="time_line" class="chart" style="height: 500px;"></div>
				<div id="forthChart" class="chart">
					<div id="forthChartList" class="yearSet"></div>
					<div id="combo_chart_div" style="height: 500px;"></div>
				</div>
				<div id="fifthChart" class="chart">
					<div id="fifthChartList" class="yearSet"></div>
					<div id="dailyWorkerCount_div" style="height: 500px;"></div>
				</div>
				<div id="sixthChart" class="chart">
					<div id="sixthChartList" class="yearSet"></div>
					<div id="lateEarly_chart" style="height: 500px;"></div>
				</div>
			</div>
		</div>
	</section>
</div>
<script>
	const statisHeader = selectElements(".statis_body span");
	const statisBody = selectElements(".statistics_view .chart");
	statisHeader.map((e, index) => {
		e.addEventListener("click", ({target}) => {
			statisHeader.map(s => s.classList.remove("focus"));
			target.classList.add("focus");
			var height = -(500 * index);
			statisBody.map(s => {
				s.style.transform = "translateY(" + height + "px)";
			})
		});
	});
	$('.yearSet').hover(function() {
  	$(this).find('.area_chart1').stop(true, true).delay(200).fadeIn(500);
	}, function() {
  	$(this).find('.area_chart1').stop(true, true).delay(200).fadeOut(500);
	});
</script>
<script type="text/javascript" src="<%=request.getContextPath()%>/js/statistics.js"></script>

<%@ include file="footer.jsp"%>