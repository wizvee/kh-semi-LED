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
				<div>
					<span>재직</span>
				</div>
				<div>
					<span>퇴직</span>
				</div>
				<div>
					<span>진행</span>
				</div>
			</div>
			<div class="mngEmp_body">
				<div class="mngEmpWork_area focus">
					<%
						for (Employee e : empList) {
							if(e.getEmpEnd() == null) {
					%>
					
					<%
							}
						}
					%>
				</div>
				<div class="mngEmpResign_area">2</div>
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
						<p>고용 형태</p>
						<div>
							<input type="hidden" name="empId" vlaue="">
							<input type="radio" name="empType" value="H" id="et-h">
							<label for="et-h">시급직</label>
							<input type="radio" name="empType" value="D" id="et-d">
							<label for="et-d">일당직</label>
							<input type="radio" name="empType" value="M" id="et-M">
							<label for="et-M">월급직</label>
						</div>
						<input type="text" class="inpt-underline" name="empWage">
					</div>
					<div>
						<p>근무조 설정</p>
					</div>
					<div>
						<p>적용 시작일</p>
					</div>
					<button class="btn-outline btn_enrollEmp">저장</button>
				</div>
			</div>
		</div>
	</section>
	<script src="<%=request.getContextPath()%>/js/owner/manageEmp.js"></script>
</div>
<%@ include file="footer.jsp"%>