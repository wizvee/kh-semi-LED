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
			<div id="result"></div>
			<button id="btn_checkPw" class="btn-primary" onclick="return false;">확인</button>
		</form>
		</div>
	</section>
</div>

<script>
	/*  function update_validate(){
		var pw=$('#pw').val().trim();
		if(pw.length<=0){
			alert("비밀번호를 입력하세요.");
			$('#pw').focus();
			return false;
		}
		return true;
	} */
	
	$(function(){
		$('#btn_checkPw').click(function(){
			if($('#pw').val().trim()<=0){
				$.ajax({
					url:"<%=request.getContextPath()%>/checkAjaxPw.do",
					data:{pw:$(this).val(), userId:$('#userId').val()},
					success:function(data){
						if(!data){
							$("#result").html("비밀번호를 입력하세요.").css("color","red");
						}
					}
				})
			} else {
				$.ajax({
					url:"<%=request.getContextPath()%>/checkAjaxPw.do",
					data:{pw:$('#pw').val().trim(), userId:userInfo.userId},
					success:function(data){
						if(!data){
							$("#result").html("비밀번호 일치하지 않습니다.").css("color","red");
						} else {
							location.href="/p_190826_semi/views/owner/ownerMyPage.jsp";
						}
					}
				})
			}
		});
	});

</script>


<%@ include file="footer.jsp"%>
