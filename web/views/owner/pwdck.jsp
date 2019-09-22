<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="com.semi.user.model.vo.User"%>

<%@ include file="header.jsp"%>

<style type="text/css">

</style>

<div id="content">
	<section class="item">
		<div class="item_header">
		<!-- 제발 이거 하고 자자 -->
			<h2>비밀번호 확인</h2>
		</div>
		<div class="item_body item_mypage">
		<form action="<%=request.getContextPath()%>/checkPasswordEnd.do" name="updateUserFrm" method="POST" onsubmit="return update_validate();">
			<input type="password" class="inpt-outline" name="pw">
			<input type="hidden" class="inpt-userId" value=<%=loginOwner.getUserId()%> name="userId">
			<button class="btn-primary">확인</button>
		</form>
		</div>
	</section>
</div>


<%@ include file="footer.jsp"%>
