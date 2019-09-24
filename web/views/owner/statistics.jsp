<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="header.jsp"%>
<!-- 구글 AJAX API 불러오기 -->
<script type="text/javascript" src="https://www.gstatic.com/charts/loader.js"></script>
<script type="text/javascript">

	// Load Charts and the corechart package.
	google.charts.load('current', {'packages':['corechart']});

	google.charts.setOnLoadCallback(hourlyWageWorkers);
	google.charts.setOnLoadCallback(dailyWageWorkers);
	google.charts.setOnLoadCallback(monthlyWageWorkers);

	// Callback that draws the pie chart for Sarah's pizza.
	function hourlyWageWorkers() {

	  // Create the data table for Sarah's pizza.
	  var data = new google.visualization.DataTable();
	  data.addColumn('string', 'Topping');
	  data.addColumn('number', 'Slices');
	  data.addRows([
		['Mushrooms', 7800],
		['Onions', 8500],
		['Olives', 8600],
		['Zucchini', 9000],
		['Pepperoni', 9500]
	  ]);

	  // Set options for Sarah's pie chart.
	  var options = {title:'시급 근무자',
					 width:400,
					 height:300};

	  // Instantiate and draw the chart for Sarah's pizza.
	  var chart = new google.visualization.PieChart(document.getElementById('hourlyWageWorkers_div'));
	  chart.draw(data, options);
	}

	// Callback that draws the pie chart for Anthony's pizza.
	function dailyWageWorkers() {

	  // Create the data table for Anthony's pizza.
	  var data = new google.visualization.DataTable();
	  data.addColumn('string', 'Topping');
	  data.addColumn('number', 'Slices');
	  data.addRows([
		['Mushrooms', 90000],
		['Onions', 110000],
	  ]);

	  // Set options for Anthony's pie chart.
	  var options = {title:'일당 근무자',
					 width:400,
					 height:300};

	  // Instantiate and draw the chart for Anthony's pizza.
	  var chart = new google.visualization.PieChart(document.getElementById('dailyWageWorkers_div'));
	  chart.draw(data, options);
	}

		// Callback that draws the pie chart for Anthony's pizza.
		function monthlyWageWorkers() {

// Create the data table for Anthony's pizza.
var data = new google.visualization.DataTable();
data.addColumn('string', 'Topping');
data.addColumn('number', 'Slices');
data.addRows([
  ['Mushrooms', 2100000],
  ['Onions', 2400000],
  ['Olives', 2500000],
]);

// Set options for Anthony's pie chart.
var options = {title:'월급 근무자',
			   width:400,
			   height:300};

// Instantiate and draw the chart for Anthony's pizza.
var chart = new google.visualization.PieChart(document.getElementById('monthlyWageWorkers_div'));
chart.draw(data, options);
}

// ============================================ 직원 급여 추이 라인 그래프 현재근무자 =========================================
google.charts.setOnLoadCallback(drawChartLine);

function drawChartLine() {
  var data = google.visualization.arrayToDataTable([
	['월', '시급', '일당', '월급'],
	['7월',  350,      100, 	500],
	['8월',  450,      130, 	510],
	['9월',  250,       40, 	550],
	['10월',  200,      80, 	600]
  ]);

  var options = {
	title: '직원 급여 추이 라인 그래프',
	curveType: 'function',
	legend: { position: 'bottom' },
  };

  var chart = new google.visualization.LineChart(document.getElementById('curve_chart'));

  chart.draw(data, options);
}
</script>


<!------------------------------------- script THE END ------------------------------------>

<div id="content">
	<section class="item">
		<div class="item_header">
			<h2>통계</h2>
		</div>
		<div class="item_body">
			<div class ="statistics_menu">
				<div class="statistics_list">직원 급여 파이 차트</div>
				<div class="statistics_list">인권비 급여/일급/월급/전체 커브 차트</div>
				<div class="statistics_list">알바생별 받은 총 인권비 테이블차트</div>
				<div class="statistics_list">근무 기간 타임라인 차트</div>
				<div class="statistics_list">평균 근무 시간 콤보 차트</div>
				<div class="statistics_list">조직도 관계 차트</div>
			</div>
			<br>
			<div class="statistics_view">
					<div class="columns">
						<div id="hourlyWageWorkers_div" style="border: 1px solid #ccc"></div>
						<br>
						<div id="dailyWageWorkers_div" style="border: 1px solid #ccc"></div>
						<br>
						<div id="monthlyWageWorkers_div" style="border: 1px solid #ccc"></div>
						<br>						
						<div id="curve_chart" style="width: 900px; height: 500px"></div>
					</div>
			</div>
		</div>
	</section>

</div>
<%@ include file="footer.jsp"%>