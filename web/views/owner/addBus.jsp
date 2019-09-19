<%@ page language="java" contentType="text/html; charset=UTF-8"
pageEncoding="UTF-8"%> <%@ page import="com.semi.user.model.vo.User"%> <%@
include file="header.jsp"%>
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
        <form class="frm_addBus" action="<%=request.getContextPath() %>/owner/addBus.do" method="post">
          <fieldset>
            <legend>기본 정보</legend>
            <input type="hidden" name="userId" value="<%=loginOwner.getUserId()%>" />
            <div>사업장 이름</div>
            <input type="text" name="name" value="" />
            <div>사업장 주소</div>
            <input type="text" name="addr" value="" />
            <div>사업장 전화번호</div>
            <input type="tel" name="phone" value="" />
            <div>사업장 사업자번호</div>
            <div>
              <input type="text" name="bNum" required />
            </div>
          </fieldset>
          <fieldset>
            <legend>근무조 등록(가제)</legend>
            <button id="btn_addShift" onclick="return false;">근무조 추가</button>
          </fieldset>
          <div>
            <!-- <button>등록</button> -->
          </div>
        </form>
      </div>
    </div>
  </section>
</div>
<script src="<%=request.getContextPath() %>/js/addBus.js"></script>
<%@ include file="footer.jsp"%>