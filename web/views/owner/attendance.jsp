<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ page
	import="com.semi.user.model.vo.User,
com.semi.emp.model.vo.Employee,
java.util.List"%>
<%
	List<Employee> list = (List) request.getAttribute("empList");
%>
<%-- <!-- <%=request.getPatameter() -->

 --%>
<%@ include file="header.jsp"%>
<style>
.item .item_atd {
	display: grid;
	grid-template-columns: repeat(2, 1fr);
	grid-gap: 10px;
}

.item .item_atd div {
	
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
	border: 1px solid black;
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

.atd_progressBar_late {
	float:left;
	background-color: red;
	border: none;
}

.atd_progressBar_work {
	float:left;
	background-color: chartreuse;
	border: none;
}

.atd_progressBar_ealryLeave {
	float:left;
	background-color: dodgerblue;
	border: none;
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
</style>

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
			%>
			<%
				if (e.getSftId() != null) {
			%>
			<div class="item_atd_employeeList">
				<h1
					style="text-align: right; font-weight: bolder; color: red; font-size: 2em;"><%=e.getUserType()%></h1>
				<br>
				<p>
					직원 명 :
					<%=e.getUserName()%></p>
				<p>
					근무 조 :
					<%=e.getShift().getSftName()%></p>
				<p>
					근무 시간
					<%=e.getShift().getSftOn()%>
					~
					<%=e.getShift().getSftOff()%></p>

			</div>

			<div class="atd_check_late">
				<p>근무 시간</p>
				<p><%=e.getShift().getSftOn()%>
					~
					<%=e.getShift().getSftOff()%></p>
				<input type="hidden" id="stAtTime"
					value=<%=e.getAttendance().getStAtdTime()%>> <input
					type="hidden" id="enAtTime"
					value=<%=e.getAttendance().getEnAtdTime()%>> <input
					type="hidden" id="stSftime"
					value=<%=e.getAttendance().getStSftTime()%>> <input
					type="hidden" id="enSftime"
					value=<%=e.getAttendance().getEnSftTime()%>>
				<!-- 	<progress value="20" max="100"></progress> -->
				<div class="atd_progress_color">
					<div style="background-color: red;"></div>
					: 지각
					<div style="background-color: chartreuse;"></div>
					: 근무 시간
					<div style="background-color: dodgerblue;"></div>
					: 조퇴
				</div>
				<div class="atd_progress_wraper" >
		
					<%
					double stAtd = e.getAttendance().getStAtdTime();
					double enAtd = e.getAttendance().getEnAtdTime();
					double stSft = e.getAttendance().getStSftTime();
					double enSft = e.getAttendance().getEnSftTime();
					
					double widthLate = 0;
					double widthEarly = 0;
					double widthNomal = 0;
					
					if (stSft < stAtd) {
						widthLate = ((100 - ((((enSft - stSft) - (stAtd - stSft)) / (enSft - stSft)) * 100)));
					}
					if (enSft > enAtd) {
						widthEarly = ((100 - ((((enSft - stSft) - (enSft - enAtd)) / (enSft - stSft)) * 100)));
					}
					widthNomal = 100 - (widthLate + widthEarly);
					
					%>
					
					<div style="float:left; height:100%;  background-color: red; width:<%= widthLate%>%;"></div>
					<div style= "float:left; height:100%; background-color: chartreuse; width :<%= widthNomal %>%;"></div>
					<div style= "float:left; height:100%; background-color: dodgerblue; width :<%= widthEarly %>%;"></div>
					
				</div>
			</div>
			<%
				} else {
			%>
			<div class="item_atd_employeeList">
				<h1
					style="text-align: right; font-weight: bolder; color: red; font-size: 2em;">신입</h1>
				<br>
				<p>
					직원 명 :
					<%=e.getUserName()%></p>
				<p>직원의 근무시간을 설정해 주세요</p>
			</div>
			<div class="atd_check_late">
			<p>근무 시간</p>
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

<script type="text/javascript">

		
			</script>
</div>
<%@ include file="footer.jsp"%>