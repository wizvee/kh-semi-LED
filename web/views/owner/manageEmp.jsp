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
							if (true) {
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
							<button class="btn-outline btn_Approval">승인</button>
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
						<input type="hidden" name="empId" vlaue="">
						<p>고용 형태</p>
						<div>
							<input type="radio" name="empType" value="H" id="empTypeH">
							<label for="empTypeH">시급직</label> <input type="radio" name="empType" value="D"
								id="empTypeD"> <label for="empTypeD">일당직</label> <input type="radio" name="empType"
								value="M" id="empTypeM"> <label for="empTypeM">월급직</label>
							<div class="inptIcon_area">
								<input type="text" class="inpt-outline" name="empWage">
								<span>원</span>
							</div>
						</div>
						<p>근무조 설정</p>
						<div class="busShift_area">
							<%
								for (Shift s : sftList) {
							%>
							<div class="sftItem">
								<input type="hidden" name="sftId" value="<%=s.getSftId()%>">
								<span><%=s.getSftName()%></span>
								<div>
									<%
										ArrayList<String> sftDays = new ArrayList<>(Arrays.asList(s.getSftDay().split(",")));
											String[] wkd = { "일", "월", "화", "수", "목", "금", "토" };
											for (String str : wkd) {
												if (sftDays.contains(str)) {
									%>
									<span class="workDay contain"><%=str%></span>
									<%
										} else {
									%>
									<span class="workDay"><%=str%></span>
									<%
										}
											}
									%>
								</div>
								<span><%=s.getSftOn()%></span> <span><%=s.getSftOff()%></span>
							</div>
							<%
								}
							%>
						</div>
						<p>적용 시작일</p>
						<div>
							<div class="inptIcon_area">
								<input type="text" class="inpt-outline" name="sftId"> <span><i
										class="fa fa-calendar-plus-o" aria-hidden="true"></i></span>
							</div>
							<button class="btn-outline btn_enrollEmp">저장</button>
						</div>
					</div>
				</div>
			</div>
		</div>
	</section>
	<script src="<%=request.getContextPath()%>/js/owner/manageEmp.js"></script>
</div>
<%@ include file="footer.jsp"%>