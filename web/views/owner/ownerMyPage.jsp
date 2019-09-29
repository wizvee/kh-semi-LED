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

.item .item_mypage #pic {
	display: table-cell;
	vertical-align: middle;
}

.item .item_mypage #pic .snb_profile {
	border: 2px solid #fff;
    width: 50px;
    height: 50px;
    border-radius: 50%;
}

.btn_pic, .btn_email, .btn_name, .btn_phone, .btn_password, .btn_quit{
	text-align: left;
	height: 60px;
	width: 100%;
}

.item .item_mypage_pic, .item .item_mypage_pw, .item .item_mypage_phone, .item .item_mypage_name, .item .item_mypage_email, .item .item_mypage_quit {
	display: none;
    margin-top: 5px;
    grid-template-columns: 50px 1fr 100px;
    line-height: 50px;
    border-radius: 10px;
    border: 1px solid #e6e6e6;
}

.item_mypage .btn_pic, .item_mypage .btn_email, .item_mypage .btn_name, .item_mypage .btn_phone, .item_mypage .btn_password, .item_mypage .btn_quit{
    display: grid;
    margin-top: 5px;
    grid-template-columns: 50px 1fr 100px;
    height: 70px;
    line-height: 50px;
    border-radius: 10px;
    border: 1px solid #e6e6e6;
}

.item .item_mypage_pw, .item .item_mypage_phone, .item .item_mypage_name, .item .item_mypage_email, .item .item_mypage_quit {
	display: none;
	padding-top: 10px;
	padding-bottom: 10px;
}

.item .item_mypage_pic {
	display: none;
	padding-bottom: 10px;
	height: 80px;
}

.sub_profile {
	border: 2px solid #fff;
    width: 100px;
    height: 100px;
    border-radius: 50%;
    box-shadow: 5px 5px 7px rgba(0,0,0,0.1);
}

#profile, #ori_profile {
	display:none;
}

/* #chevron-right-name{
	position: relative;
	/* left:950px; */
	/* left:88.8%; */
} */

#chevron-right-phone{
	/* position: relative;
	left:81.3%; */
}

#chevron-right-pw{
	/* position: relative;
	left:84.6%; */
}

#chevron-right-quit{
	/* position: relative;
	left:75%; */
}

label {
	margin: 0;
    padding: 7px 13px;
    height: 30px;
	box-sizing: border-box;
    vertical-align: top;
    -webkit-appearance: none;
	border: 0;
    border-radius: 5px;
    outline: none;
}

.mypage_header {
	display: grid;
	grid-template-columns: 70px 1fr 40px;
    font-size: 95%;
}
.mypage_header .mypage_frist_span {
	
}
</style>

