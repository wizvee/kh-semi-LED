var json;

$.ajax({
  url: contextPath+"owner/requestStatistics.do",
  type: "post",
  async: false,
  success: function(data) {
    json = JSON.parse(data);
  },
  error: function(data) {
    console.log("데이터 안넘어 옴!");
  }
});

function addComma(value) {
  return Number(value)
    .toLocaleString("en")
    .split(".")[0];
}

// object 값 없으면 0으로 리턴하는 함수
function solutionObj(object) {
  if (typeof object == 'undefined') {
      var newVal= {
      jan: 0,
      feb: 0,
      mar: 0,
      apr: 0,
      may: 0,
      jun: 0,
      july: 0,
      aug: 0,
      sep: 0,
      oct: 0,
      nov: 0,
      dec: 0
    };
    return newVal;
  }
  return object;
}

// ============================== 1.직원 급여 월별 라인 그래프 ===================================

var firstYear;
var yearNow = new Date().getFullYear();

if(typeof json.forWageLine[0].years[0]!='undefined'){
  firstYear = json.forWageLine[0].years[0].year;
}
else{
  firstYear=yearNow;
}

console.log(firstYear);

var dispNo = yearNow - firstYear;

var dis1 = solutionObj(json.forWageLine[0].years[dispNo]);

for (var y = firstYear; y <= yearNow; y++) {
  $("#firstChartList").append('<li class="area_chart1" style="display:none"><a href="#">' + y + "년</a></li><br>");
}

google.charts.load("current", { packages: ["corechart"] });
google.charts.setOnLoadCallback(drawChart);

function drawChart() {
  var data = google.visualization.arrayToDataTable([
    ["월", "총 급여 지출"],
    ["1월", dis1.jan],
    ["2월", dis1.feb],
    ["3월", dis1.mar],
    ["4월", dis1.apr],
    ["5월", dis1.may],
    ["6월", dis1.jun],
    ["7월", dis1.july],
    ["8월", dis1.aug],
    ["9월", dis1.sep],
    ["10월", dis1.oct],
    ["11월", dis1.nov],
    ["12월", dis1.dec]
  ]);

  var options = {
    title: yearNow + "년 월별 직원 총 급여 지출",
    hAxis: { title: "월별", titleTextStyle: { color: "#333" } },
    vAxis: { title: "만원", minValue: 0 },
    backgroundColor:'#F8F8F8',
    animation: {
      easing: "inAndOut",
      startup: true,
      duration: 3000
    },
    legend:{
      alignment: 'center',
    },
    chartArea:{ backgroundColor:'#f1f8e9' }
  };

  var chart = new google.visualization.AreaChart(
    document.getElementById("arealine_chart")
  );
  chart.draw(data, options);
}

var chartYearList = document.querySelectorAll(".area_chart1");

Array.from(chartYearList).forEach(function(e) {
  e.addEventListener("click", function() {
    var no = parseInt(e.textContent) - firstYear;
    var temp = json.forWageLine[0].years[no];

    google.charts.setOnLoadCallback(drawLinChart);

    function drawLinChart() {
      var data = google.visualization.arrayToDataTable([
        ["월", "총 급여 지출"],
        ["1월", temp.jan],
        ["2월", temp.feb],
        ["3월", temp.mar],
        ["4월", temp.apr],
        ["5월", temp.may],
        ["6월", temp.jun],
        ["7월", temp.july],
        ["8월", temp.aug],
        ["9월", temp.sep],
        ["10월", temp.oct],
        ["11월", temp.nov],
        ["12월", temp.dec]
      ]);

      var options = {
        title: temp.year + "년 월별 총 직원 급여 지출",
        hAxis: { title: "월별", titleTextStyle: { color: "#333" } },
        vAxis: { title: "만원", minValue: 0 },
        backgroundColor:'#F8F8F8',
        animation: {
          easing: "inAndOut",
          startup: true,
          duration: 3000
        },
        legend:{
          alignment: 'center',
        },
        chartArea:{ backgroundColor:'#f1f8e9' }
      };

      var chart = new google.visualization.AreaChart(
        document.getElementById("arealine_chart")
      );
      chart.draw(data, options);
    }
  });
});
// }
// else{ 
// $('#arealine_chart').append("데이터가 존재하지 않습니다.");
// };
// =============================== 2.알바생별 받은 총 인권비 테이블차트 ==============================

google.charts.load("current", { packages: ["table"] });
google.charts.setOnLoadCallback(drawTable);

