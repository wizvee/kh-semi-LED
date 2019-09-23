<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ page import="com.semi.user.model.vo.User"%>
<%
	User infoUser =(User)session.getAttribute("infoUser");
%>

<%@ include file="header.jsp"%>

<style type="text/css">
	.item .item_mypage {
		grid-template-rows : repeat(4, 1fr);
	}
	.item .item_mypage div {
		height: 100px;
		border-bottom: 1px solid #000;
	}
	.item .item_mypage div .snb_profile{
		width: 50px;
		height: 50px;
	}
</style>

<div id="content">
	<section class="item">
		<div class="item_header">
			<h2>개인 정보</h2>
		</div>
		<form name="updateUserFrm" method="POST" onsubmit="return update_validate();">
			<div class="item_body item_mypage">
				<div style='display:none;'><p class="infoUserId">userId <%=infoUser.getUserId()%></div>
				<input type="hidden" value=<%=infoUser.getUserId()%> name="userId">
				<input type="hidden" value=<%=infoUser.getPassword() %> name="infoUserPwd">
				<div>사진 : <img class="snb_profile" src="<%=request.getContextPath()%>/upload/profile/<%=loginOwner.getProfilePic() %>" alt="프로필 사진" name="infoUserPic"></div>
				<div>이메일 : <input type="text" value=<%=infoUser.getEmail()%> name="infoUserEmail"></div>
				<div>이름 : <input type="text" value=<%=infoUser.getUserName()%> name="infoUserName"></div>
				<div>휴대폰 : <input type="text" value=<%=infoUser.getUserPhone()%> name="infoUserPhone"></div>
				<button id="btn_updateinfo" class="btn-primary" onclick="return false;">회원정보 변경</button>
			</div>
		</form>
			
		<form name="updateUserFrmPw" method="POST">
			<div class="item_body item_mypage_pw">
				현재 비밀번호 : <input type="password" class="inpt-outline" name="pw" id="pw">
				<div id="result_pw"></div>
				새 비밀번호 : <input type="password" class="inpt-outline" name="nPw" id="nPw">
				<div id="result_nPw"></div>
				새 비밀번호 확인 : <input type="password" class="inpt-outline" name="nkPw" id="nkPw">				
				<div id="result_nkPw"></div>
				<button id="btn_checkPw" class="btn-primary" onclick="return false;">비밀번호 변경</button>	
			</div>
		</form>
	</section>
</div>

<script>
	$(function(){
		$('#btn_checkPw').click(function(){
			$("#result_pw").html("");
			$("#result_nPw").html("");
			$("#result_nkPw").html("");
			if($('#pw').val().trim()<=0){
				$("#result_pw").html("현재 비밀번호를 입력하세요.").css("color","red");
			} else if($('#nPw').val().trim()<=0){
				$("#result_nPw").html("새 비밀번호를 입력하세요.").css("color","red");
			} else if($('#pw').val().trim()==$('#nPw').val().trim()){
				$("#result_nPw").html("현재 비밀번호와 다른 비밀번호를 입력하세요.").css("color","red");				
			} else if($('#nkPw').val().trim()<=0){
				$("#result_nkPw").html("새 비밀번호 확인을 입력하세요.").css("color","red");
			} else if($('#nPw').val().trim()!=$('#nkPw').val().trim()){
				$("#result_nkPw").html("새 비밀번호가 일치하지 않습니다.").css("color","red");
			} else {
				$.ajax({
					url:"<%=request.getContextPath()%>/checkAjaxinfoPw.do",
					data:{pw:$('#pw').val().trim(), nPw:$('#nPw').val().trim(), userId:userInfo.userId},
					success:function(data){
						if(!data){
							$("#result_nkPw").html("비밀번호 변경 실패.").css("color","red");
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
