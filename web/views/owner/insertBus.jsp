<%@page import="java.util.Map"%>
<%@page import="java.util.HashMap"%>
<%@page import="com.google.gson.Gson"%>
<%@page import="com.semi.userinfo.model.vo.UserInfo"%>
<%@page import="com.semi.bus.model.vo.Business"%>
<%@page import="com.semi.owner.model.vo.Owner"%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
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
						<div>사업장 이름</div>
						<input type="text" name="bName" class="inpt-outline" value="" />
						<div>사업장 주소</div>
						<input type="text" name="addr" class="inpt-outline" value="" />
						<div>사업장 전화번호</div>
						<input type="tel" name="phone" class="inpt-outline" value="" />
						<div>사업장 사업자번호</div>
						<input type="text" name="bNum" class="inpt-outline" required />
					</div>
					<div class="fieldSide_area">
						<button id="btn_addSft" onclick="return false;">근무조 추가</button>
					</div>
					<div>
						<button id="btn_istBus" onclick="return false;">등록</button>
					</div>
				</form>
			</div>
		</div>
	</section>
</div>
<script src="<%=request.getContextPath()%>/js/insertBus.js"></script>
<%@ include file="footer.jsp"%>