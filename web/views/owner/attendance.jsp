<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>


<%@ include file="header.jsp"%>
<style>
.item .item_atd {
	display: grid;
	grid-template-columns: repeat(2, 1fr);
	grid-template-rows: auto;
	grid-column-gap: 10%;
}


.item .item_atd div p {
	text-align: right;
}

.item_atd_divB {
	background-image: url(<%=request.getContextPath()%>/images/EmployeeNone.png);
	background-repeat: no-repeat;
	background-position: center;
	background-size: cover;
	opacity: 0.4;
	margin-top: 20px;
	height: 700px;
}

.item_atd_divB p {
	position: relative;
	top: 50%;
	width: 100%;
	text-align: center;
	font-size: 3em;
	font-weight: bolder;
	padding-bottom: 0.9em;
	color: rgb(10, 0, 0);
}


.item_atd_employeeList {
	border: 1px solid black;
}

.atd_check_late {
	margin-top: 10%;
	
}

.atd_check_late p {
	text-align: center;
	font-size: 1em;
}

.atd_progress_wraper {
	display: inline-block;
	background-color: blanchedalmond;
	height: 20px;
	width: 100%;
}

.atd_progress_color div {
	width: 1em;
	height: 1em;
	border: 1px solid black;
	display: inline-block;
	margin-left: 5px;
}

.atd_progress_color {
	font-size: 1em;
	margin-top: 4%;
	margin-bottom: 3%;
}

@import url('https://fonts.googleapis.com/css?family=Abel');

.skin {
	fill: #eab38f;
}

.eyes {
	fill: #1f1f1f;
}

.hair {
	fill: #2f1b0d;
}

.line {
	fill: none;
	stroke: #2f1b0d;
	stroke-width: 2px;
} 

.card {
	width: 450px;
	height: 250px;
	background-color: #fff;
	background: linear-gradient(#f8f8f8, #fff);
	box-shadow: 0 8px 16px -8px rgba(0, 0, 0, 0.4);
	border-radius: 6px;
	overflow: hidden;
	position: relative;
	margin: 1.5rem;
}

.card h1 {
	text-align: center;
}

.card .additional {
	position: absolute;
	width: 150px;
	height: 100%;
	background: linear-gradient(#dE685E, #EE786E);
	transition: width 0.4s;
	overflow: hidden;
	z-index: 2;
}

.card.green .additional {
position: absolute;
	width: 150px;
	height: 100%;
	background: linear-gradient(#dE685E, #EE786E);
	transition: width 0.4s;
	overflow: hidden;
	z-index: 2;
	}

.card:hover .additional {
	width: 100%;
	border-radius: 0 5px 5px 0;
}

.card .additional .user-card {
	width: 150px;
	height: 100%;
	position: relative;
	float: left;
}

.card .additional .user-card::after {
	content: "";
	display: block;
	position: absolute;
	top: 10%;
	right: -2px;
	height: 80%;
	border-left: 2px solid rgba(0, 0, 0, 0.025);
	*/
}

.card .additional .user-card .level, .card .additional .user-card .points
	{
	top: 15%;
	color: #fff;
	text-transform: uppercase;
	font-size: 0.75em;
	font-weight: bold;
	background: rgba(0, 0, 0, 0.15);
	padding: 0.125rem 0.75rem;
	border-radius: 100px;
	white-space: nowrap;
}

.card .additional .user-card .points {
	top: 85%;
}

.card .additional .user-card svg {
	top: 50%;
}

.card .additional .more-info {
	width: 300px;
	float: left;
	position: absolute;
	left: 150px;
	height: 100%;
}

.card .additional .more-info h1 {
	color: #fff;
	margin-bottom: 0;
}

.card.green .additional .more-info h1 {
	color: #224C36;
}

.card .additional .coords {
	margin: 0 1rem;
	color: #fff;
	font-size: 1rem;
}

.card.green .additional .coords {
	color: #325C46;
}

.card .additional .coords span+span {
	float: right;
}

.card .additional .stats {
	width: 95%;
	font-size: 2rem;
	display: flex;
	position: absolute;
	bottom: 1rem;
	left: 1rem;
	right: 1rem;
	top: auto;
	color: #fff;
}

.card.green .additional .stats {
	color: #325C46;
}

.card .additional .stats>div {
	display: grid;
	grid-template-columns: repeat(7,1fr);
	grid-template-rows: 9px;
	border: 1px solid red;
	margin: 7px;
	width: 10px;
	height: 10px;
	background-color: #f8f8f8;
}

.card .additional .stats i {
	display: block;
}


.card .general {
	width: 300px;
	height: 100%;
	position: absolute;
	top: 0;
	right: 0;
	z-index: 1;
	box-sizing: border-box;
	padding: 1rem;
	padding-top: 0;
}

.card .general .more {
	position: absolute;
	bottom: 1rem;
	right: 1rem;
	font-size: 0.9em;
}

    .center {
      position: absolute;
      top: 50%;
      left: 50%;
      -webkit-transform: translate(-50%, -50%);
    }

#calendar {
  width: 80%;
  
}


/* ------- Agenda View ------- */
#agendaView > table{
    width: 100%;
    min-height: 500px;
    background: #FFF;
    
}

#agendaView .fc-agendaView-event-start,
#agendaView .fc-agendaView-event-end{
    width: 20%;
}

#agendaView .fc-agendaView-event-title{
    width: 60%;
}
#agendaView th{
    height: 30px;
    font-size: 14px;
    vertical-align: text-bottom;
    font-weight: bold;
    padding: 10px 0 0 10px;
}

#agendaView td{
    height: 30px;
    padding: 10px 0 0 10px;
    font-size: 14px;
    vertical-align: text-bottom;    
}

fieldset {
	border: 4px solid #e5e5e5;
}

fieldset > legend {
	font-size: 1.5em;
}

</style>


<div id="content">

	<section class="item">
		<div class="item_header">
			<h2>근태 관리</h2>
		</div>


		<div class="item_body calendar_area">
			<div class="calendar_header">
				<i id="btn_calPrv" class="fa fa-chevron-left" aria-hidden="true"></i>
				<span>월</span> <i id="btn_calNxt" class="fa fa-chevron-right" aria-hidden="true"></i>
			</div>
			<div class="calendar_body"></div>
		</div>
		
		
		
		
		<script src="<%=request.getContextPath()%>/src/owner/subCal.js">
			$('.calendar_body').ready(function(){
				$.ajax({
					url: <%=request.getContextPath()%>"/ajaxCal.do",
					data : {"date" : this.target.getMonth()+1},
					method : "post",
					dataType: "json",
					success: function(data) {
						var date=$(".date");
						for(var i = 0; i < data.length; i++) {
							if(data[i][1]==0){
								var flagDate=data[i][0];
								$.each(date,function(i,v){
									var dateId=$(v).attr("id");
									if(dateId!=undefined){
										var temp=flagDate.substring(flagDate.length-2);
										var temp2=dateId.substring(dateId.length-2)
										if(temp==temp2){
											$(v).css("borderBottom","5px solid red");
										}
									}
								});  
							});
		</script>

			<fieldset>
					<legend> 사업장 근무자 리스트 </legend>
					<div class="item_atd">


	</div>

	</fieldset>

</section>
</div>

<%@ include file="footer.jsp"%>