
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


// ============================== 1.직원 급여 월별 라인 그래프 ===================================

var firstYear=json.forWageLine[0].years[0].year;
console.log(firstYear);
var yearNow=new Date().getFullYear();

var dispNo=yearNow-firstYear;
var dis1=json.forWageLine[0].years[dispNo];


for(var y=firstYear;y<=yearNow;y++){
  $("#firstChartList").append('<div class="area_chart">'+y+'년</div>');
}

google.charts.load('current', {'packages':['corechart']});
google.charts.setOnLoadCallback(drawChart);

function drawChart() {
  var data = google.visualization.arrayToDataTable([
    ['월', '급여지출'],
    ['1월',  dis1.jan],
    ['2월',  dis1.feb],
    ['3월',  dis1.mar],
    ['4월',  dis1.apr],
    ['5월',  dis1.may],
    ['6월',  dis1.jun],
    ['7월',  dis1.july],
    ['8월',  dis1.aug],
    ['9월',  dis1.sep],
    ['10월',  dis1.oct],
    ['11월',  dis1.nov],
    ['12월',  dis1.dec]
  ]);

  var options = {
    title: dis1.year+" 년 월별 직원 급여 지출",
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
        google.charts.setOnLoadCallback(drawLinChart);

      function drawLinChart() {
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
  

// =============================== 2.알바생별 받은 총 인권비 테이블차트 ==============================

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


// =============================== 3.근무 기간 타임라인 차트 ==================================

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

// ================================ 4.평균 월별 근무 시간 콤보 차트 ==================================

for(var y=firstYear;y<=yearNow;y++){
  $("#forthChartList").append('<div class="area_chart">'+y+'년</div>');
}
  var dis4=json.forWorkingHour[0].years[dispNo];


  google.charts.setOnLoadCallback(drawVisualization);

      function drawVisualization() {
      
        var data = google.visualization.arrayToDataTable([
		      ['월별',            '총 근무시간'],
		      ['1월',           dis4.jan],
          ['2월',         dis4.feb],
          ['3월',           dis4.mar],
          ['4월',            dis4.apr],
          ['5월',           dis4.may],
          ['6월',           dis4.jun],
          ['7월',           dis4.jul],
          ['8월',             dis4.aug],
          ['9월',           dis4.sep],
		      ['10월',           dis4.oct],
		      ['11월',           dis4.nov],
          ['12월',           dis4.dec],
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

// ================================= 5.월별 근무자 수 라인차트  ==================================
    
for(var y=firstYear;y<=yearNow;y++){
  $("#fifthChartList").append('<div class="area_chart">'+y+'년</div>');
}
  var dis5=json.forTotalEmp[0].years[dispNo];
  console.log(dis5)



google.charts.load('current', {packages: ['corechart', 'line']});
google.charts.setOnLoadCallback(dailyWorkerCount);

function dailyWorkerCount() {

      var data = new google.visualization.DataTable();
      data.addColumn('number', '월');
      data.addColumn('number', '근무자수');

      data.addRows([
        [1, dis5.jan],   [2, dis5.feb],  [3, dis5.mar],  [4, dis5.apr],  [5, dis5.may],  [6, dis5.jun],
		    [7, dis5.july],  [8, dis5.aug],  [9, dis5.sep],  [10, dis5.oct],  [11, dis5.nov], [12, dis5.dec],
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

    var chartYearList = document.querySelectorAll(".area_chart");
    
    Array.from(chartYearList).forEach(function(e) {
      e.addEventListener('click', function(){
        var no=parseInt(e.textContent)-firstYear;
        var temp=json.forTotalEmp[0].years[no];

        google.charts.setOnLoadCallback(dailyWorkerCount);

    function dailyWorkerCount() {

      var data = new google.visualization.DataTable();
      data.addColumn('number', '월');
      data.addColumn('number', '근무자수');

      data.addRows([
        [1, temp.jan],   [2, temp.feb],  [3, temp.mar],  [4, temp.apr],  [5, temp.may],  [6, temp.jun],
		    [7, temp.july],  [8, temp.aug],  [9, temp.sep],  [10, temp.oct],  [11, temp.nov], [12, temp.dec],
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


      });
    });

// ================================= 6.월별 총 지각 조퇴 바 차트  ==================================

google.charts.load('current', {'packages':['corechart']});
google.charts.setOnLoadCallback(drawChartLate);

function drawChartLate() {
  var data = google.visualization.arrayToDataTable([
    ['월', '지각', '조퇴' ,'연장근무'],
    ['1월',  3,      0,    2],
    ['2월',  5,      1,     5],
    ['3월',  3,       2,    5],
    ['4월',  6,      0,     3],
    ['5월',  3,      0,      0],
    ['6월',  1,      5,      2],
    ['7월',  2,      0,     2],
    ['8월',  3,      2,      5],
    ['9월',  5,      1,      9],
    ['10월',  1,      2,     3],
    ['11월',  3,      1,      2],
    ['12월',  4,      2,      1],
  ]);

  var options = {
    title: '월별 총 지각/조퇴/연장근무  수',
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



    



