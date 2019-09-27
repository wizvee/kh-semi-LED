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

.item .item_body_select {
	display: grid;
	grid-template-columns: 90% 5% 5%;
	grid-column: 3;
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
</style>
<script type="text/javascript">

console.log("jsp 테스트");
console.log(<%=list.get(0).getEmpEnd()%>);
console.log(<%=list.get(4).getEmpEnd()%>);
</script>

<div id="content">

	<section class="item">
		<div class="item_header">
			<h2>근태 관리</h2>
		</div>

		<%
			if (!list.isEmpty()) {
		%>
		<div class="item_body_select">
			<select name="term">
				<option value="">조회 기간 선택</option>
				<option value="7">최근 일주일</option>
				<option value="1">1개월</option>
				<option value="3">3개월</option>
				<option value="6">6개월</option>
				<option value="12">1년</option>
			</select>
		</div>
		<div class="item_body item_atd">
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
							<svg width="110" height="110" viewBox="0 0 250 250"
								xmlns="http://www.w3.org/2000/svg" role="img"
								aria-labelledby="title desc" class="center">
              <title id="title">Teacher</title>
              <desc id="desc">Cartoon of a Caucasian woman smiling, and wearing black glasses and a purple shirt with white collar drawn by Alvaro Montoro.</desc>
             
              <defs>
                <clipPath id="scene">
                  <circle cx="125" cy="125" r="115" />
                </clipPath>
                <clipPath id="lips">
                  <path
									d="M 106,132 C 113,127 125,128 125,132 125,128 137,127 144,132 141,142  134,146  125,146  116,146 109,142 106,132 Z" />
                </clipPath>
              </defs>
              <circle cx="125" cy="125" r="120" fill="rgba(0,0,0,0.15)" />
              <g stroke="none" stroke-width="0" clip-path="url(#scene)">
                <rect x="0" y="0" width="250" height="250"
									fill="#b0d2e5" />
                <g id="head">
                  <path fill="none" stroke="#111111" stroke-width="2"
									d="M 68,103 83,103.5" />
                  <path class="hair"
									d="M 67,90 67,169 78,164 89,169 100,164 112,169 125,164 138,169 150,164 161,169 172,164 183,169 183,90 Z" />
                  <circle cx="125" cy="100" r="55" class="skin" />
                  <ellipse cx="102" cy="107" rx="5" ry="5" class="eyes"
									id="eye-left" />
                  <ellipse cx="148" cy="107" rx="5" ry="5" class="eyes"
									id="eye-right" />
                  <rect x="119" y="140" width="12" height="40"
									class="skin" />
                  <path class="line eyebrow"
									d="M 90,98 C 93,90 103,89 110,94" id="eyebrow-left" />
                  <path class="line eyebrow"
									d="M 160,98 C 157,90 147,89 140,94" id="eyebrow-right" />
                  <path stroke="#111111" stroke-width="4"
									d="M 68,103 83,102.5" />
                  <path stroke="#111111" stroke-width="4"
									d="M 182,103 167,102.5" />
                  <path stroke="#050505" stroke-width="3" fill="none"
									d="M 119,102 C 123,99 127,99 131,102" />
                  <path fill="#050505"
									d="M 92,97 C 85,97 79,98 80,101 81,104 84,104 85,102" />
                  <path fill="#050505"
									d="M 158,97 C 165,97 171,98 170,101 169,104 166,104 165,102" />
                  <path stroke="#050505" stroke-width="3"
									fill="rgba(240,240,255,0.4)"
									d="M 119,102 C 118,111 115,119 98,117 85,115 84,108 84,104 84,97 94,96 105,97 112,98 117,98 119,102 Z" />
                  <path stroke="#050505" stroke-width="3"
									fill="rgba(240,240,255,0.4)"
									d="M 131,102 C 132,111 135,119 152,117 165,115 166,108 166,104 166,97 156,96 145,97 138,98 133,98 131,102 Z" />
                  <path class="hair"
									d="M 60,109 C 59,39 118,40 129,40 139,40 187,43 189,99 135,98 115,67 115,67 115,67 108,90 80,109 86,101 91,92 92,87 85,99 65,108 60,109" />
                  <path id="mouth" fill="#d73e3e"
									d="M 106,132 C 113,127 125,128 125,132 125,128 137,127 144,132 141,142  134,146  125,146  116,146 109,142 106,132 Z" /> 
                  <path id="smile" fill="white"
									d="M125,141 C 140,141 143,132 143,132 143,132 125,133 125,133 125,133 106.5,132 106.5,132 106.5,132 110,141 125,141 Z"
									clip-path="url(#lips)" />
                </g>
                <g id="shirt">
                  <path fill="#8665c2"
									d="M 132,170 C 146,170 154,200 154,200 154,200 158,250 158,250 158,250 92,250 92,250 92,250 96,200 96,200 96,200 104,170 118,170 118,170 125,172 125,172 125,172 132,170 132,170 Z" />
                  <path id="arm-left" class="arm" stroke="#8665c2"
									fill="none" stroke-width="14"
									d="M 118,178 C 94,179 66,220 65,254" />
                  <path id="arm-right" class="arm" stroke="#8665c2"
									fill="none" stroke-width="14"
									d="M 132,178 C 156,179 184,220 185,254" />
                  <path fill="white"
									d="M 117,166 C 117,166 125,172 125,172 125,182 115,182 109,170 Z" />
                  <path fill="white"
									d="M 133,166 C 133,166 125,172 125,172 125,182 135,182 141,170 Z" />
                  <circle cx="125" cy="188" r="4" fill="#5a487b" />
                  <circle cx="125" cy="202" r="4" fill="#5a487b" />
                  <circle cx="125" cy="216" r="4" fill="#5a487b" />
                  <circle cx="125" cy="230" r="4" fill="#5a487b" />
                  <circle cx="125" cy="244" r="4" fill="#5a487b" />
                  <path stroke="#daa37f" stroke-width="1"
									class="skin hand" id="hand-left"
									d="M 51,270 C 46,263 60,243 63,246 65,247 66,248 61,255 72,243 76,238 79,240 82,243 72,254 69,257 72,254 82,241 86,244 89,247 75,261 73,263 77,258 84,251 86,253 89,256 70,287 59,278" /> 
                  <path stroke="#daa37f" stroke-width="1"
									class="skin hand" id="hand-right"
									d="M 199,270 C 204,263 190,243 187,246 185,247 184,248 189,255 178,243 174,238 171,240 168,243 178,254 181,257 178,254 168,241 164,244 161,247 175,261 177,263 173,258 166,251 164,253 161,256 180,287 191,278" /> 
                </g>
              </g>
            </svg>
						</div>
						<div class="more-info">
							<h1><%=e.getUserName()%></h1>
							<div class="coords">
								<span>지정 근무 시간</span> <span><%=e.getShift().getSftOn()%> ~ <%=e.getShift().getSftOff()%></span>
							</div>
							<div class="coords">
								<span>Position/Role</span> <span>City, Country</span>
							</div>
							<div class="stats">
								
								<div></div><div></div><div></div><div></div>
								<div></div><div></div><div></div><div></div>
							<div></div><div></div>
								
								
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

		<%
			} else {
		%>



		<div>
			<div class="card green">
				<div class="additional">
					<div class="user-card">
						<div class="level center">Level 13</div>
						<div class="points center">5,312 Points</div>
						<svg width="110" height="110" viewBox="0 0 250 250"
							xmlns="http://www.w3.org/2000/svg" role="img"
							aria-labelledby="title desc" class="center">
              <title id="title">Teacher</title>
              <desc id="desc">Cartoon of a Caucasian woman smiling, and wearing black glasses and a purple shirt with white collar drawn by Alvaro Montoro.</desc>
             
              <defs>
                <clipPath id="scene">
                  <circle cx="125" cy="125" r="115" />
                </clipPath>
                <clipPath id="lips">
                  <path
								d="M 106,132 C 113,127 125,128 125,132 125,128 137,127 144,132 141,142  134,146  125,146  116,146 109,142 106,132 Z" />
                </clipPath>
              </defs>
              <circle cx="125" cy="125" r="120" fill="rgba(0,0,0,0.15)" />
              <g stroke="none" stroke-width="0" clip-path="url(#scene)">
                <rect x="0" y="0" width="250" height="250"
								fill="#b0d2e5" />
                <g id="head">
                  <path fill="none" stroke="#111111" stroke-width="2"
								d="M 68,103 83,103.5" />
                  <path class="hair"
								d="M 67,90 67,169 78,164 89,169 100,164 112,169 125,164 138,169 150,164 161,169 172,164 183,169 183,90 Z" />
                  <circle cx="125" cy="100" r="55" class="skin" />
                  <ellipse cx="102" cy="107" rx="5" ry="5" class="eyes"
								id="eye-left" />
                  <ellipse cx="148" cy="107" rx="5" ry="5" class="eyes"
								id="eye-right" />
                  <rect x="119" y="140" width="12" height="40"
								class="skin" />
                  <path class="line eyebrow"
								d="M 90,98 C 93,90 103,89 110,94" id="eyebrow-left" />
                  <path class="line eyebrow"
								d="M 160,98 C 157,90 147,89 140,94" id="eyebrow-right" />
                  <path stroke="#111111" stroke-width="4"
								d="M 68,103 83,102.5" />
                  <path stroke="#111111" stroke-width="4"
								d="M 182,103 167,102.5" />
                  <path stroke="#050505" stroke-width="3" fill="none"
								d="M 119,102 C 123,99 127,99 131,102" />
                  <path fill="#050505"
								d="M 92,97 C 85,97 79,98 80,101 81,104 84,104 85,102" />
                  <path fill="#050505"
								d="M 158,97 C 165,97 171,98 170,101 169,104 166,104 165,102" />
                  <path stroke="#050505" stroke-width="3"
								fill="rgba(240,240,255,0.4)"
								d="M 119,102 C 118,111 115,119 98,117 85,115 84,108 84,104 84,97 94,96 105,97 112,98 117,98 119,102 Z" />
                  <path stroke="#050505" stroke-width="3"
								fill="rgba(240,240,255,0.4)"
								d="M 131,102 C 132,111 135,119 152,117 165,115 166,108 166,104 166,97 156,96 145,97 138,98 133,98 131,102 Z" />
                  <path class="hair"
								d="M 60,109 C 59,39 118,40 129,40 139,40 187,43 189,99 135,98 115,67 115,67 115,67 108,90 80,109 86,101 91,92 92,87 85,99 65,108 60,109" />
                  <path id="mouth" fill="#d73e3e"
								d="M 106,132 C 113,127 125,128 125,132 125,128 137,127 144,132 141,142  134,146  125,146  116,146 109,142 106,132 Z" /> 
                  <path id="smile" fill="white"
								d="M125,141 C 140,141 143,132 143,132 143,132 125,133 125,133 125,133 106.5,132 106.5,132 106.5,132 110,141 125,141 Z"
								clip-path="url(#lips)" />
                </g>
                <g id="shirt">
                  <path fill="#8665c2"
								d="M 132,170 C 146,170 154,200 154,200 154,200 158,250 158,250 158,250 92,250 92,250 92,250 96,200 96,200 96,200 104,170 118,170 118,170 125,172 125,172 125,172 132,170 132,170 Z" />
                  <path id="arm-left" class="arm" stroke="#8665c2"
								fill="none" stroke-width="14"
								d="M 118,178 C 94,179 66,220 65,254" />
                  <path id="arm-right" class="arm" stroke="#8665c2"
								fill="none" stroke-width="14"
								d="M 132,178 C 156,179 184,220 185,254" />
                  <path fill="white"
								d="M 117,166 C 117,166 125,172 125,172 125,182 115,182 109,170 Z" />
                  <path fill="white"
								d="M 133,166 C 133,166 125,172 125,172 125,182 135,182 141,170 Z" />
                  <circle cx="125" cy="188" r="4" fill="#5a487b" />
                  <circle cx="125" cy="202" r="4" fill="#5a487b" />
                  <circle cx="125" cy="216" r="4" fill="#5a487b" />
                  <circle cx="125" cy="230" r="4" fill="#5a487b" />
                  <circle cx="125" cy="244" r="4" fill="#5a487b" />
                  <path stroke="#daa37f" stroke-width="1"
								class="skin hand" id="hand-left"
								d="M 51,270 C 46,263 60,243 63,246 65,247 66,248 61,255 72,243 76,238 79,240 82,243 72,254 69,257 72,254 82,241 86,244 89,247 75,261 73,263 77,258 84,251 86,253 89,256 70,287 59,278" /> 
                  <path stroke="#daa37f" stroke-width="1"
								class="skin hand" id="hand-right"
								d="M 199,270 C 204,263 190,243 187,246 185,247 184,248 189,255 178,243 174,238 171,240 168,243 178,254 181,257 178,254 168,241 164,244 161,247 175,261 177,263 173,258 166,251 164,253 161,256 180,287 191,278" /> 
                </g>
              </g>
            </svg>
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