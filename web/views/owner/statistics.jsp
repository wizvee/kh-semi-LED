<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="header.jsp"%>
<!-- 구글 AJAX API 불러오기 -->
<script type="text/javascript" src="https://www.gstatic.com/charts/loader.js"></script>
<script type="text/javascript">

	google.charts.load('current', {'packages':['corechart']});

	// google.charts.setOnLoadCallback(WageWorkers);

	// // Callback that draws the pie chart for Sarah's pizza.
	// function WageWorkers() {

	//   // Create the data table for Sarah's pizza.
	//   var data = new google.visualization.DataTable();
	//   data.addColumn('string', '근무자');
	//   data.addColumn('number', '월 급여');
	//   data.addRows([
	// 	['강동원', 2100000],
	// 	['한예슬', 800000],
	// 	['손예진', 880600],
	// 	['신예은', 1900000],
	// 	['브래드피트', 95000]
	//   ]);

	//   // Set options for Sarah's pie chart.
	//   var options = {title:'월별 근무자 급여 비율',
	// 				 width:400,
	// 				 height:300,
	// 				 animation:{
	// 					easing:'inAndOut',
	// 					startup:true,
	// 					duration:3000
	// 					}
	// 				};

	//   // Instantiate and draw the chart for Sarah's pizza.
	//   var chart = new google.visualization.PieChart(document.getElementById('worker_wage_div'));
	//   chart.draw(data, options);
	// }


// ============================== 직원 급여 추이 라인 그래프현재근무자 ===================================
google.charts.setOnLoadCallback(drawChartLine);

function drawChartLine() {
  var data = google.visualization.arrayToDataTable([
	['월', '시급', '일당', '월급', '전체'],
	['1월',  0,      0, 	0,	0],
	['2월',  0,      0, 	510,	0+0+510],
	['3월',  250,       0, 	550,	250+0+550],
	['4월',  200,      0, 	600,	200+0+800],
	['5월',  350,      100, 	500,	350+100+500],
	['6월',  450,      130, 	510,	450+130+510],
	['7월',  250,       40, 	550,	250+40+550],
	['8월',  200,      80, 	600,	200+80+800],
	['9월',  350,      100, 	500,	350+100+500],
	['10월',  450,      130, 	510,	450+130+510],
	['11월',  250,       40, 	550,	250+40+550],
	['12월',  200,      80, 	600,	200+80+800]
  ]);

  var options = {
	title: '월별 급여/일급/월급/전체 인권비',
	curveType: 'function',
	chartArea: {
				width: '70%',
				height:'70%'
			} ,
	vAxis: {title: '만원'},
    hAxis: {title: '월별'},
	legend: { position: 'bottom' },
	animation:{
			easing:'inAndOut',
			startup:true,
			duration:3000
		}
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
          ['강동원',  {v: 0, f: '3,000,000'}, true],
          ['조인성',   {v:0,   f: '8,000,000'},  false],
          ['한예슬', {v: 12500, f: '400,000'}, false],
		  ['손예진',   {v: 7000,  f: '7,000,000'},  true],
		  ['강동원',  {v: 10000, f: '590,000'}, true],
          ['조인성',   {v:8000,   f: '8,000,000'},  false],
          ['한예슬', {v: 12500, f: '400,000'}, false],
		  ['손예진',   {v: 7000,  f: '7,000,000'},  true],
		  ['강동원',  {v: 10000, f: '590,000'}, true],
          ['조인성',   {v:8000,   f: '8,000,000'},  false],
          ['한예슬', {v: 12500, f: '400,000'}, false],
		  ['손예진',   {v: 7000,  f: '7,000,000'},  true],
		  ['강동원',  {v: 10000, f: '590,000'}, true],
          ['조인성',   {v:8000,   f: '8,000,000'},  false],
          ['한예슬', {v: 12500, f: '400,000'}, false],
		  ['손예진',   {v: 7000,  f: '7,000,000'},  true],
		  ['한예슬', {v: 12500, f: '400,000'}, false],
		  ['손예진',   {v: 7000,  f: '7,000,000'},  true],
		  ['강동원',  {v: 10000, f: '590,000'}, true],
          ['조인성',   {v:8000,   f: '8,000,000'},  false],
          ['한예슬', {v: 12500, f: '400,000'}, false],
		  ['손예진',   {v: 7000,  f: '7,000,000'},  true],
		  ['강동원',  {v: 10000, f: '590,000'}, true],
          ['조인성',   {v:8000,   f: '8,000,000'},  false],
          ['한예슬', {v: 12500, f: '400,000'}, false],
		  ['손예진',   {v: 7000,  f: '7,000,000'},  true]
		]);
		
		var options={
			title:'직원별 받아간 총 인권비',
			chartArea: {
				width: '80%',
				height:'80%'
			} 
		}

        var table = new google.visualization.Table(document.getElementById('table_div'));

        table.draw(data, {showRowNumber: true, width: '80%', height: '80%'});
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
      [ '1', '강동원', new Date(2017, 4-1, 11), new Date(2017, 8-1, 12) ],
      [ '2', '조인성',  new Date(2017, 5-1, 4),  new Date(2018, 7-1, 1) ],
	  [ '3', '한예슬',  new Date(2018, 8-1, 21),  new Date(2019, 3-1, 20) ],
	  [ '4', '손예진',  new Date(2018, 8-1, 10),  new Date(year,month,day)],
	  [ '5', '강동원', new Date(2019, 2-1, 30), new Date(year,month,day) ],
      [ '6', '조인성',  new Date(2019, 6-1, 4),  new Date(year,month,day) ],
	  [ '7', '한예슬',  new Date(2019, 7-1, 21),  new Date(year,month,day) ],
	  [ '8', '손예진',  new Date(2019, 8-1, 10),  new Date(year,month,day)],
	]); 

	var options={
		timeline: { groupByRowLabel: false },
		chartArea: {
				width: '70%',
				height:'70%'
			} ,
		forceIFrame:true,
	}

    chart.draw(dataTable,options);
  }

