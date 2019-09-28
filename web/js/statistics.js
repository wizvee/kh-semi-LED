
var json

$.ajax({url: "/p_190826_semi/owner/requestStatistics.do",
	type: "post", 
	async: false, 
    success: function(data){
      json = JSON.parse(data);
    },
    error: function(data) {
        console.log('데이터 안넘어 옴!');
    } 
});

function addComma(value) {
    return Number(value).toLocaleString('en').split(".")[0];
}


// ============================== 직원 급여 월별 라인 그래프 ===================================

var firstYear=json.forWageLine[0].years[0].year;
console.log(firstYear);
var yearNow=new Date().getFullYear();

var dispNo=yearNow-firstYear;
var dis=json.forWageLine[0].years[dispNo];


for(var y=firstYear;y<=yearNow;y++){
  $("#firstChartList").append('<div class="area_chart">'+y+'년</div>');
}

google.charts.load('current', {'packages':['corechart']});
google.charts.setOnLoadCallback(drawChart);

function drawChart() {
  var data = google.visualization.arrayToDataTable([
    ['월', '급여지출'],
    ['1월',  dis.jan],
    ['2월',  dis.feb],
    ['3월',  dis.mar],
    ['4월',  dis.apr],
    ['5월',  dis.may],
    ['6월',  dis.jun],
    ['7월',  dis.july],
    ['8월',  dis.aug],
    ['9월',  dis.sep],
    ['10월',  dis.oct],
    ['11월',  dis.nov],
    ['12월',  dis.dec]
  ]);
  var options = {
    title: dis.year+" 년 월별 직원 급여 지출",
    hAxis: {title: '월별',  titleTextStyle: {color: '#333'}},
    vAxis: {title: '만원', minValue: 0},
    animation:{
      easing:'inAndOut',
      startup:true,
      duration:3000
      },
  };
  
  var chart = new google.visualization.AreaChart(document.getElementById('area_chart'));
  chart.draw(data, options);
}

var chartYearList = document.querySelectorAll(".area_chart");

Array.from(chartYearList).forEach(function(e) {
  e.addEventListener('click', function(){
    var no=parseInt(e.textContent)-firstYear;
    var temp=json.forWageLine[0].years[no];
    
        google.charts.load('current', {'packages':['corechart']});
        google.charts.setOnLoadCallback(drawChart);

      function drawChart() {
        var data = google.visualization.arrayToDataTable([
          ['월', '급여지출'],
          ['1월',  temp.jan],
          ['2월',  temp.feb],
          ['3월',  temp.mar],
          ['4월',  temp.apr],
          ['5월',  temp.may],
          ['6월',  temp.jun],
          ['7월',  temp.july],
          ['8월',  temp.aug],
          ['9월',  temp.sep],
          ['10월',  temp.oct],
          ['11월',  temp.nov],
          ['12월',  temp.dec]
        ]);

        var options = {
          title: temp.year+" 년 월별 직원 급여 지출",
          hAxis: {title: '월별',  titleTextStyle: {color: '#333'}},
          vAxis: {title: '만원', minValue: 0},
          animation:{
            easing:'inAndOut',
            startup:true,
            duration:3000
            },
        };

        var chart = new google.visualization.AreaChart(document.getElementById('area_chart'));
        chart.draw(data, options);
      }
    });
  });
  

// =============================== 알바생별 받은 총 인권비 테이블차트 ==============================

