<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="com.semi.user.model.vo.User"%>

<%@ include file="header.jsp"%>

<style type="text/css">

</style>

<div id="content">
	<section class="item">
		<div class="item_header">
			<h2>비밀번호 확인</h2>
		</div>
		<div class="item_body item_mypage">
		<form action="<%=request.getContextPath()%>/checkPasswordEnd.do" name="updateUserFrm" method="POST" onsubmit="return update_validate();">
			<input type="password" class="inpt-outline" name="pw" id="pw">
			<input type="hidden" class="inpt-userId" value=<%=loginOwner.getUserId()%> name="userId">
			<button id="btn_checkPw" class="btn-primary">확인</button>
		</form>
		</div>
	</section>
</div>

<script>
	function update_validate(){
		var pw=$('#pw').val().trim();
		if(pw.length<=0){
			alert("비밀번호를 입력하세요.");
			$('#pw').focus();
			return false;
		}
		return true;
	}
	
	$(function(){
		$('#btn-checkPw').click(function(){
			if($(this).val().trim()!=null)
		})
	})

</script>


<%@ include file="footer.jsp"%>
