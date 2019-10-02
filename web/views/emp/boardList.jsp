<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import=" com.semi.board.model.vo.Board" %>
<%@ page import="java.util.List" %>
<%
    request.setCharacterEncoding("UTF-8");
    List<Board> list=(List)request.getAttribute("list");
    int cPage=(int)request.getAttribute("cPage");
    String type=(String)request.getAttribute("searchType");
    String keyword=(String)request.getAttribute("searchKeyword");
    
%>    
<%@ include file="header.jsp"%>

<!-- jQuery library -->
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.4.1/jquery.min.js"></script>
<script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.4.0/js/bootstrap.min.js"></script>
<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.4.0/css/bootstrap.min.css">

<script>

$(function(){
	$("#searchType").change(function(){ //검색 타입별로 나누어서 하나가 선택될경우 하나가 보이지 않게끔 함
		$("#search-boardTitle").css("display","none");
		$("#search-boardContent").css("display","none");
		$("#search-"+$(this).val()).css("display","inline-block");
	});
	$("#searchType").trigger("change"); //검색창 타입별로 플레이스홀더 바뀌게 하는 구문
});

    </script>
    
    <style>
    /*타이틀 부분*/
    #title {text-align: center;}
 
    /*글쓰기버튼*/
	input#btn-add{float:right; margin: 0 0 15px;}
	
	/*페이지바*/
	
	div#pageBar{margin-top:10px; text-align:center;}
	
	div#pageBar span.cPage{color: #0066ff;}
	
	
	
    </style>
  
		<h2 id="title">일하는 자들의 안식처</h2>
		
			
<!--  검색  기능 구현-->
  <div id="neck-container" >
		<div class="container">
		<div id="search-container" class="pull-right">
              
           <select id="searchType" >
              <option value="boardTitle" <%="board_title".equals(type)?"selected":"" %>>제목</option>
              <option value="boardContent" <%="board_content".equals(type)?"selected":"" %>>내용</option>
           </select>
           <div id="search-boardTitle">
              <form action="<%=request.getContextPath()%>/board/boardSearch">
                 <input type="hidden" name="searchType" value="board_title"> 
                 <input type="text" name="searchKeyword" size="25"
                 placeholder="제목입력" value='<%=type!=null&&type.equals("board_title")?keyword:""%>'/> 
                 
                 <button type="submit">검색</button>
              </form>
           </div>
           <div id="search-boardContent">
              <form action="<%=request.getContextPath()%>/board/boardSearch">
                 <input type="hidden" name="searchType" value="board_content">
                 <input type="text" name="searchKeyword" size="25"
                 placeholder="내용입력" value='<%=type!=null&&type.equals("board_content")?keyword:""%>'/>
                 <button type="submit">검색</button>
              </form>
           </div>
           
     </div>
		<table id="tbl-board" class="table table-bordered">
		<thead>
			<tr>
				<th>번호</th>
				<th>제목</th>
				<th>작성일</th>
				<th>첨부파일</th>
				<th>조회수</th>
			</tr>
		</thead>	
		<tbody>
			<%if(list.isEmpty()) {%>
				<tr>
					<td colspan="6">
						등록된 게시글이 없습니다!
					</td>
				</tr>
			<%}else{
				for(Board b : list){ %>
				<tr>
					<td name="boardNo"><%=b.getBoardNo() %></td>
					<td>
						<a href='<%=request.getContextPath()%>/views/boardView?no=<%=b.getBoardNo()%>&cPage=<%=cPage%>'>
							<%=b.getBoardTitle()%>
						</a>
					</td>
					<td><%=b.getBoardDate() %></td>
					<td>
						<%if(b.getBoardOriginalFilename()!=null){ %>
						<img src="<%=request.getContextPath()%>/images/file.png"
						width="16px">
						<%} %>
					</td>
					
					<td><%=b.getBoardReadCount() %></td>
				</tr>
			<%	}
			}%>
			</tbody>
		</table>
		
		<input type="button" value="글쓰기" id="btn-add" class="pull-right"
			onclick="location.href='<%=request.getContextPath()%>/board/Create'"/>
		</div>
		<div id="pageBar">
			<%=request.getAttribute("pageBar") %>
		</div>
		
<%@ include file="footer.jsp"%>
		
	