<div id="content">
	<section class="item">
		<div class="item_header">
			<h2>개인 정보</h2>
		</div>
		<form name="updateUserFrm" method="POST">
			<div class="item_body item_mypage">
				<input type="hidden" name="userId" value=<%=infoUser.getUserId() %>>
				<input type="hidden" name="infoUserPwd" value=<%=infoUser.getPassword() %>>
				<button id="btn_pic_view" class="btn_pic" onclick="return false;">
					<div class="mypage_header" id="pic">
						<span class="mypage_frist_span"><b>프로필 :</b></span>
						<span><img class="snb_profile" src="<%=request.getContextPath()%>/upload/profile/<%=loginOwner.getProfilePic() %>" alt="프로필 사진" name="infoUserPic"></span>
					</div>
				</button>
				<div class="item_body item_mypage_pic">
					<button id="btn_ori_pic" class="btn-primary" onclick="return false;">기본 프로필 변경</button>
					<label id="btn_new_pic" class="btn-primary" for="profile">새 프로필 변경</label>
					<input type="file" name="profile" id="profile">
				</div>

				<button id="btn_email_view" class="btn_email" onclick="return false;">
					<div class="mypage_header">
						<span class="mypage_frist_span"><b>이메일 :</b></span>
						<span><%=infoUser.getEmail() %></span>
					</div>
				</button>
				<div class="item_body item_mypage_email">
					<i class="fa fa-unlock-alt" aria-hidden="true"> 변경 할 이메일 : </i> 
					<input type="email" class="inpt-outline" name="email" id="email" placeholder="이메일을 입력하세요.">
					<input type="text" class="inpt-outline" name="email_number" id="email_number" placeholder="인증번호를 입력하세요.">
					<button id="btn_email" class="btn-primary" onclick="return false;">인증번호 전송</button>
					<button id="btn_email" class="btn-primary" onclick="return false;">이메일 변경</button>
				</div>

				<button id="btn_name_view" class="btn_name" onclick="return false;">
					<div class="mypage_header">
						<span><b>이&nbsp;&nbsp;&nbsp;름 :</b></span>
						<span><%=infoUser.getUserName() %></span>
						<span><i class="fa fa-chevron-right" id="chevron-right-name" aria-hidden="true"></i></span>
					</div>
				</button>
				<div class="item_body item_mypage_name">
					<i class="fa far fa-id-badge" aria-hidden="true"> 변경 할 이름 : </i>
					<input type="text" class="inpt-outline" name="name" id="name" placeholder="이름을 입력하세요.">
					<div id="result_name"></div>
					<button id="btn_name" class="btn-primary" onclick="return false;">이름 변경</button>
				</div>

				<button id="btn_phone_view" class="btn_phone" onclick="return false;">
					<div class="mypage_header">
						<span><b>휴대폰 :</b></span>
						<span><%=infoUser.getUserPhone()%></span>
						<span><i class="fa fa-chevron-right" id="chevron-right-phone" aria-hidden="true"></i></span>
					</div>
				</button>
				<div class="item_body item_mypage_phone">
					<i class="fa fas fa-phone" aria-hidden="true"> 변경 할 휴대폰 번호 : </i> 
					<input type="text" class="inpt-outline" name="new_phone" id="new_phone" placeholder="-포함 입력하세요.">
					<div id="result_phone"></div>
					<button id="btn_new_phone" class="btn-primary" onclick="return false;"> 휴대폰 번호 변경</button>
				</div>
				

				<button id="btn_password_view" class="btn_password" onclick="return false;">
					<div class="mypage_header">
						<span><b>비밀번호 :</b></span>
						<span>********</span>
						<span><i class="fa fa-chevron-right" id="chevron-right-pw" aria-hidden="true"></i></span>
					</div>
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
					<div class="mypage_header">
						<span><b>회원탈퇴 :</b></span>
						<span>회원 가입일 <%=infoUser.getJoinDate()%></span>
						<span><i class="fa fa-chevron-right" id="chevron-right-quit" aria-hidden="true"></i></span>
					</div>
				</button>
				<div class="item_body item_mypage_quit">
					<i class="fa fas fa-exclamation" aria-hidden="true"> 정상적인 회원 탈퇴를 위해 이메일과 비밀번호를 입력하세요.</i> 
					<input type="email" class="inpt-outline" name="quit_email" id="quit_email" placeholder="이메일를 입력하세요.">
					<input type="password" class="inpt-outline" name="quit_pw" id="quit_pw" placeholder="비밀번호를 입력하세요.">
					<input type="hidden" name="quit_type" id="quit_type" value="T">
					<div id="result_quit"></div>
					<button id="btn_quit" class="btn-primary" onclick="return false;">회원탈퇴</button>
				</div>

			</div>
		</form>
	</section>
</div>

