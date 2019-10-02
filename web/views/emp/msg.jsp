<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%
    String msg=(String)request.getAttribute("msg"); 
    String loc=(String)request.getAttribute("loc");
    String script=(String)request.getAttribute("script");
    
    
    %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>알림페이지</title>
</head>
<body>
<script>
alert('<%=msg%>'); 
<%=script!=null?script:""%>
location.href="<%=request.getContextPath()%><%=loc%>"; 


<% System.out.println(request.getContextPath()); %>


</script>

</body>
</html>