<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ page
	import="com.semi.user.model.vo.User,
com.semi.emp.model.vo.Employee,
java.util.List"%>
<%
	List<Employee> list = (List) request.getAttribute("empList");
%>

<%@ include file="header.jsp"%>
<style>
.item .item_atd {
	display: grid;
	grid-template-columns: repeat(2, 1fr);
	grid-template-rows: auto;
	grid-column-gap: 10%;
}

.calendar_body {


}

.item .item_atd div {
	margin-left: 20%
	margin-right:20%
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
	background: linear-gradient(#92bCa6, #A2CCB6);
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
    
    fieldset {
	border:5px solid #8B9EB1;
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

		<%if (!list.isEmpty()) {%>
		<div class="item_body">
			<div class="calendar_header">
            <i id="btn_calPrv" class="fa fa-chevron-left" aria-hidden="true"></i>
            <span>월</span> <i id="btn_calNxt" class="fa fa-chevron-right" aria-hidden="true"></i>
			<div class="calendar_body"></div>
         </div>
	







			
			
			
		</div>
		<script src="<%=request.getContextPath()%>/dist/calendar.js"></script>
		<script>
			var calendar = new Calendar();
		</script>
		
		<fieldset>
		<legend> 사업장 근무자 리스트 </legend>
		<div class="item_atd">
			<%
				for (Employee e : list) {
					if(e.getEmpEnd()!=null) {
						continue;
					}
			%>
			<%
				if (e.getSftId() != null && e.getAttendance() != null) {
			%>

			<div>
				<div class="card">
					<div class="additional">
						<div class="user-card">
							<div class="level center">LEVEL <%=e.getEmpLevel()!=null?e.getEmpLevel():"미설정"%></div>
							<div class="points center"><%=e.getShift().getSftName()%></div>
							<img alt="/upload/profil/emp_default.png" src=<%=e.getProfilePic()%>>
						</div>
						<div class="more-info">
							<h1><%=e.getUserName()%></h1>
							<div class="coords">
								<span>지정 근무 시간</span> <span><%=e.getShift().getSftOn()%> ~ <%=e.getShift().getSftOff()%></span>
							</div>
							<div class="coords">
								<span>Position/Role</span> <span>City, Country</span>
							</div>
					
						</div>
					</div>
					<div class="general">
						<h1><%=e.getUserName()%></h1>
						<hr>
						<p style="margin:10%">
						<%=e.getAttendance()!=null&&e.getAttendance().getAtdOn()!=null?e.getAttendance().getAtdOn().substring(0, 4):""%> 년 
						<%=e.getAttendance()!=null&&e.getAttendance().getAtdOn()!=null?e.getAttendance().getAtdOn().substring(4, 6):""%> 월
						<%=e.getAttendance()!=null&&e.getAttendance().getAtdOn()!=null?e.getAttendance().getAtdOn().substring(6, 8):""%> 일의
						<br> 근무 기록 입니다.
						</p>
						<div class="atd_check_late">
							<p>근무 시간</p>
							<p><%=e.getShift().getSftOn()%>
								~
								<%=e.getShift().getSftOff()%></p>
							<input type="hidden" id="stAtTime"
								value=<%=e.getAttendance()!=null?e.getAttendance().getStAtdTime():""%>> <input
								type="hidden" id="enAtTime"
								value=<%=e.getAttendance()!=null?e.getAttendance().getEnAtdTime():""%>> <input
								type="hidden" id="stSftime"
								value=<%=e.getAttendance()!=null?e.getAttendance().getStSftTime():""%>> <input
								type="hidden" id="enSftime"
								value=<%=e.getAttendance()!=null?e.getAttendance().getEnSftTime():""%>>
							<!-- 	<progress value="20" max="100"></progress> -->
							<div class="atd_progress_color">
								<div style="background-color: red;"></div>
								: 지각
								<div style="background-color: chartreuse;"></div>
								: 근무 시간
								<div style="background-color: dodgerblue;"></div>
								: 조퇴
							</div>
							<div class="atd_progress_wraper">

								<%
									double widthLate = 0;
									double widthEarly = 0;
									double widthNomal = 0;
									if(e.getAttendance()!=null){
									double stAtd = e.getAttendance().getStAtdTime();
												double enAtd = e.getAttendance().getEnAtdTime();
												double stSft = e.getAttendance().getStSftTime();
												double enSft = e.getAttendance().getEnSftTime();

												

												if (stSft < stAtd) {
													widthLate = ((100 - ((((enSft - stSft) - (stAtd - stSft)) / (enSft - stSft)) * 100)));
												}
												if (enSft > enAtd) {
													widthEarly = ((100 - ((((enSft - stSft) - (enSft - enAtd)) / (enSft - stSft)) * 100)));
												}
												widthNomal = 100 - (widthLate + widthEarly);
									}
								%>

								<div
									style="float:left; height:100%;  background-color: red; width:<%=widthLate%>%;"></div>
								<div
									style="float:left; height:100%; background-color: chartreuse; width :<%=widthNomal%>%;"></div>
								<div
									style="float:left; height:100%; background-color: dodgerblue; width :<%=widthEarly%>%;"></div>

							</div>
						</div>

						<span class="more">Mouse over the card for more info</span>
					</div>
				</div>
			</div>
</fieldset>

		<%
			} else {
		%>



		<div>
			<div class="card green">
				<div class="additional">
					<div class="user-card">
						<div class="level center">Level 13</div>
						<div class="points center">5,312 Points</div>
							<img alt=<%=request.getContextPath()%>"/upload/profil/emp_default.png" src=<%=e.getProfilePic()%>>
					</div>
					<div class="more-info">
							<h1><%=e.getUserName()%></h1>
							<div class="coords">
								<span>Group Name</span> <span>Joined January 2019</span>
							</div>
							<div class="coords">
								<span>Position/Role</span> <span>City, Country</span>
							</div>
							<div class="stats">
						
							
							
								
							</div>
						</div>
					</div>
				<div class="general">
					<h1>Jane Doe</h1>
					<div class="atd_check_late">
						<p>근무 시간</p>
					</div>


					<span class="more">Mouse over the card for more info</span>
				</div>
			</div>
</div>

<%
	}
%>
<%
	}
%>
</div>
<%
	} else {
%>
<div class="item_atd_divB">
	<p>아직 근무하는 직원이 없어요..!</p>
</div>
<div class="item_atd">
	<button class="atd_none_btn"
		onclick="location.href = '<%=request.getContextPath()%>/#'">근무조
		편성하기</button>
	<button class="atd_none_btn"
		onclick="location.href='<%=request.getContextPath()%>/views/owner/main.jsp'">돌아가기</button>
</div>
<%
	}
%>

</section>
</div>

<%@ include file="footer.jsp"%>