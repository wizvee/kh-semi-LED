<%@ page language="java" contentType="text/html; charset=UTF-8"
pageEncoding="UTF-8"%> <%@ page import="com.semi.user.model.vo.User"%> <%@
include file="header.jsp"%>
<div id="content">
  <section class="item">
    <div class="item_header">
      <h2>사업장 등록</h2>
    </div>
    <div class="item_body">
      <form class="frm_addBus" action="" method="post">
        <div class="addBus_area">
          <div>
            <input type="text" id="input" class="inpt-outline" />
            <button class="btn-primary" onclick="fn_search(); return false;">
              검색
            </button>
          </div>
        </div>
      </form>
    </div>
  </section>
</div>
<script>
  function fn_search() {
    $.ajax({
      type: "post",
      async: false,
      url: "/p_190826_semi/search.do",
      dataType: "text",
      data: { data: $("#input").val() },
      success: function(data) {
        var json = JSON.parse(data);
        $(".addBus_area .result_area").remove();
        for (var i in json.items) {
          var area = $("<div class='result_area'>");
          area.append($("<p class='title'>").html(json.items[i].title));
          area.append($("<p class='address'>").html(json.items[i].roadAddress));
          area.append($("<p class='telephone'>").html(json.items[i].telephone));
          $(".addBus_area").append(area);
        }
      },
      error: function() {
        alert("연결실패");
      }
    });
  }
</script>
<%@ include file="footer.jsp"%>