function drawTable() {
  var data = new google.visualization.DataTable();
  data.addColumn("string", "이름");
  data.addColumn("number", "받아간 총 급여");
  data.addColumn("boolean", "현재 근무여부");

  for (i in json.forWageTable) {
    data.addRow([
      json.forWageTable[i].empName,
      {
        v: json.forWageTable[i].totalWage,
        f: addComma(json.forWageTable[i].totalWage) + "원"
      },
      json.forWageTable[i].workingNow
    ]);
  }

  var options = {
    title: "직원별 받아간 총 급여",
  };

  var table = new google.visualization.Table(
    document.getElementById("table_div")
  );

  table.draw(data, { title:"직원별 받아간 총 급여", showRowNumber: true, height:'80%', width:'83%'});
}

// =============================== 3.근무 기간 타임라인 차트 ==================================

google.charts.load("current", { packages: ["timeline"] });
google.charts.setOnLoadCallback(drawTimeLine);

function drawTimeLine() {
  var container = document.getElementById("time_line");
  var chart = new google.visualization.Timeline(container);
  var dataTable = new google.visualization.DataTable();

  dataTable.addColumn({ type: "string", id: "Term" });
  dataTable.addColumn({ type: "string", id: "Name" });
  dataTable.addColumn({ type: "date", id: "Start" });
  dataTable.addColumn({ type: "date", id: "End" });

  var d = new Date();
  var thisDay = d.getFullYear() + "-" + (d.getMonth() + 1) + "-" + d.getDate()+" "+d.getHours()+":"+d.getMinutes();
  console.log(thisDay);

  for (a in json.forTimeLine) {
    dataTable.addRow([
      String(parseInt(a) + 1),
      json.forTimeLine[a].empName,
      new Date(json.forTimeLine[a].empStart),
      new Date(
        json.forTimeLine[a].empEnd == undefined? thisDay: json.forTimeLine[a].empEnd)
    ]);
  }

  var options = {
    title: "직원 타임라인",
    timeline: { groupByRowLabel: false },
    forceIFrame: true,
    width: '80%',
    height: '80%',
    backgroundColor:'#f1f8e9'
  };
  chart.draw(dataTable, options);
}

// ================================ 4.평균 월별 근무 시간 콤보 차트 ==================================

for (var y = firstYear; y <= yearNow; y++) {
  $("#forthChartList").append('<li class="area_chart4" style="display:none"><a href="#">' + y + "년</a></li><br>");
}
var dis4 = solutionObj(json.forWorkingHour[0].years[dispNo]);

google.charts.setOnLoadCallback(drawVisualization);

function drawVisualization() {
  var data = google.visualization.arrayToDataTable([
    ["월별", "근무시간"],
    ["1월", dis4.jan],
    ["2월", dis4.feb],
    ["3월", dis4.mar],
    ["4월", dis4.apr],
    ["5월", dis4.may],
    ["6월", dis4.jun],
    ["7월", dis4.jul],
    ["8월", dis4.aug],
    ["9월", dis4.sep],
    ["10월", dis4.oct],
    ["11월", dis4.nov],
    ["12월", dis4.dec]
  ]);

  var options = {
    title: yearNow+"년 월별 근무자 전체 근무시간",
    vAxis: { title: "시간" },
    hAxis: { title: "월별" },
    seriesType: "bars",
    series: { 4: { type: "line" } },
    animation: {
      easing: "inAndOut",
      startup: true,
      duration: 3000
    },
    backgroundColor:'#F8F8F8',
    chartArea: {
      width: "65%",
      height: "70%"
    },
    legend:{
      alignment: 'center',
    },
    chartArea:{ backgroundColor:'#f1f8e9' }
  };

  var chart = new google.visualization.ComboChart(
    document.getElementById("combo_chart_div")
  );
  chart.draw(data, options);
}
var chartYearList = document.querySelectorAll(".area_chart4");

Array.from(chartYearList).forEach(function(e) {
  e.addEventListener("click", function() {
    var no = parseInt(e.textContent) - firstYear;
    var temp = json.forWorkingHour[0].years[no];

    google.charts.setOnLoadCallback(drawVisualization);

    function drawVisualization() {
      var data = google.visualization.arrayToDataTable([
        ["월별", "총 근무시간"],
        ["1월", temp.jan],
        ["2월", temp.feb],
        ["3월", temp.mar],
        ["4월", temp.apr],
        ["5월", temp.may],
        ["6월", temp.jun],
        ["7월", temp.jul],
        ["8월", temp.aug],
        ["9월", temp.sep],
        ["10월", temp.oct],
        ["11월", temp.nov],
        ["12월", temp.dec]
      ]);

      var options = {
        title: temp.year+"년 월별 근무자 전체 근무시간",
        vAxis: { title: "시간" },
        hAxis: { title: "월별" },
        seriesType: "bars",
        series: { 4: { type: "line" } },
        animation: {
          easing: "inAndOut",
          startup: true,
          duration: 3000
        },
        backgroundColor:'#F8F8F8',
        chartArea: {
          width: "65%",
          height: "70%"
        },
        legend:{
          alignment: 'center',
        },
        chartArea:{ backgroundColor:'#f1f8e9' }
      };

      var chart = new google.visualization.ComboChart(
        document.getElementById("combo_chart_div")
      );
      chart.draw(data, options);
    }
  });
});

