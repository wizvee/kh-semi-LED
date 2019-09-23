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
					<div><img class="mngEmp_profile" src="<%=request.getContextPath()%>/upload/profile/<%=e.getProfilePic()%>">
					<span><%=e.getUserName() %></span></div>
					<% } %>
				</div>
			</div>
		</div>
	</section>
	<script src="<%=request.getContextPath()%>/js/owner/manageEmp.js"></script>
</div>
<%@ include file="footer.jsp"%>