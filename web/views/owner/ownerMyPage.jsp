<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ page import="com.semi.user.model.vo.User"%>
<%
	User infoUser =(User)session.getAttribute("infoUser");
%>

<%@ include file="header.jsp"%>

<style type="text/css">
.item .item_mypage {
	grid-template-rows: repeat(1, 1fr);
}

.item .item_mypage .pic {
	display: table-cell;
	vertical-align: middle;
}

.item .item_mypage .pic .snb_profile {
	width: 50px;
	height: 50px;
}

.btn_pic, .btn_email, .btn_name, .btn_phone, .btn_password, .btn_quit{
	text-align: left;
	height: 60px;
	width: 100%;
}

.item .item_mypage_pic, .item .item_mypage_pw, .item .item_mypage_phone, .item .item_mypage_name, .item .item_mypage_email {
	display: none;
}

/* .item .item_mypage_pic{
	height: 80px;
} */

</style>

<div id="content">
	<section class="item">
		<div class="item_header">
			<h2>개인 정보</h2>
		</div>
		<form name="updateUserFrm" method="POST">
			<div class="item_body item_mypage">
				<input type="hidden" name="userId" value=<%=infoUser.getUserId() %>>
				<input type="hidden" name="infoUserPwd"
					value=<%=infoUser.getPassword() %>>
				<button id="btn_pic_view" class="btn_pic" onclick="return false;">
					<div class="pic">
						사진 : <img class="snb_profile" src="<%=request.getContextPath()%>/upload/profile/<%=loginOwner.getProfilePic() %>" alt="프로필 사진" name="infoUserPic">
					</div>
				</button>
				<div class="item_body item_mypage_pic">
					<button id="btn_orgin_pic" class="btn-primary" onclick="return false;">기본 프로필로 변경</button>
					<button id="btn_new_pic" class="btn-primary" onclick="fn_new_pic(); return false;">새 프로필 변경</button>
				</div>

				<button id="btn_email_view" class="btn_email" onclick="return false;">
					<div>
						이메일 :
						<%=infoUser.getEmail() %>
					</div>
				</button>
				<div class="item_body item_mypage_email">
					<i class="fa fa-unlock-alt" aria-hidden="true"> 변경 할 이메일 : </i> 
					<input type="email" class="inpt-outline" name="email" id="email" placeholder="이메일을 입력하세요.">
					<button id="btn_email" class="btn-primary" onclick="return false;">이메일 변경</button>
				</div>

				<button id="btn_name_view" class="btn_name" onclick="return false;">
					<div>
						<span>이름 :</span>
						<span><%=infoUser.getUserName() %></span>
					</div>
				</button>
				<div class="item_body item_mypage_name">
					<i class="fa fa-unlock-alt" aria-hidden="true"> 변경 할 이름 : </i>
					<input type="text" class="inpt-outline" name="name" id="name" placeholder="이름을 입력하세요.">
					<div id="result_name"></div>
					<button id="btn_name" class="btn-primary" onclick="return false;">이름 변경</button>
				</div>

				<button id="btn_phone_view" class="btn_phone" onclick="return false;">
					<div>
						<span>휴대폰 :</span>
						<span><%=infoUser.getUserPhone()%></span>
					</div>
				</button>
				<div class="item_body item_mypage_phone">
					<i class="fa fa-unlock-alt" aria-hidden="true"> 변경 할 휴대폰 번호 : </i> 
					<input type="text" class="inpt-outline" name="phone" id="phone" placeholder="-포함 입력하세요.">
					<div id="result_phone"></div>
					<button id="btn_phone" class="btn-primary" onclick="return false;"> 휴대폰 번호 변경</button>
				</div>
				

				<button id="btn_password_view" class="btn_password" onclick="return false;">
					<div>비밀번호 : **********</div>
				</button>

				<div class="item_body item_mypage_pw">
					<span data-placeholder="현재 비밀번호"> 
					<i class="fa fa-unlock-alt" aria-hidden="true"> 현재 비밀번호 : </i>
					</span> 
					<input type="password" class="inpt-outline" name="pw" id="pw" placeholder="현재 비밀번호를 입력하세요.">
					<div id="result_pw"></div>

					<span data-placeholder="새 비밀번호"> 
					<i class="fa fa-unlock-alt" aria-hidden="true"> 새 비밀번호 : </i>
					</span> <input type="password" class="inpt-outline" name="nPw" id="nPw" placeholder="새 비밀번호를 입력하세요.">
					<div id="result_nPw"></div>

					<span data-placeholder="새 비밀번호 확인"> 
					<i class="fa fa-unlock-alt" aria-hidden="true"> 새 비밀번호 확인 : </i>
					</span> <input type="password" class="inpt-outline" name="nkPw" id="nkPw" placeholder="새 비밀번호 확인을 입력하세요.">
					<div id="result_nkPw"></div>

					<button id="btn_checkPw" class="btn-primary" onclick="return false;">비밀번호 변경</button>
				</div>
				
				<button id="btn_quit_view" class="btn_quit" onclick="return false;">
					<div>
						<span>회원탈퇴 :</span>
						<span><%=loginOwner.getJoinDate()%>회원 가입일</span>
					</div>
				</button>
				<div class="item_body item_mypage_phone">
					<i class="fa fa-unlock-alt" aria-hidden="true"> 변경 할 휴대폰 번호 : </i> 
					<input type="text" class="inpt-outline" name="phone" id="phone" placeholder="-포함 입력하세요.">
					<div id="result_phone"></div>
					<button id="btn_phone" class="btn-primary" onclick="return false;"> 휴대폰 번호 변경</button>
				</div>

			</div>
		</form>
	</section>
