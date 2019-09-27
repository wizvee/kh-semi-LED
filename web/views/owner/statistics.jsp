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
<script type="text/javascript">

var json

$.ajax({url: "/p_190826_semi/owner/requestStatistics.do",
	type: "post", 
	async: false, 
    success: function(data){
    	json = JSON.parse(data);
    },
    error: function(data) {
        alert('데이터 안넘어 옴!');
    } 
});




function addComma(value) {
    return Number(value).toLocaleString('en').split(".")[0];
}








// ============================== 직원 급여 월별 라인 그래프 ===================================

		var pathOne=json.forWageLine[0]; 
/* 		console.log(pathOne); */
		console.log(pathOne.allDays[0].year);
	
      google.charts.load('current', {'packages':['corechart']});
      google.charts.setOnLoadCallback(drawChart);
			
  			for(i in pathOne){
     		console.log(pathOne.allDays[i].year); 
			} 
     	
/*      	for(var i=0;i<pathOne.size;i++){
     		console.log(pathOne.getAllDays[i]);
     	} */
     	
      
      function drawChart() {
        var data = google.visualization.arrayToDataTable([
          ['월', '급여지출'],
          ['1월',  1000],
          ['2월',  1170],
          ['3월',  660],
          ['4월',  1020],
          ['5월',  900],
          ['6월',  1030],
          ['7월',  1200],
          ['8월',  1400],
          ['9월',  1600],
          ['10월',  1430],
          ['11월',  1230],
          ['12월',  830]
        ]);

        var options = {
          title: '월별 직원 급여 지출',
          hAxis: {title: '월별',  titleTextStyle: {color: '#333'}},
          vAxis: {title: '만원', minValue: 0}
        };

        var chart = new google.visualization.AreaChart(document.getElementById('area_chart'));
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
    
        for(i in json.forWageTable){
        	data.addRow([json.forWageTable[i].empName, {v:0,f: String(addComma(json.forWageTable[i].totalWage))+"원"}, json.forWageTable[i].workingNow])
        } 
		
		var options={
			title:'직원별 받아간 총 급여',
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
			<% if (dataMap!=null){ %>
			<div><%=dataMap.get("forWageTable").get(0).getEmpName()%></div>
			<% } %>
			<div class="statistics_view">
					<div class="columns">
						<!-- <div id="worker_wage_div" style= "width: 400px; height: 300px; border: 1px solid red"></div> -->
						<div id="area_chart" style="width: 700px; height: 500px; border: 1px solid red"></div>	
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