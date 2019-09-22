<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="header.jsp"%>
<div id="content" class="content_main">

	<section class="item">
		<div class="item_header">
			<h2>알림 목록(임시)</h2>
		</div>
		<div class="item_body">
			<% for(Notification n : notiList) { %>
			<a href="<%=request.getContextPath()+n.getNotiUrl()%>"><%=n.getNotiMsg() %></a>
			<% } %>
		</div>
	</section>

</div>
<%@ include file="footer.jsp"%>