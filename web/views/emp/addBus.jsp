<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="com.semi.user.model.vo.User"%>
<%@ include file="header.jsp"%>
<div id="content">
	<section class="item">
		<div class="item_header">
			<h2>사업장 등록</h2>
		</div>
		<div class="item_body">
			<div class="addBus_area">
				<div class="search_area">
					<input type="text" id="input" class="inpt-outline" placeholder="사업장 검색 ..."
						onkeypress="javascript:if(event.keyCode==13){fn_search(); return false;}" />
					<span><i class="fa fa-search" aria-hidden="true"></i></span>
				</div>
			</div>
			<div class="addInfo_area">
				<form class="frm_addBus" method="post">
					<div class="fieldBasic_area">

					</div>
					<div class="fieldSide_area">

					</div>
					<div>
						<button id="btn_istBus" onclick="return false;">등록</button>
					</div>
				</form>
			</div>
		</div>
	</section>
</div>
<script src="<%=request.getContextPath()%>/js/addBus.js"></script>
<%@ include file="footer.jsp"%>