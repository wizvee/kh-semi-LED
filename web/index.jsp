<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="com.semi.user.model.vo.User"%>
<% User loginUser = (User) session.getAttribute("loginUser"); %>
<!DOCTYPE html>
<html lang="ko">

<head>
  <meta charset="UTF-8" />
  <meta name="viewport" content="width=device-width, initial-scale=1.0" />
  <meta http-equiv="X-UA-Compatible" content="ie=edge" />
  <meta name="google-signin-client_id" content="YOUR_CLIENT_ID.apps.googleusercontent.com">
  <title>service : site menu</title>
  <!-- CSS -->
  <link rel="stylesheet" href="css/main.min.css" />
  <link rel="stylesheet" href="css/font-awesome.css" />
</head>

<body>
  <div id="wrap">
    <!-- gnb -->
    <nav class="gnb gnb_index">
      <div>기능소개</div>
      <div>사용방법</div>
      <div>고객센터</div>
    </nav>
    <!-- //gnb -->
  </div>
  <!-- container -->
  <div id="container" class="container_index">
    <!-- 폼모달 체크박스 -->
    <input type="checkbox" id="ck_pop_frm" />
    <!-- //폼모달 체크박스 -->
    <!-- section1 -->
    <div class="bg_video_area">
      <video src="videos/index.mp4" autoplay loop muted></video>
    </div>
    <section class="section_slide section_index">
      <!-- 타이포 구역 -->
      <div class="typo_area">
        <p>WELCOME</p>
        <% if (loginUser == null) { %>
        <label for="ck_pop_frm" class="btn-primary">LOGIN</label>
        <% } else { %>
        <button class="btn-primary" onclick="location.href='<%=request.getContextPath() %>/main.do'">
          MYPAGE
        </button>
        <% } %>
      </div>
      <!-- //타이포 구역 -->
      <!-- 로그인 및 회원가입 폼 -->
      <div id="pop_wrap">
        <!-- pop header -->
        <div id="pop_header">
          <div>
            <label id="btn_close" for="ck_pop_frm">
              <i class="fa fa-times" aria-hidden="true"></i>
            </label>
          </div>
          <span id="frm_login" class="active">로그인</span>
          <span id="frm_register">회원가입</span>
        </div>
        <!-- //pop header -->
        <!-- pop container -->
        <div id="pop_container">
          <!-- 로그인 폼 -->
          <form method="post" class="frm_login active">
            <div>
              <input type="email" name="userEmail" class="inpt-gradient" required />
              <span data-placeholder="이메일">
                <i class="fa fa-envelope" aria-hidden="true"></i>
              </span>
            </div>
            <div>
              <input type="password" name="userPw" class="inpt-gradient" required />
              <span data-placeholder="비밀번호">
                <i class="fa fa-unlock-alt" aria-hidden="true"></i>
              </span>
            </div>
            <div>
              <button id="btn_login" class="btn-gradient" onclick="return false;">
                로그인
              </button>
            </div>
            <div class="option_area">
              <input type="checkbox" name="" id="ck_keep" />
              <label for="ck_keep">로그인 유지</label>
              <a href="#">비밀번호 찾기</a>
            </div>
            <div class="msg_area"></div>
            <div>
              <button id="btn_fb" class="btn-primary">
                <i class="fa fa-facebook-square" aria-hidden="true"></i>
                페이스북으로 로그인
              </button>
            </div>
            <div>
              <button id="btn_gg" class="btn-primary">
                  <!-- <div class="g-signin2" data-onsuccess="onSignIn"></div> 구글 로그인 버튼 --> 
                <i class="fa fa-google-plus-square" aria-hidden="true"></i>
                구글로 로그인
              </button>
            </div>
          </form>
          <!-- //로그인 폼 -->
          <!-- 회원가입 폼 -->
          <form method="post" class="frm_register">
            <div>
              <input type="email" name="userEmail" class="inpt-gradient" id="uMail" required />
              <span data-placeholder="이메일">
                <i class="fa fa-envelope" aria-hidden="true"></i>
              </span>
            </div>
            <div>
              <input type="password" name="userPw" class="inpt-gradient" id="pw1" required />
              <span data-placeholder="비밀번호">
                <i class="fa fa-unlock-alt" aria-hidden="true"></i>
              </span>
            </div>
            <div>
              <input type="password" name="pw2" class="inpt-gradient" id="pw2" required />
              <span data-placeholder="비밀번호">
                <i class="fa fa-unlock-alt" aria-hidden="true"></i>
              </span>
            </div>
            <div>
              <input type="text" name="userName" class="inpt-gradient" required />
              <span data-placeholder="이름">
                <i class="fa fa-user" aria-hidden="true"></i>
              </span>
            </div>
            <div>
              <input type="tel" name="userPhone" class="inpt-gradient" required />
              <span data-placeholder="핸드폰">
                <i class="fa fa-phone-square" aria-hidden="true"></i>
              </span>
            </div>
            <div>
              <button id="btn_register" class="btn-gradient" onclick="return false;">
                회원가입
              </button>
            </div>
            <div class="msg_area"></div>
          </form>
          <!-- // 회원가입 폼 -->
        </div>
        <!-- //pop container -->
      </div>
    </section>
    <!-- //section1 -->
    <div class="section_slide">
      <p>슬라이드1</p>
    </div>
    <div class="section_slide">
      <p>슬라이드2</p>
    </div>
    <div class="section_slide">
      <p>슬라이드3</p>
    </div>
  </div>
  <!-- //container -->
  <!-- JavaScript Libraries -->
  <script src="https://apis.google.com/js/platform.js" async defer></script>
  <script type="text/javascript" src="js/jquery-3.4.1.min.js"></script>
  <script type="text/javascript" src="js/index.js"></script>
</body>

</html>