// ================================= 5.월별 근무자 수 라인차트  ==================================

for (var y = firstYear; y <= yearNow; y++) {
  $("#fifthChartList").append('<li class="area_chart5" style="display:none"><a href="#">' + y + "년</a></li><br>");
}
var dis5 = solutionObj(json.forTotalEmp[0].years[dispNo]);

google.charts.load("current", { packages: ["corechart", "line"] });
google.charts.setOnLoadCallback(dailyWorkerCount);

function dailyWorkerCount() {
  var data = new google.visualization.DataTable();
  data.addColumn("number", "월");
  data.addColumn("number", "근무자수");

  data.addRows([
    [1, dis5.jan],
    [2, dis5.feb],
    [3, dis5.mar],
    [4, dis5.apr],
    [5, dis5.may],
    [6, dis5.jun],
    [7, dis5.july],
    [8, dis5.aug],
    [9, dis5.sep],
    [10, dis5.oct],
    [11, dis5.nov],
    [12, dis5.dec]
  ]);

  var options = {
    title: yearNow+"년 월별 총 근무자 수",
    hAxis: {
      title: "월",
      format: 'long'
    },
    backgroundColor:'#F8F8F8',
    vAxis: {
      title: "인원수(명)"
    },
    chartArea: {
      width: "70%",
      height: "70%"
    },
    animation: {
      easing: "inAndOut",
      startup: true,
      duration: 3000
    },
    tooltip: {
      isHtml: true
    },
    legend:{
      alignment: 'center',
    },
    chartArea:{ backgroundColor:'#f1f8e9' }
  };

  var chart = new google.visualization.LineChart(
    document.getElementById("dailyWorkerCount_div")
  );

  chart.draw(data, options);
}

var chartYearList = document.querySelectorAll(".area_chart5");

Array.from(chartYearList).forEach(function(e) {
  e.addEventListener("click", function() {
    var no = parseInt(e.textContent) - firstYear;
    var temp = json.forTotalEmp[0].years[no];

    google.charts.setOnLoadCallback(dailyWorkerCount);

    function dailyWorkerCount() {
      var data = new google.visualization.DataTable();
      data.addColumn("number", "월");
      data.addColumn("number", "근무자수");

      data.addRows([
        [1, temp.jan],
        [2, temp.feb],
        [3, temp.mar],
        [4, temp.apr],
        [5, temp.may],
        [6, temp.jun],
        [7, temp.july],
        [8, temp.aug],
        [9, temp.sep],
        [10, temp.oct],
        [11, temp.nov],
        [12, temp.dec]
      ]);

      var options = {
        title: temp.year+"년 월별 총 근무자 수",
        hAxis: {
          title: "월",
          format: 'long'
        },
        vAxis: {
          title: "인원수(명)"
        },
        backgroundColor:'#F8F8F8',
        chartArea: {
          width: "70%",
          height: "70%"
        },
        animation: {
          easing: "inAndOut",
          startup: true,
          duration: 3000
        },
        tooltip: {
          isHtml: true
        },
        legend:{
          alignment: 'center',
        },
        chartArea:{ backgroundColor:'#f1f8e9' }
      };

      var chart = new google.visualization.LineChart(
        document.getElementById("dailyWorkerCount_div")
      );

      chart.draw(data, options);
    }
  });
});

// ================================= 6.월별 총 지각 조퇴 바 차트  ==================================

for (var y = firstYear; y <= yearNow; y++) {
  $("#sixthChartList").append('<li class="area_chart6" style="display:none"><a href="#">' + y + "년</a></li><br>");
}

console.log(json.forTotalEarlyLeave[0].years[1]);

  var dis6 = solutionObj(json.forTotalLate[0].years[dispNo]);

  var dis7 = solutionObj(json.forTotalEarlyLeave[0].years[dispNo]);

  var dis8 = solutionObj(json.forTotalOverTime[0].years[dispNo]);


google.charts.load("current", { packages: ["line"] });
google.charts.setOnLoadCallback(drawChartLate);

