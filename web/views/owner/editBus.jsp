<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page
	import="com.semi.bus.model.vo.Business"
 %>
<%
	Business bi=(Business)request.getAttribute("busInfo");
%>
<%@ include file="header.jsp"%>
<div id="content">

	<section class="item">
		<div class="item_header">
			<h2>사업장 정보</h2>
		</div>
		<div class="item_body">
			<div>사업장 이름 : <%=bi.getBusName() %></div>
			<div>사업자 이름 : <%=bi.getOwnName() %></div>
			<div>사업장 번호 : <%=bi.getBusNum() %></div>
			<div>사업장 전화번호 : <%=bi.getBusPhone() %></div>
			<div>사업장 주소: <%=bi.getBusAddr() %></div>
			<div>월급일 : <%=bi.getBusDate() %></div>
			<div></div>
		</div>
	</section>
</div>
<script>

</script>
<%@ include file="footer.jsp"%>