</div>

<script>
	
	function fn_new_pic(){
		console.log("gg");
	}

	$(function(){
	 	$(".btn_pic").click(function(){
	 		var area = document.querySelectorAll(".item_mypage_pic")[0];
	 		if(area.style.display==""||area.style.display == "none")
	 			area.style.display = "block";
	 		else
	 			area.style.display = "none";
	 	});
	 });
	
	$(function(){
	 	$(".btn_email").click(function(){
	 		var area = document.querySelectorAll(".item_mypage_email")[0];
	 		if(area.style.display==""||area.style.display == "none")
	 			area.style.display = "block";
	 		else
	 			area.style.display = "none";
	 	});
	 });
	
	$(function(){
	 	$(".btn_name").click(function(){
	 		var area = document.querySelectorAll(".item_mypage_name")[0];
	 		if(area.style.display==""||area.style.display == "none")
	 			area.style.display = "block";
	 		else
	 			area.style.display = "none";
	 	});
	 });
	
	$(function(){
	 	$(".btn_phone").click(function(){
	 		var area = document.querySelectorAll(".item_mypage_phone")[0];
	 		if(area.style.display==""||area.style.display == "none")
	 			area.style.display = "block";
	 		else
	 			area.style.display = "none";
	 	});
	 });
	
	$(function(){
	 	$(".btn_password").click(function(){
	 		var area = document.querySelectorAll(".item_mypage_pw")[0];
	 		if(area.style.display==""||area.style.display == "none")
	 			area.style.display = "block";
	 		else
	 			area.style.display = "none";
	 	});
	 });

	$(function(){
		$('#btn_name').click(function(){
			var area = document.querySelectorAll(".item_mypage_name")[0];
			$("#result_name").html("");
			if($('#name').val().trim().length<=0){
				$("#result_name").html("이름을 입력하세요.").css("color","red");
			}
			else{
				$.ajax({
					url:"<%=request.getContextPath()%>/changeAjaxinfoUpdateName.do",
					data:{name : $('#name').val().trim(), userId:userInfo.userId},
					success:function(data){
						if(!data){
							$("#result_name").html("이름 변경 실패. 다시 시도해 주세요.").css("color","red");
						} else{
							area.style.display = "none";
							$('#btn_name_view').find('div>span').last().html($('#name').val().trim());
							$('.snb_own').find('nav>ul>li').eq(1).html($('#name').val().trim());
						}
					}
				});
			}
		});
	});
	
	$(function(){
		$('#btn_phone').click(function(){
			var regExp = /01(0|1|6|7|8|9)-(\d{4}|\d{3})-\d{4}$/g;
			var area = document.querySelectorAll(".item_mypage_phone")[0];
			$("#result_phone").html("");
			
			if($('#phone').val().trim().length<=0){
				$("#result_phone").html("휴대폰 번호를 입력하세요.").css("color","red");
			}
			else if($('#phone').val().trim().length>0 && !regExp.test($('#phone').val().trim())){
				$("#result_phone").html("다시 입력해 주세요.").css("color","red");
			}
			else{
				$.ajax({
					url:"<%=request.getContextPath()%>/changeAjaxinfoUpdatePhone.do",
					data:{phone : $('#phone').val().trim(), userId:userInfo.userId},
					success:function(data){
						if(!data){
							$("#result_phone").html("휴대폰 번호 변경 실패. 다시 시도해 주세요.").css("color","red");
						} else{
							area.style.display = "none";
							$('#btn_phone_view').find('div>span').last().html($('#phone').val().trim());
						}
					}
				});
			}
		});
	});
	
	$(function(){
		$('#btn_checkPw').click(function(){
			$("#result_pw").html("");
			$("#result_nPw").html("");
			$("#result_nkPw").html("");
			var reg = /^(?=.*[a-zA-Z])((?=.*\d)|(?=.*\W)).{8,16}$/;
			if($('#pw').val().trim().length<=0){
				$("#result_pw").html("현재 비밀번호를 입력하세요").css("color","red");
			} else if($('#nPw').val().trim().length<=0){
				$("#result_nPw").html("새 비밀번호를 입력하세요").css("color","red");
			} else if($('#pw').val().trim()==$('#nPw').val().trim()){
				$("#result_nPw").html("현재 비밀번호와 다른 비밀번호를 입력하세요").css("color","red");				
			} else if($('#nkPw').val().trim().length<=0){
				$("#result_nkPw").html("새 비밀번호 확인을 입력하세요").css("color","red");
			} else if($('#nPw').val().trim()!=$('#nkPw').val().trim()){
				$("#result_nkPw").html("새 비밀번호가 일치하지 않습니다").css("color","red");
			} else if(!reg.test($('#nPw').val().trim())){
				$("#result_nPw").html("8~16자 영문 대·소문자 사용").css("color","red");
			} else {
				$.ajax({
					url:"<%=request.getContextPath()%>/changeAjaxinfoUpdatePw.do",
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
