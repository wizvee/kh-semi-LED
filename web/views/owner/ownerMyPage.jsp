<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ page import="com.semi.user.model.vo.User"%>
<%
	User infoUser =(User)request.getAttribute("infoUser");
%>

<%@ include file="header.jsp"%>

<style type="text/css">
	.item .item_mypage {
		grid-template-rows : repeat(7, 1fr);
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
			</div>
		<input type="button" onclick="fn_updateinfoUser();" value="회원정보 변경"/>
		</form>
			
		<form name="updateUserFrmPw" method="POST" onsubmit="return updatePw_validate();">
			<div class="item_body item_mypage_pw">
				<div><a href="#" class="infoUserPwd">비밀번호 **********</a></div>
				
				<input type="button" onclick="fn_updateinfoUser();" value="비밀번호 변경"/>	
			</div>
		<input type="button" onclick="fn_backPage();" value="취소"/>
		</form>
	</section>
</div>

<%@ include file="footer.jsp"%>

<script>
	$(function(){
		$(".item_mypage>div>.infoUserName").on("click",function(){
			var input=$("<input>").attr({"type":"text","name":"userName"}).val($(this).html());
			$(this).parent().append(input);
			var removeVal=$(this).remove();
			console.log($(input).select());
			console.log(removeVal);
		})
	})
	
	function fn_updateinfoUser(){
		//수정 된 정보가 있다면 servlet에 전송하는 것.
		updateUserFrm.action="<%=request.getContextPath()%>/infoUser/infoUserUpdate.do";
		console.log(updateUserFrm.action);
		updateUserFrm.submit();
	}
</script>