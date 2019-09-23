<%@page import="com.semi.emp.model.vo.Employee"%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="header.jsp"%>
<div id="content">
	<%
		ArrayList<Employee> empList = (ArrayList<Employee>) request.getAttribute("empList");
	%>
	<section class="item">
		<div class="item_header">
			<h2>전체 직원 관리</h2>
		</div>
		<div class="item_body item_mngEmp">
			<div class="mngEmp_header">
				<div><span>재직</span></div>
				<div><span>퇴직</span></div>
				<div><span>진행</span></div>
			</div>
			<div class="mngEmp_body">
				<div class="mngEmpWork_area focus">
					1
				</div>
				<div class="mngEmpResign_area">
					2
				</div>
				<div class="mngEmpEnroll_area">
					<% for(Employee e : empList) { %>
					<div class="empList_area">
						<img class="item_profile"
							src="<%=request.getContextPath()%>/upload/profile/<%=e.getProfilePic()%>">
						<%=e.getUserName() %>
						<div>
							<button class="btn-outline btn_Approval">승인</button>
							<input type="hidden" name="mngEmp_empId" value="<%=e.getUserId()%>">
							<button class="btn-outline btn_Reject">거절</button>
						</div>
					</div>
					<% } %>
				</div>
				<div class="approvalEmpInfo_area">
					<div>
						<p>고용 형태</p>
						<div>
							<input type="radio" name="type" id="empTypeH">
							<label for="empTypeH"></label>
							<input type="radio" name="type" id="empTypeD">
							<input type="radio" name="type" id="empTypeM">
							<input type="text" name="" id="">
						</div>
					</div>
				</div>
			</div>
		</div>
	</section>
	<script src="<%=request.getContextPath()%>/js/owner/manageEmp.js"></script>
</div>
<%@ include file="footer.jsp"%>