<%@page language="java" contentType="text/html; charset=UTF-8"
pageEncoding="UTF-8"%> <%@page import="com.semi.user.model.vo.User"%> <% User
loginUser = (User) session.getAttribute("loginUser"); %>
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
        <form
          action="<%=request.getContextPath()%>/userType.do"
          method="post"
          class="frm_enroll_area"
        >
          <p>가입 유형을 선택해주세요.</p>
          <div>
            <input type="radio" name="type" value="O" id="owner" />
            <label for="owner">사장님</label>
            <input type="radio" name="type" value="E" id="employee" />
            <label for="employee">종업원</label>
          </div>
          <input
            type="hidden"
            name="userId"
            value="<%=loginUser.getUserId()%>"
          />
          <button class="btn-primary">다음</button>
        </form>
      </div>
    </div>
  </body>
</html>