google.charts.load('current', {'packages':['table']});
      google.charts.setOnLoadCallback(drawTable);

      function drawTable() {
        var data = new google.visualization.DataTable();
        data.addColumn('string', '이름');
        data.addColumn('number', '받아간 총 급여');
        data.addColumn('boolean', '현재 근무여부');
    
        for(i in json.forWageTable){
        	data.addRow([json.forWageTable[i].empName, {v:json.forWageTable[i].totalWage,f: addComma(json.forWageTable[i].totalWage)+"원"}, json.forWageTable[i].workingNow])
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

  console.log(json.forTimeLine[0].empName);
  
  function drawTimeLine() {
    var container = document.getElementById('time_line');
    var chart = new google.visualization.Timeline(container);
    var dataTable = new google.visualization.DataTable();

    dataTable.addColumn({ type: 'string', id: 'Term' });
    dataTable.addColumn({ type: 'string', id: 'Name' });
    dataTable.addColumn({ type: 'date', id: 'Start' });
	  dataTable.addColumn({ type: 'date', id: 'End' });
	
	var d=new Date();
  var thisDay=d.getFullYear()+"-"+(d.getMonth()+1)+"-"+d.getDate();
    console.log(thisDay)

  for(a in json.forTimeLine){
    dataTable.addRow([String(parseInt(a)+1),json.forTimeLine[a].empName, new Date(json.forTimeLine[a].empStart) ,new Date(json.forTimeLine[a].empEnd==undefined?thisDay:json.forTimeLine[a].empEnd)])
  } 

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

for(var y=firstYear;y<=yearNow;y++){
  $("#forthChartList").append('<div class="area_chart">'+y+'년</div>');
}
  dis=json.forWorkingHour[0].years[dispNo];


  google.charts.setOnLoadCallback(drawVisualization);

      function drawVisualization() {
      
        var data = google.visualization.arrayToDataTable([
		      ['월별',            '총 근무시간'],
		      ['1월',           dis.jan],
          ['2월',         dis.feb],
          ['3월',           dis.mar],
          ['4월',            dis.apr],
          ['5월',           dis.may],
          ['6월',           dis.jun],
          ['7월',           dis.jul],
          ['8월',             dis.aug],
          ['9월',           dis.sep],
		      ['10월',           dis.oct],
		      ['11월',           dis.nov],
          ['12월',           dis.dec],
          ]);

        var options = {
          title : '월별 근무자 전체 근무시간',
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
    var chartYearList = document.querySelectorAll(".area_chart");
    
    Array.from(chartYearList).forEach(function(e) {
      e.addEventListener('click', function(){
        var no=parseInt(e.textContent)-firstYear;
        var temp=json.forWorkingHour[0].years[no];

        google.charts.setOnLoadCallback(drawVisualization);

        function drawVisualization() {
        
          var data = google.visualization.arrayToDataTable([
            ['월별',            '총 근무시간'],
            ['1월',           temp.jan],
            ['2월',         temp.feb],
            ['3월',           temp.mar],
            ['4월',            temp.apr],
            ['5월',           temp.may],
            ['6월',           temp.jun],
            ['7월',           temp.jul],
            ['8월',             temp.aug],
            ['9월',           temp.sep],
            ['10월',           temp.oct],
            ['11월',           temp.nov],
            ['12월',           temp.dec],
            ]);
  
          var options = {
            title : '월별 근무자 전체 근무시간',
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

      });
    });

// ================================= 월별 근무자 수 라인차트  ==================================
	  
google.charts.load('current', {packages: ['corechart', 'line']});
google.charts.setOnLoadCallback(dailyWorkerCount);

function dailyWorkerCount() {

      var data = new google.visualization.DataTable();
      data.addColumn('number', '월');
      data.addColumn('number', '근무자수');

      data.addRows([
        [1, 8],   [2, 9],  [3, 4],  [4, 4],  [5, 8],  [6, 9],
		    [7, 8],  [8, 6],  [9, 9],  [10, 10],  [11, 9], [12, 13],
      ]);
      
      var options = {
		title:'월별 총 근무자 수',
		hAxis: {
          title: '월',
          format:'long'
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

google.charts.load('current', {'packages':['corechart']});
google.charts.setOnLoadCallback(drawChartLate);

function drawChartLate() {
  var data = google.visualization.arrayToDataTable([
    ['월', '지각', '조퇴'],
    ['1월',  3,      0],
    ['2월',  5,      1],
    ['3월',  3,       2],
    ['4월',  6,      0],
    ['5월',  3,      0],
    ['6월',  1,      5],
    ['7월',  2,      0],
    ['8월',  3,      2],
    ['9월',  5,      1],
    ['10월',  1,      2],
    ['11월',  3,      1],
    ['12월',  4,      2],
  ]);

  var options = {
    title: '월별 총 지각/조퇴 자 수',
    chartArea: {
			width: '80%',
			height:'70%'
    },
    animation:{
			easing:'inAndOut',
			startup:true,
			duration:3000
    },
    hAxis: {
      title: '인원수(명)',
      minValue: 0
    },
    vAxis: {
      title: '월'
    },
    curveType: 'function',
    legend: { position: 'bottom' }
  };

  var chart = new google.visualization.LineChart(document.getElementById('lateEarly_chart'));

  chart.draw(data, options);
}

    



