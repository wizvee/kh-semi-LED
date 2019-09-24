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
		height: 50px;
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
		<form name="updateUserFrm" method="POST">
			<div class="item_body item_mypage">
				<input type="hidden" value=<%=infoUser.getUserId()%> name="userId">
				<input type="hidden" value=<%=infoUser.getPassword() %> name="infoUserPwd">
				<div>사진 : <img class="snb_profile" src="<%=request.getContextPath()%>/upload/profile/<%=loginOwner.getProfilePic() %>" alt="프로필 사진" name="infoUserPic"></div>
				
				<div>이메일 : <button class="btn-email"><%=infoUser.getEmail() %></button></div>
				
				<div>이름 : <button class="btn-name"><%=infoUser.getUserName() %></button></div>
				
				<div>휴대폰 : <button class="btn-phone"><%=infoUser.getUserPhone() %></button></div>
				
			</div>
		</form>
			
		<form name="updateUserFrmPw" method="POST">
			<div class="item_body item_mypage_pw">
				<span data-placeholder="현재 비밀번호">
                	<i class="fa fa-unlock-alt" aria-hidden="true"> 현재 비밀번호 : </i>
              	</span>
				<input type="password" class="inpt-outline" name="pw" id="pw">
				<div id="result_pw"></div>
				
				<span data-placeholder="새 비밀번호">
                	<i class="fa fa-unlock-alt" aria-hidden="true"> 신규 비밀번호 : </i>
              	</span>
				<input type="password" class="inpt-outline" name="nPw" id="nPw">
				<div id="result_nPw"></div>
				
				<span data-placeholder="새 비밀번호 확인">
                	<i class="fa fa-unlock-alt" aria-hidden="true"> 신규 비밀번호 확인 : </i>
              	</span>
				<input type="password" class="inpt-outline" name="nkPw" id="nkPw">				
				<div id="result_nkPw"></div>
				
				<button id="btn_checkPw" class="btn-primary" onclick="return false;">비밀번호 변경</button>	
			</div>
		</form>
	</section>
</div>

<script>
	$(function(){
		$(".btn-phone").click(function(){
			if($(this).parent().has('#phone').length==0){
				var tr=$("<tr id='phone'>");
				var html="<td style='display:none; text-align:left;' colspan='2'>"
				html+="<form action='<%=request.getContextPath()%>' method='post'>";
				html+='<textarea name="content" cols="60" rows="3"></textarea>';
				html+='<input type="submit" value="등록">';
				html+='</form></td>';
				tr.html(html);
				tr.insertAfter($(this).parent().parent()).children("td").slideDown(200);
			}else{
				$(this).parent().children("#phone").remove();
			}
		});
	});

	$(function(){
		$('#btn_checkPw').click(function(){
			$("#result_pw").html("");
			$("#result_nPw").html("");
			$("#result_nkPw").html("");
			var reg = /^(?=.*[a-zA-Z])((?=.*\d)|(?=.*\W)).{8,16}$/;
			if($('#pw').val().trim()<=0){
				$("#result_pw").html("현재 비밀번호를 입력하세요").css("color","red");
			} else if($('#nPw').val().trim()<=0){
				$("#result_nPw").html("새 비밀번호를 입력하세요").css("color","red");
			} else if($('#pw').val().trim()==$('#nPw').val().trim()){
				$("#result_nPw").html("현재 비밀번호와 다른 비밀번호를 입력하세요").css("color","red");				
			} else if($('#nkPw').val().trim()<=0){
				$("#result_nkPw").html("새 비밀번호 확인을 입력하세요").css("color","red");
			} else if($('#nPw').val().trim()!=$('#nkPw').val().trim()){
				$("#result_nkPw").html("새 비밀번호가 일치하지 않습니다").css("color","red");
			} else if($('#nPw').val().trim()!=reg){
				$("#result_nPw").html("8~16자 영문 대·소문자 사용").css("color","red");
			} else {
				$.ajax({
					url:"<%=request.getContextPath()%>/checkAjaxinfoPw.do",
					data:{pw:$('#pw').val().trim(), nPw:$('#nPw').val().trim(), userId:userInfo.userId},
					success:function(data){
						if(!data){
							$("#result_nkPw").html("비밀번호 변경 실패. 다시 시도해 주세요.").css("color","red");
						} else {
							location.href="/p_190826_semi/views/owner/pwdck.jsp";
						}
					}
				});
			}
		});
	});
</script>

<%@ include file="footer.jsp"%>