<script>
	 	var picarea = document.querySelectorAll(".item_mypage_pic")[0];
	 	var emailarea = document.querySelectorAll(".item_mypage_email")[0];
	 	var namearea = document.querySelectorAll(".item_mypage_name")[0];
	 	var phonearea = document.querySelectorAll(".item_mypage_phone")[0];
	 	var pwdarea = document.querySelectorAll(".item_mypage_pw")[0];
	 	var quitarea = document.querySelectorAll(".item_mypage_quit")[0];
	 	
	$(function(){
	 	$(".btn_pic").click(function(){	
	 		if(picarea.style.display==""||picarea.style.display == "none") {
	 			picarea.style.display = "block";
	 			emailarea.style.display = "none";
	 			namearea.style.display = "none";
	 			phonearea.style.display = "none";
	 			pwdarea.style.display = "none";
	 			quitarea.style.display = "none";
	 		}
	 		else{
	 			picarea.style.display = "none";	 			
	 		}
	 	});
	 });
	
	$(function(){
	 	$(".btn_email").click(function(){
	 		if(emailarea.style.display==""||emailarea.style.display == "none") {
	 			picarea.style.display = "none";
	 			emailarea.style.display = "block";
				namearea.style.display = "none";
				phonearea.style.display = "none";
				pwdarea.style.display = "none";
				quitarea.style.display = "none";	 			
	 		}
	 		else
	 			emailarea.style.display = "none";
	 	});
	 });
	
	$(function(){
	 	$(".btn_name").click(function(){
	 		
	 		if(namearea.style.display==""||namearea.style.display == "none") {
	 			picarea.style.display = "none";
	 			emailarea.style.display = "none";
 				namearea.style.display = "block";
 				phonearea.style.display = "none";
 				pwdarea.style.display = "none";
 				quitarea.style.display = "none";	 			
	 		}
	 		else
	 			namearea.style.display = "none";
	 	});
	 });
	
	$(function(){
	 	$(".btn_phone").click(function(){
	 		
	 		if(phonearea.style.display==""||phonearea.style.display == "none"){
	 			picarea.style.display = "none";
	 			emailarea.style.display = "none";
 				namearea.style.display = "none";
 				phonearea.style.display = "block";
 				pwdarea.style.display = "none";
 				quitarea.style.display = "none";		 			
	 		}
	 		else
	 			phonearea.style.display = "none";
	 	});
	 });
	
	$(function(){
	 	$(".btn_password").click(function(){
	 		
	 		if(pwdarea.style.display==""||pwdarea.style.display == "none") {
	 			picarea.style.display = "none";
	 			emailarea.style.display = "none";
				namearea.style.display = "none";
				phonearea.style.display = "none";
				pwdarea.style.display = "block";
				quitarea.style.display = "none";	 			
	 		}
	 		else
	 			pwdarea.style.display = "none";
	 	});
	 });

	$(function(){
	 	$(".btn_quit").click(function(){
	 		
	 		if(quitarea.style.display==""||quitarea.style.display == "none"){
	 			picarea.style.display = "none";
	 			emailarea.style.display = "none";
				namearea.style.display = "none";
				phonearea.style.display = "none";
				pwdarea.style.display = "none";
				quitarea.style.display = "block";
	 			
	 		}
	 		else
	 			quitarea.style.display = "none";
	 	});
	 });
	
	$(function(){
		$("#btn_ori_pic").click(function(){
			$.ajax({
				url: "<%=request.getContextPath()%>/ajaxoriproFile.do",
				data: {userId:userInfo.userId},
				success:function(data){
					$('.snb_profile').attr("src", contextPath + "upload/profile/own_default.png");
				}
			});
		});
	});
	
	$(function(){
		$("#profile").change(function(){
			var fd = new FormData();
			fd.append("upfile",$(this)[0].files[0]);
			fd.append("userId",userInfo.userId);
			$.ajax({
				url:"<%=request.getContextPath()%>/ajaxproFile.do",
				data:fd,
				type:"post",
				processData:false,
				contentType:false,
				success:function(data){
					$('.snb_profile').attr("src", contextPath + "upload/profile/" + data);
				}
			});
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
							$('#btn_name_view').find('div>span').eq(1).html($('#name').val().trim());
							$('.snb_own').find('nav>span').html($('#name').val().trim());
						}
					}
				});
			}
		});
	});
	
	$(function(){
		$('#btn_new_phone').click(function(){
			var regExp = /01(0|1|6|7|8|9)-(\d{4}|\d{3})-\d{4}$/g;
			var area = document.querySelectorAll(".item_mypage_phone")[0];
			$("#result_phone").html("");
			
			if($('#new_phone').val().trim().length<=0){
				$("#result_phone").html("휴대폰 번호를 입력하세요.").css("color","red");
			}
			else if($('#new_phone').val().trim().length>0 && !regExp.test($('#new_phone').val().trim())){
				$("#result_phone").html("다시 입력해 주세요.").css("color","red");
			}
			else{
				$.ajax({
					url:"<%=request.getContextPath()%>/changeAjaxinfoUpdatePhone.do",
					data:{phone : $('#new_phone').val().trim(), userId:userInfo.userId},
					success:function(data){
						if(!data){
							$("#result_phone").html("휴대폰 번호 변경 실패. 다시 시도해 주세요.").css("color","red");
						} else{
							area.style.display = "none";
							$('#btn_phone_view').find('div>span').eq(1).html($('#new_phone').val().trim());
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
	
	$(function(){
		$('#btn_quit').click(function(){
			$.ajax({
				url:"<%=request.getContextPath()%>/quitAjaxinfoUser.do",
				data:{email:$('#quit_email').val(), pw:$('#quit_pw').val(), quittype:$('#quit_type').val()},
				success:function(data){
					if(!data){
						$("#result_quit").html("회원탈퇴 실패. 다시 시도해 주세요.").css("color","red");
					} else {					
						location.href="/p_190826_semi";
					}
				}
			});
		});
	});
</script>

<%@ include file="footer.jsp"%>
