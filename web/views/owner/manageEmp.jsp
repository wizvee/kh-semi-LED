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
		<div class="item_body">
			<div class="emp_work">
				<p>재직</p>
				<%
					for (Employee e : empList) {
						if (e.getEmpType() != null) {
				%>
				<p><%=e.getUserName()%></p>
				<%
						}
					}
				%>
			</div>
			<div class="emp_resign">
				<p>퇴직</p>
			</div>
			<div class="emp_enroll">
				<p>진행중</p>
				<%
					for (Employee e : empList) {
						if (e.getEmpType() == null) {
				%>
				<p><%=e.getUserName()%></p>
				<%
						}
					}
				%>
			</div>
		</div>
	</section>

</div>
<%@ include file="footer.jsp"%>