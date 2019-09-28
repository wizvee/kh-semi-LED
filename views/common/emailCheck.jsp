<%@page language="java" contentType="text/html; charset=UTF-8"
pageEncoding="UTF-8"%> <%@page import="com.semi.user.model.vo.User"%> <% boolean
send = request.getAttribute("send") != null ? (Boolean)
request.getAttribute("send") : false; User loginUser = (User)
session.getAttribute("loginUser"); String email = request.getParameter("email")
!= null ? request.getParameter("email") : loginUser.getEmail(); %>
<!DOCTYPE html>
<html lang="ko">
  <head>
    <meta charset="UTF-8" />
    <meta name="viewport" content="width=device-width, initial-scale=1.0" />
    <meta http-equiv="X-UA-Compatible" content="ie=edge" />
    <title>service : site menu</title>
    <!-- CSS -->
    <link
      rel="stylesheet"
      href="<%=request.getContextPath()%>/css/main.min.css"
    />
    <link
      rel="stylesheet"
      href="<%=request.getContextPath()%>/css/font-awesome.css"
    />
  </head>

  <body>
    <div id="wrap">
      <div id="container" class="container_enroll">
        <div class="email_area">
          <% if (request.getAttribute("broken") != null) { if ((Boolean)
          request.getAttribute("broken")) { %>
          <p>인증 메일이 완료되었습니다!</p>
          <p>이제 모든 서비스를 이용하실 수 있습니다.</p>
          <span>
            <button
              class="btn-primary"
              onclick="location.href='<%=request.getContextPath()%>'"
            >
              돌아가기
            </button>
          </span>
          <% } else { %>
          <p>이미 인증되거나 만료된 키입니다!</p>
          <p>로그인 혹은 회원가입을 진행해주세요.</p>
          <span>
            <button
              class="btn-primary"
              onclick="location.href='<%=request.getContextPath()%>'"
            >
              돌아가기
            </button>
          </span>
          <% } %> <% } else if (send || !loginUser.isMailCheck()) { %>
          <p>인증 메일이 발송되었습니다!</p>
          <p>이메일 인증을 완료해주세요.</p>
          <span>
            <button class="btn-outline" onclick="fn_reSend();">재전송</button>
            <button
              class="btn-primary"
              onclick="location.href='<%=request.getContextPath()%>'"
            >
              돌아가기
            </button>
          </span>
          <% } else if (!send) { %>
          <p>인증 메일 발송에 실패하였습니다!</p>
          <p>이메일 주소를 확인해주세요.</p>
          <span>
            <button class="btn-primary" onclick="fn_reSend();">재전송</button>
            <button
              class="btn-primary"
              onclick="location.href='<%=request.getContextPath()%>'"
            >
              돌아가기
            </button>
          </span>
          <% } %>
        </div>
      </div>
    </div>
    <!-- JavaScript Libraries -->
    <script src="<%=request.getContextPath()%>/js/jquery-3.4.1.min.js"></script>
    <script>
      function fn_reSend() {
        $.ajax({
          type: "post",
          async: false,
          url: "/p_190826_semi/emailSend.do",
          dataType: "text",
          data: { email: "<%=email%>" },
          success: function() {
            $(".email_area p").remove();
            $(".email_area button")[0].remove();
            $(".email_area").prepend("<p>이메일 인증을 완료해주세요.</p>");
            $(".email_area").prepend("<p>인증 메일이 다시 발송되었습니다!</p>");
          }
        });
      }
    </script>
  </body>
</html>
