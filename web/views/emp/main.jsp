<%@ page language="java" contentType="text/html; charset=UTF-8"
   pageEncoding="UTF-8"%>
<%@ page import="com.semi.user.model.vo.User"%>
<%@ include file="header.jsp"%>
<div id="content" class="content_main">

   <section class="item">
      <div class="item_header">
         <h2>출·퇴근 위젯</h2>
      </div>
      <div class="item_body atd_body">
         <button id="" class="btn-primary btn-primaryOn">출근</button>
         <button id="" class="btn-primary btn-primaryOff">퇴근</button>
      </div>
   </section>
   
<script>
   $('.btn-primaryOn').click(function(){
      $.ajax({
         url: contextPath + "atdOn.do",
              method : "post",
           dataType: "json",
        success: function(data) {
           
      }


      });
   });

   $('.btn-primaryOff').click(function(){
      $.ajax({
         url: contextPath + "atdOff.do",
              method : "post",
           dataType: "json",
        success: function(data) {
      }


      });


});



</script>
</div>
<%@ include file="footer.jsp"%>