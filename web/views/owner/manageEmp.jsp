<%@page import="java.util.Arrays"%>
<%@page import="com.semi.sft.model.vo.Shift"%>
<%@page import="com.semi.emp.model.vo.Employee"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ include file="header.jsp"%>
<%
	ArrayList<Employee> empList = (ArrayList<Employee>) request.getAttribute("empList");
	ArrayList<Shift> sftList = (ArrayList<Shift>) request.getAttribute("sftList");

	ArrayList<Employee> workList = new ArrayList<>();
	ArrayList<Employee> resignList = new ArrayList<>();
	ArrayList<Employee> enrollList = new ArrayList<>();

	for (Employee e : empList) {
		if (e.getEmpType() == null)
			enrollList.add(e);
		else if (e.getEmpEnd() != null)
			resignList.add(e);
		else
			workList.add(e);
	}
%>
<script>
	window.onload = function () {
		document.querySelector("#btnSnbMngEmp").classList.add("focus");
	}
</script>
<div id="content">

	<section class="item">
		<div class="item_header">
			<h2>전체 직원 관리</h2>
		</div>
		<div class="item_body item_mngEmp">
			<div class="mngEmp_header">
				<span class="focus">재직</span> <span>퇴직</span> <span>진행</span>
			</div>
			<div class="mngEmp_body">
				<div class="mngEmpWork_area mngDiv focus">
					<%
						if (!workList.isEmpty()) {
						int count = 0;
							for (Employee e : workList) {
								String type = null;
								switch (e.getEmpType()) {
								case "H":
									type = "시급";
									break;
								case "D":
									type = "일당";
									break;
								case "M":
									type = "월급";
									break;
								}

								String sftName = null;
								for(Shift s : sftList) {
									if(e.getSftId().equals(s.getSftId()))
										sftName = s.getSftName();
								}
					%>
					<div class="viewEmp_area">
						<div class="view_header">
							<b>#<%=++count %></b>
						</div>
						<div class="view_body focus">
							<img src="<%=request.getContextPath()%>/upload/profile/<%=e.getProfilePic()%>">
							<span><%=e.getUserName()%></span>
							<span><%=e.getUserPhone()%></span>
							<div class="detailInfo">
								<span class="<%=e.getEmpType()%> tag"><%=type %></span>
								<span class="tag"><%=String.format("%,d", e.getEmpWage()) %></span>
								<span class="tag"><%=sftName %></span>
							</div>
							<button class="btn-primary btn_viewEdit">편집</button>
						</div>
						<div class="edit_body">
							<span><%=e.getUserName()%></span>
							<select class="editEmpType inpt-outline">
								<option value="H" <%=e.getEmpType().equals("H") ? "selected" : "" %>>시급</option>
								<option value="D" <%=e.getEmpType().equals("D") ? "selected" : "" %>>일당</option>
								<option value="M" <%=e.getEmpType().equals("M") ? "selected" : "" %>>월급</option>
							</select>
							<input type="text" class="editEmpWage inpt-outline" value="<%=e.getEmpWage() %>">
							<select class="editSftId inpt-outline">
								<%
									for(Shift s : sftList) {
								%>
								<option value="<%=s.getSftId()%>"
									<%=e.getSftId().equals(s.getSftId()) ? "selected" : "" %>><%=s.getSftName() %>
								</option>
								<%
									}
								%>
							</select>
							<div>
								<button class="btn-primary btn_editEmp">수정</button>
								<input type="hidden" value="<%=e.getUserId()%>">
								<button class="btn-outline btn_backView">돌아가기</button>
							</div>
						</div>
					</div>
					<%
						}
						} else {
					%>
					<span class="mngEmpMsg_area">재직중인 직원이 없습니다.</span>
					<%
						}
					%>
				</div>
				<div class="mngEmpResign_area mngDiv">
					<%
						if (!resignList.isEmpty()) {
							for (Employee e : resignList) {
					%>

					<%
						}
						} else {
					%>
					<span class="mngEmpMsg_area">퇴직한 직원이 없습니다.</span>
					<%
						}
					%>
				</div>
				<div class="mngEmpEnroll_area mngDiv">
					<%
						if (!enrollList.isEmpty()) {
							for (Employee e : enrollList) {
					%>
					<div class="empList_area">
						<img class="item_profile"
							src="<%=request.getContextPath()%>/upload/profile/<%=e.getProfilePic()%>">
						<%=e.getUserName()%>
						<div>
							<button class="btn-primary btn_approval">승인</button>
							<input type="hidden" value="<%=e.getUserId()%>">
							<button class="btn-outline btn_reject">거절</button>
						</div>
					</div>
					<%
						}
						} else {
					%>
					<span>진행중인 직원이 없습니다.</span>
					<%
						}
					%>
				</div>
				<div class="approvalEmpInfo_area">
					<div>
						<span>고용 형태</span>
						<div>
							<input type="radio" name="empType" value="H" id="et-h" checked>
							<label for="et-h">시급직</label>
							<input type="radio" name="empType" value="D" id="et-d">
							<label for="et-d">일당직</label>
							<input type="radio" name="empType" value="M" id="et-M">
							<label for="et-M">월급직</label>
						</div>
					</div>
					<div>
						<span>급여 설정</span> <input type="text" class="inpt-outline" name="empWage" value="8350">
					</div>
					<div>
						<span>근무조 설정</span>
						<div class="sftList">
							<%
								ArrayList<String> days = new ArrayList<>(Arrays.asList(sftList.get(0).getSftDay().split(",")));
							%>
							<div class="sftSelect">
								<div id="<%=sftList.get(0).getSftId()%>" class="sftItem">
									<span><b><%=sftList.get(0).getSftName()%></b></span>
									<div class="sftDay">
										<span <%=days.contains("일") ? "class='work'" : ""%>>일</span>
										<span <%=days.contains("월") ? "class='work'" : ""%>>월</span>
										<span <%=days.contains("화") ? "class='work'" : ""%>>화</span>
										<span <%=days.contains("수") ? "class='work'" : ""%>>수</span>
										<span <%=days.contains("목") ? "class='work'" : ""%>>목</span>
										<span <%=days.contains("금") ? "class='work'" : ""%>>금</span>
										<span <%=days.contains("토") ? "class='work'" : ""%>>토</span>
									</div>
									<span><%=sftList.get(0).getSftOn()%></span>
									<span><strong>~</strong></span>
									<span><%=sftList.get(0).getSftOff()%></span>
								</div>
							</div>
							<div class="dropMenu">
								<%
									for(Shift s : sftList) {
										days = new ArrayList<>(Arrays.asList(s.getSftDay().split(",")));
								%>
								<div id="<%=s.getSftId()%>" class="sftItem">
									<span><b><%=s.getSftName()%></b></span>
									<div class="sftDay">
										<span <%=days.contains("일") ? "class='work'" : ""%>>일</span>
										<span <%=days.contains("월") ? "class='work'" : ""%>>월</span>
										<span <%=days.contains("화") ? "class='work'" : ""%>>화</span>
										<span <%=days.contains("수") ? "class='work'" : ""%>>수</span>
										<span <%=days.contains("목") ? "class='work'" : ""%>>목</span>
										<span <%=days.contains("금") ? "class='work'" : ""%>>금</span>
										<span <%=days.contains("토") ? "class='work'" : ""%>>토</span>
									</div>
									<span><%=s.getSftOn()%></span>
									<span><strong>~</strong></span>
									<span><%=s.getSftOff()%></span>
								</div>
								<%
									}
								%>
							</div>
						</div>
					</div>
					<div>
						<span>적용 시작일</span> <input type="text" class="inpt-outline" name="empStart">
					</div>
					<button class="btn-outline btn_arvEmp">저장</button>
				</div>
			</div>
		</div>
	</section>
	<script src="<%=request.getContextPath()%>/dist/owner/manageEmp.js"></script>
</div>
<%@ include file="footer.jsp"%>