// ================================ 평균 월별 근무 시간 콤보 차트 ==================================

  google.charts.setOnLoadCallback(drawVisualization);

      function drawVisualization() {
        // Some raw data (not necessarily accurate)
        var data = google.visualization.arrayToDataTable([
		  ['월별', '시급 근무자', '일당근무자', '월급근무자', '전체', '평균'],
		  ['1월',  140,      0,         130,           140+0+130, 	(140+0+130)/5],
          ['2월',  135,      0,        150,           135+0+150, 		(135+0+150)/3],
          ['3월',  157,      20,        130,            157+20+130, 	(157+20+130)/3],
          ['4월',  132,      0,        120,           132+0+120, 			(132+0+120)/3],
          ['5월',  142,      0,         200,           142+0+200, 		(142+0+200)/3],
          ['6월',  120,      55,         220,           120+55+220, 	( 120+55+220)/3],
          ['7월',  130,      52,        210,           130+52+210, 		(130+52+210)/3],
          ['8월',  90,      20,        210,            90+20+210, 	( 90+20+210)/3],
          ['9월',  110,      0,        180,           110+0+180, 			(110+0+180)/3],
		  ['10월',  120,      0,         200,           120+0+200, 		(120+0+200)/3],
		  ['11월',  160,      55,         250,           160+55+250, 	(160+55+250)/3],
          ['12월',  150,      52,        280,           150+52+280, 		(150+52+280)/3]
        ]);

        var options = {
          title : '월별 근무자 근무시간',
          vAxis: {title: '시간'},
          hAxis: {title: '월별'},
          seriesType: 'bars',
		  series: {4: {type: 'line'}},
		  animation:{
			easing:'inAndOut',
			startup:true,
			duration:3000
			},
			chartArea: {
				width: '65%',
				height:'70%'
			} 
		 };

        var chart = new google.visualization.ComboChart(document.getElementById('combo_chart_div'));
        chart.draw(data, options);
	  }

// ================================= 월별 근무자 수 라인차트  ==================================
	  
google.charts.load('current', {packages: ['corechart', 'line']});
google.charts.setOnLoadCallback(dailyWorkerCount);

function dailyWorkerCount() {

      var data = new google.visualization.DataTable();
      data.addColumn('number', '월');
      data.addColumn('number', '근무자수');

      data.addRows([
        [1, 3],   [2, 5],  [3, 4],  [4, 4],  [5, 8],  [6, 9],
		[7, 8],  [8, 6],  [9, 9],  [10, 10],  [11, 9], [12, 13],
      ]);

      var options = {
		title:'월별 총 근무자 수',
		hAxis: {
          title: '월'
        },
        vAxis: {
          title: '인원수(명)'
		},
		chartArea: {
				width: '70%',
				height:'70%'
			} ,
		animation:{
			easing:'inAndOut',
			startup:true,
			duration:3000
		},
		tooltip:{
			isHtml:true
		},
      };

      var chart = new google.visualization.LineChart(document.getElementById('dailyWorkerCount_div'));

      chart.draw(data, options);
    }

// ================================= 월별 총 지각 조퇴 바 차트  ==================================

google.charts.load('current', {packages: ['corechart', 'bar']});
google.charts.setOnLoadCallback(drawMultSeries);

function drawMultSeries() {
      var data = google.visualization.arrayToDataTable([
        ['월', '지각', '조퇴'],
        ['1월', 10, 9],
        ['2월', 5, 3],
        ['3월', 3, 5],
        ['4월', 2, 4],
        ['5월', 8, 3],
        ['6월', 8, 4],
        ['7월', 4, 6],
        ['8월', 5, 3],
        ['9월', 4, 2],
        ['10월', 3, 1],
        ['11월', 9, 2],
        ['12월', 11, 2]
      ]);

      var options = {
		title: '월별 총 지각/조퇴 자 수',
        chartArea: {
			width: '80%',
			height:'80%'
		},
        hAxis: {
          title: '인원수(명)',
          minValue: 0
        },
        vAxis: {
          title: '월'
		},
		animation:{
			easing:'inAndOut',
			startup:true,
			duration:3000
		}
      };

      var chart = new google.visualization.BarChart(document.getElementById('barchart_div'));
      chart.draw(data, options);
    }




</script>


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
			<div class="statistics_view">
					<div class="columns">
						<!-- <div id="worker_wage_div" style= "width: 400px; height: 300px; border: 1px solid red"></div> -->	
						<div id="curve_chart" style="width: 700px; height: 500px; border: 1px solid red"></div>
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
<%@ include file="footer.jsp"%>