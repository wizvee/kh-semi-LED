<%@page import="java.util.Arrays"%>
<%@page import="com.semi.sft.model.vo.Shift"%>
<%@page import="com.semi.emp.model.vo.Employee"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ include file="header.jsp"%>
<div id="content">
	<%
		ArrayList<Employee> empList = (ArrayList<Employee>) request.getAttribute("empList");
		ArrayList<Shift> sftList = (ArrayList<Shift>) request.getAttribute("sftList");
	%>
	<section class="item">
		<div class="item_header">
			<h2>전체 직원 관리</h2>
		</div>
		<div class="item_body item_mngEmp">
			<div class="mngEmp_header">
				<span class="focus">재직</span>
				<span>퇴직</span>
				<span>진행</span>
			</div>
			<div class="mngEmp_body">
				<div class="mngEmpWork_area focus">
					1
				</div>
				<div class="mngEmpResign_area">
					2
				</div>
				<div class="mngEmpEnroll_area">
					<%
						for (Employee e : empList) {
							if (e.getEmpType() == null) {
					%>
					<div class="empList_area">
						<img class="item_profile"
							src="<%=request.getContextPath()%>/upload/profile/<%=e.getProfilePic()%>">
						<%=e.getUserName()%>
						<div>
							<button class="btn-primary btn_Approval">승인</button>
							<input type="hidden" value="<%=e.getUserId()%>">
							<button class="btn-outline btn_Reject">거절</button>
						</div>
					</div>
					<%
							}
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
						<span>급여</span>
						<input type="text" class="inpt-outline" name="empWage" value="8350">
					</div>
					<div>
						<span>근무조 설정</span>
						<%
							if(sftList != null && !sftList.isEmpty()) {
								ArrayList<String> days= new ArrayList<>(Arrays.asList(sftList.get(0).getSftDay().split(",")));
						%>
							<div class="temp dropToggle select">
								<input type="hidden" name="sftId" value="<%=sftList.get(0).getSftId()%>">
								<span><%=sftList.get(0).getSftName() %></span>
								<div class="sftDay">
									<span <%=days.contains("일") ? "class='work'" : "" %>>일</span>
									<span <%=days.contains("월") ? "class='work'" : "" %>>월</span>
									<span <%=days.contains("화") ? "class='work'" : "" %>>화</span>
									<span <%=days.contains("수") ? "class='work'" : "" %>>수</span>
									<span <%=days.contains("목") ? "class='work'" : "" %>>목</span>
									<span <%=days.contains("금") ? "class='work'" : "" %>>금</span>
									<span <%=days.contains("토") ? "class='work'" : "" %>>토</span>
								</div>
								<span><%=sftList.get(0).getSftOn() %></span>
								<span><strong>~</strong></span>
								<span><%=sftList.get(0).getSftOff() %></span>
								<span><i class="fa fa-sort-desc" aria-hidden="true"></i></span>
							</div>
						<%
							}
						%>
						
						
						
						
						
						
						
						<%
							if(sftList != null && !sftList.isEmpty()) {
								for(int i = 0; i < sftList.size(); i++) {
								ArrayList<String> days= new ArrayList<>(Arrays.asList(sftList.get(i).getSftDay().split(",")));
						%>
						
						
						<%
								}
							}
						%>
					</div>
					<div>
						<span>적용 시작일</span>
						<input type="text" class="input-outline" name="empStart">
					</div>
					<button class="btn-outline btn_arvEmp">저장</button>
				</div>
			</div>
		</div>
	</section>
	<script src="<%=request.getContextPath()%>/dist/owner/manageEmp.js"></script>
</div>
<%@ include file="footer.jsp"%>