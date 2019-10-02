<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    
<%
 String password=(String)request.getAttribute("id");
 String email=(String)request.getAttribute("email");
 String imageUrl=(String)request.getAttribute("imageUrl");
%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<script src="<%=request.getContextPath() %>/js/jquery-3.4.1.min.js"></script>
<title>추가 정보를 입력해주세요!</title>
</head>
<body>
	<form action="<%=request.getContextPath()%>/snsLoginEndServlet.do" method="post">
	<input type="text" name="email" value=<%=email %>>
	<input type="password" name="password" value=<%=password %>>
	<input type="hidden" name="imageUrl" value=<%=imageUrl %>>
	<input type="text" name="name" placeholder="이름 입력">
	<input type="text" name="phone" placeholder="전화번호 입력 (-) 사용" >
	<input type="submit" value="가입"/>
	</form>
</body>
</html>