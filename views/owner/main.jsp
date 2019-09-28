<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="header.jsp"%>
<div id="content" class="content_main">

	<section class="item">
		<div class="item_header">
			<h2>제목</h2>
		</div>
		<div class="item_body">
			<button id="test">테스트</button>
		</div>
	</section>
	
	<script>
		document.querySelector("#test").addEventListener("click", () => {
			userInfo.flag = "T";
			socket.send(JSON.stringify(userInfo));
			console.log("!");
		})
	</script>
</div>
<%@ include file="footer.jsp"%>