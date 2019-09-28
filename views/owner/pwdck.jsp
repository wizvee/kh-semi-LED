<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="com.semi.user.model.vo.User"%>
<%
	User infoUser =(User)session.getAttribute("infoUser");
%>

<%@ include file="header.jsp"%>

<!-- <style type="text/css">
 	.item .item_mypage div b{
		color : blue;
	}
	.item .item_mypage .email{
		border-top : 2px solid #000;
	} 
</style> -->

<div id="content">
	<section class="item">
		<div class="item_header">
			<h2>회원정보 확인</h2>
		</div>
		<div class="item_body item_mypage">
		<form name="updateUserFrm" method="POST">
			<%-- <div><b><%=infoUser.getUserName()%></b>님의 정보를 안전하게 보호하기 위해 비밀번호를 다시 한번 확인 합니다.</div>
			<br>
			<div class="email">아이디(이메일) : <%=infoUser.getEmail()%></div> --%>
			비밀번호 : <input type="password" class="inpt-outline" name="pw" id="pw">
			<div id="result"></div>
			<button id="btn_checkPw" class="btn-primary" onclick="return false;">확인</button>
		</form>
		</div>
	</section>
</div>

<script>
	$(function(){
		$('#btn_checkPw').click(function(){
			if($('#pw').val().trim()<=0){
				$("#result").html("비밀번호를 입력하세요.").css("color","red");
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
				});
			}
		});
	});

</script>


<%@ include file="footer.jsp"%>
