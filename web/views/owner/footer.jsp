<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
        <!-- //content -->
      </div>
      <button id="btn_chatting"><i class="fa fa-comments" aria-hidden="true"></i></button>
      <!-- //container -->
    </div>
    <!-- JavaScript -->
    <script>
      let socket = new WebSocket("ws://localhost:9090/p_190826_semi/ws");

      socket.onmessage = e => {
      console.log(e.data);
      }
    </script>
    <script src="<%=request.getContextPath()%>/js/common.js"></script>
    <script src="<%=request.getContextPath()%>/js/chatting.js"></script>
  </body>
</html>