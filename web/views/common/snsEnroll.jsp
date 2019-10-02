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
	<form action="<%=request.getContextPath()%>/snsLoginEndServlet" method="post">
	<input type="text" name="email" value=<%=email %>>
	<input type="text" name="password" value=<%=password %>>
	<input type="hidden" name="imageUrl" value=<%=imageUrl %>>
	<input type="text" name="name">
	<input type="text" name="phone">
	<input type="submit" value="가입"/>
	</form>
</body>
</html>