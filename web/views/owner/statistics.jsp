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

// ============================== 직원 급여 추이 라인 그래프현재근무자 ===================================
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
	width:700,
	height:500,
	vAxis: {title: '백만원'},
    hAxis: {title: '월별'},
	legend: { position: 'bottom' }
	,
  };

  var chart = new google.visualization.LineChart(document.getElementById('curve_chart'));

  chart.draw(data, options);
}


// =============================== 알바생별 받은 총 인권비 테이블차트 ==============================

google.charts.load('current', {'packages':['table']});
      google.charts.setOnLoadCallback(drawTable);

      function drawTable() {
        var data = new google.visualization.DataTable();
        data.addColumn('string', '이름');
        data.addColumn('number', '받아간 총 급여');
        data.addColumn('boolean', '현재 근무여부');
        data.addRows([
          ['강동원',  {v: 10000, f: '590,000'}, true],
          ['조인성',   {v:8000,   f: '8,000,000'},  false],
          ['한예슬', {v: 12500, f: '400,000'}, false],
          ['손예진',   {v: 7000,  f: '7,000,000'},  true]
        ]);

        var table = new google.visualization.Table(document.getElementById('table_div'));

        table.draw(data, {showRowNumber: true, width: '80%', height: '50%'});
      }


// =============================== 근무 기간 타임라인 차트 ==================================

google.charts.load("current", {packages:["timeline"]});
  google.charts.setOnLoadCallback(drawTimeLine);
  function drawTimeLine() {
    var container = document.getElementById('time_line');
    var chart = new google.visualization.Timeline(container);
    var dataTable = new google.visualization.DataTable();

    dataTable.addColumn({ type: 'string', id: 'Term' });
    dataTable.addColumn({ type: 'string', id: 'Name' });
    dataTable.addColumn({ type: 'date', id: 'Start' });
	dataTable.addColumn({ type: 'date', id: 'End' });
	
	var d=new Date();
	var year=d.getFullYear();
	var month=d.getMonth();
	var day=d.getDate();


    dataTable.addRows([
      [ '1', '강동원', new Date(2019, 3, 30), new Date(2019, 8, 12) ],
      [ '2', '조인성',  new Date(2019, 5, 4),  new Date(2019, 7, 1) ],
	  [ '3', '한예슬',  new Date(2019, 8, 21),  new Date(2019, 9, 20) ],
	  [ '4', '손예진',  new Date(2019, 8, 10),  new Date(year,month,day)]]); 

	var options={
	width:500,
	height:300,
	}

    chart.draw(dataTable,options);
  }

// ================================ 평균 근무 시간 콤보 차트 ==================================

  google.charts.setOnLoadCallback(drawVisualization);

      function drawVisualization() {
        // Some raw data (not necessarily accurate)
        var data = google.visualization.arrayToDataTable([
          ['월별', '월급 근무자', '일당근무자', '시급근무자', '평균'],
          ['5월',  120,      55,         220,           150],
          ['6월',  135,      52,        210,           130],
          ['7월',  157,      20,        210,            160],
          ['8월',  139,      0,        180,           80],
          ['9월',  136,      0,         200,           90]
        ]);

        var options = {
          title : '월별 근무자 근무시간',
          vAxis: {title: '시간'},
          hAxis: {title: '월별'},
          seriesType: 'bars',
          series: {3: {type: 'line'}}        };

        var chart = new google.visualization.ComboChart(document.getElementById('combo_chart_div'));
        chart.draw(data, options);
	  }

// ================================= 조직도 관계 차트 ==================================
	  
google.charts.load('current', {packages:["orgchart"]});
      google.charts.setOnLoadCallback(drawChart);

      function drawChart() {
        var data = new google.visualization.DataTable();
        data.addColumn('string', '이름');
        data.addColumn('string', 'Manager');
        data.addColumn('string', 'ToolTip');

        // For each orgchart box, provide the name, manager, and tooltip to show.
        data.addRows([
          [{'v':'조인성', 'f':'조인성<div style="color:red; font-style:italic">사장님</div>'},
           '', 'The President'],
          [{'v':'한예슬', 'f':'한예슬<div style="color:red; font-style:italic">매니저</div>'},
           '조인성', 'VP'],
          ['송중기', '한예슬', ''],
          ['이지은', '한예슬', ''],
          ['신예슬', '한예슬', '']
        ]);

        // Create the chart.
        var chart = new google.visualization.OrgChart(document.getElementById('org_chart_div'));
        // Draw the chart, setting the allowHtml option to true for the tooltips.
        chart.draw(data, {'allowHtml':true});
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
						<div id="curve_chart" style="width: 800px; height: 500px"></div>
						<br>
						<div id="table_div"></div>
						<br>
						<div id="time_line" style="height: 200px;"></div>
						<br>
						<div id="combo_chart_div" style="width: 600px; height: 300px;"></div>
						<br>
						<div id="org_chart_div"></div>
						<br>
					</div>
			</div>
		</div>
	</section>
</div>
<%@ include file="footer.jsp"%>