function drawChartLate() {
  var data = google.visualization.arrayToDataTable([
    ["월", "지각", "조퇴", "연장근무"],
    ["1월", dis6.jan, dis7.jan, dis8.jan],
    ["2월", dis6.feb, dis7.feb, dis8.feb],
    ["3월", dis6.mar, dis7.mar, dis8.mar],
    ["4월", dis6.apr, dis7.apr, dis8.apr],
    ["5월", dis6.may, dis7.may, dis8.may],
    ["6월", dis6.jun, dis7.jun, dis8.jun],
    ["7월", dis6.july, dis7.july, dis8.july],
    ["8월", dis6.aug, dis7.aug, dis8.aug],
    ["9월", dis6.sep, dis7.sep, dis8.sep],
    ["10월", dis6.oct, dis7.oct, dis8.oct],
    ["11월", dis6.nov, dis7.nov, dis8.nov],
    ["12월", dis6.dec, dis7.dec, dis8.dec]
  ]);

  var options = {
    title: yearNow+"년 월별 총 지각/조퇴/연장근무 수",
    animation: {
      easing: "inAndOut",
      startup: true,
      duration: 3000
    },
    backgroundColor:'#F8F8F8',
    hAxis: {
      title: "월",
      maxValue: 12
    },
    vAxis: {
      title: "인원수(명)",
      minValue: 0
    },
    curveType: "function",
    legend: { position: "right" 
    },
    legend:{
      alignment: 'center',
    },
    chartArea:{ backgroundColor:'#f1f8e9' }
  };

  var chart = new google.visualization.LineChart(
    document.getElementById("lateEarly_chart")
  );

  chart.draw(data, google.charts.Line.convertOptions(options));
}

  
var chartYearList = document.querySelectorAll(".area_chart6");

Array.from(chartYearList).forEach(function(e) {
  e.addEventListener("click", function() {
  
    var no = parseInt(e.textContent) - firstYear;
    
    var t1, t2, t3; 
    if(typeof json.forTotalLate[0].years[no]=='undefined'){
      t1= {jan: 0,feb: 0,mar: 0,apr: 0,may: 0,jun: 0,july: 0,aug: 0,sep: 0,oct: 0,nov: 0,dec: 0}
    }else{
      t1 = json.forTotalLate[0].years[no];
    }
    if(typeof json.forTotalEarlyLeave[0].years[no]=='undefined'){
      t2= {jan: 0,feb: 0,mar: 0,apr: 0,may: 0,jun: 0,july: 0,aug: 0,sep: 0,oct: 0,nov: 0,dec: 0}
    }else{
      t2 = json.forTotalEarlyLeave[0].years[no];
    }
    if(typeof json.forTotalOverTime[0].years[no]=='undefined'){
      t3= {jan: 0,feb: 0,mar: 0,apr: 0,may: 0,jun: 0,july: 0,aug: 0,sep: 0,oct: 0,nov: 0,dec: 0}
    }else{
      t3 = json.forTotalOverTime[0].years[no];
    }

    google.charts.setOnLoadCallback(drawChartLate);

    function drawChartLate() {
      var data = google.visualization.arrayToDataTable([
        ["월", "지각", "조퇴", "연장근무"],
        ["1월", t1.jan, t2.jan, t3.jan],
        ["2월", t1.feb, t2.feb, t3.feb],
        ["3월", t1.mar, t2.mar, t3.mar],
        ["4월", t1.apr, t2.apr, t3.apr],
        ["5월", t1.may, t2.may, t3.may],
        ["6월", t1.jun, t2.jun, t3.jun],
        ["7월", t1.july, t2.july, t3.july],
        ["8월", t1.aug, t2.aug, t3.aug],
        ["9월", t1.sep, t2.sep, t3.sep],
        ["10월", t1.oct, t2.oct, t3.oct],
        ["11월", t1.nov, t2.nov, t3.nov],
        ["12월", t1.dec, t2.dec, t3.dec]
      ]);

      var options = {
        title: (e.textContent).substr(0,4)+"년 월별 총 지각/조퇴/연장근무 수",
        animation: {
          easing: "inAndOut",
          startup: true,
          duration: 3000
        },
        backgroundColor:'#F8F8F8',
        hAxis: {
          title: "월",
          maxValue: 12
        },
        vAxis: {
          title: "인원수(명)",
          minValue: 0
        },
        curveType: "function",
        legend: { position: "right" 
        },
        legend:{
          alignment: 'center',
        },
        chartArea:{ backgroundColor:'#f1f8e9' }
      };

      var chart = new google.visualization.LineChart(
        document.getElementById("lateEarly_chart")
      );

      chart.draw(data, options);
    }
  });
});
